package com.template.memory.ui.screens

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.template.R
import com.template.memory.data.Data
import com.template.memory.ui.adapter.GameAdapter
import java.util.*
import kotlin.properties.Delegates

class GameFragment : Fragment(), View.OnClickListener {
    private var gameMode by Delegates.notNull<Int>()
    private var passedSeconds = 0
    private lateinit var timer: Timer
    private lateinit var countTimer: CountDownTimer

    private var recyclerViewObserver: MutableLiveData<List<String>> = MutableLiveData()
    private lateinit var recyclerView: RecyclerView

    private lateinit var emojisByCountList: List<String>
    private lateinit var hiddenEmojisList: MutableList<String>

    private var clicked = 0
    private var firstClickedPosition = -1
    private var secondClickedPosition = -1
    private var foundCards = 0
    private var isClickable = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        emojisByCountList = Data.getAllEmojis(gameMode)
        recyclerViewObserver.value = emojisByCountList
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_game, container, false)

        recyclerView = view.findViewById(R.id.gameRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), gameMode)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewObserver.observe(viewLifecycleOwner, {
            recyclerView.adapter = GameAdapter(requireContext(), it, this)
        })

        countTimer = object :CountDownTimer(3000, 1000){
            override fun onTick(p0: Long) {}

            override fun onFinish() {
                hiddenEmojisList = Data.getHiddenItems(gameMode).toMutableList()
                recyclerViewObserver.postValue(hiddenEmojisList)
                cancel()
            }
        }
        countTimer.start()
    }

    override fun onClick(v: View?) {
        if (v != null && this::hiddenEmojisList.isInitialized && isClickable) when(v.id){

            R.id.itemClick -> {
                isClickable = false
                val tag = v.tag as Int

                clicked++
                setUpGame(tag)
                if ((gameMode*gameMode)/2 == foundCards) {

                    activity?.supportFragmentManager?.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.frgView, FinalFragment.getTimeModel(passedSeconds, gameMode)).apply { timer.cancel() }
                        ?.addToBackStack("backToMain")
                        ?.commit()
                    activity?.supportFragmentManager?.fragments?.set(0, HomeFragment())
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        timer = Timer()
        timer.scheduleAtFixedRate(object: TimerTask() {
            override fun run() { passedSeconds++ } },3000, 1000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        timer.cancel()
    }

    private fun setUpGame(position: Int){
        val tempHiddenEmojisList = mutableListOf<String>().apply { addAll(hiddenEmojisList) }

        if (clicked == 1) {
            firstClickedPosition = position

            tempHiddenEmojisList[position] = emojisByCountList[position]
            recyclerViewObserver.postValue(tempHiddenEmojisList)

            isClickable = true

        }
        else if(clicked == 2) {
            secondClickedPosition = position

            if (firstClickedPosition == secondClickedPosition) {
                snackBar("Вы уже нажали эту карточку")
                clicked = 1
                isClickable = true

            }else{

                tempHiddenEmojisList[firstClickedPosition] = emojisByCountList[firstClickedPosition]
                tempHiddenEmojisList[secondClickedPosition] = emojisByCountList[secondClickedPosition]
                recyclerViewObserver.postValue(tempHiddenEmojisList)

                clicked = 0

                if (emojisByCountList[firstClickedPosition] == emojisByCountList[position]) {
                    snackBar("Правильно")

                    hiddenEmojisList[firstClickedPosition] = emojisByCountList[firstClickedPosition] + " hide"
                    hiddenEmojisList[secondClickedPosition] = emojisByCountList[secondClickedPosition] + " hide"

                    object : CountDownTimer(1200, 1200) {
                        override fun onTick(p0: Long) {}
                        override fun onFinish() {
                            recyclerViewObserver.postValue(hiddenEmojisList)
                            isClickable = true
                            cancel()
                        }
                    }.start()

                    firstClickedPosition = -1
                    secondClickedPosition = -1
                    foundCards++

                } else {
                    snackBar("Неправильно")

                    object : CountDownTimer(1200, 1200) {
                        override fun onTick(p0: Long) {}
                        override fun onFinish() {
                            recyclerViewObserver.postValue(hiddenEmojisList)
                            isClickable = true
                            cancel()
                        }
                    }.start()
                }
            }
        }
    }

    private fun snackBar(text: String) = Snackbar.make(requireView(), text, 1200).show()

    companion object{
        fun getGameMode(value: Int) = GameFragment().apply { this.gameMode = value }
        }
}