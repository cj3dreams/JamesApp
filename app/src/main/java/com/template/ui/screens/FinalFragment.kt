package com.template.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.template.R
import kotlin.properties.Delegates

class FinalFragment : Fragment(), View.OnClickListener{
    private var totallySeconds by Delegates.notNull<Int>()
    private var gameMode by Delegates.notNull<Int>()
    private lateinit var sharedPrefStarts: SharedPreferences

    private lateinit var homeBtn: ImageView
    private lateinit var replayBtn: ImageView
    private lateinit var resultTimeTx: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPrefStarts = requireActivity().getSharedPreferences("starsCount", Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_final, container, false)

        homeBtn = view.findViewById(R.id.finalHomeBtn)
        replayBtn = view.findViewById(R.id.finalReplayButton)
        resultTimeTx = view.findViewById(R.id.finalResultTimeTx)

        return view
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val h = totallySeconds / 3600; val m = (totallySeconds % 3600) / 60; val s = totallySeconds % 60

        resultTimeTx.text = resources.getString(R.string.resultText)+
                String.format("%02d:%02d:%02d", h, m, s)

        replayBtn.setOnClickListener(this)
        homeBtn.setOnClickListener(this)

        val ss = totallySeconds
        when(gameMode){
            2 -> when {
                ss < 11 -> giveStarsAndShowDialog(3, "Отлично!")
                ss < 17 -> giveStarsAndShowDialog(2, "Неплохо!")
                ss < 25 -> giveStarsAndShowDialog(2, "Удотворительно")
            }
            4 -> when {
                ss < 43 -> giveStarsAndShowDialog(3, "Отлично!")
                ss < 55 -> giveStarsAndShowDialog(2, "Неплохо!")
                ss < 70 -> giveStarsAndShowDialog(2, "Удотворительно")
            }
            6 ->  when {
                ss < 300 -> giveStarsAndShowDialog(3, "Отлично!")
                ss < 360 -> giveStarsAndShowDialog(2, "Неплохо!")
                ss < 500 -> giveStarsAndShowDialog(2, "Удотворительно")
            }
            8 -> when {
                ss < 600 -> giveStarsAndShowDialog(3, "Отлично!")
                ss < 700 -> giveStarsAndShowDialog(2, "Неплохо!")
                ss < 900 -> giveStarsAndShowDialog(2, "Удотворительно")
            }
        }
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.finalReplayButton -> changeFragmentClearly(GameFragment.getGameMode(gameMode))
            R.id.finalHomeBtn -> changeFragmentClearly(HomeFragment(), "h")
        }
    }

    private fun giveStarsAndShowDialog(starsCount: Int, message: String){
        sharedPrefStarts.edit()
            .putInt("starsCount", sharedPrefStarts.getInt("starsCount", 0) + starsCount).apply()

        MaterialAlertDialogBuilder(requireContext())
            .setIcon(R.drawable.ic_star)
            .setTitle(message)
            .setMessage("Вы получили $starsCount" + if(starsCount > 1) " звезды" else " звезду")
            .setNegativeButton("Ok"){ dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun changeFragmentClearly(fragment: Fragment, value: String = "g") {
        activity?.supportFragmentManager?.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.frgView, fragment)?.apply {
                if (value != "h") this.addToBackStack("backToMain") }
            ?.commit()
        if (value != "h") activity?.supportFragmentManager?.fragments?.set(0, HomeFragment())
    }

    companion object{
        fun getTimeModel(seconds: Int, mode: Int) =
            FinalFragment().apply { this.totallySeconds = seconds; this.gameMode = mode }
    }
}