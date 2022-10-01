package com.template.quiz.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.template.R

class FinishQuizFragment : Fragment(), View.OnClickListener {
    private lateinit var resultImgView: ImageView
    private lateinit var resultTx: TextView
    private lateinit var btnReplay: Button
    private lateinit var btnBackHome: Button

    private lateinit var incorrectAnswers: String
    private lateinit var correctAnswers: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = layoutInflater.inflate(R.layout.fragment_finish_quiz, container, false)

        resultImgView = view.findViewById(R.id.resultImgView)
        resultTx = view.findViewById(R.id.resultTx)
        btnReplay = view.findViewById(R.id.btnReplay)
        btnBackHome = view.findViewById(R.id.btnBackHome)

        return view
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnReplay.setOnClickListener(this)
        btnBackHome.setOnClickListener(this)

        val sixtyPercent = ((incorrectAnswers.toInt()+correctAnswers.toInt())/100.0)*60.0

        val result = if (sixtyPercent <= correctAnswers.toDouble()) "Отлично!" else "Могло бы быть и лучше"
        resultTx.text = "$result\nПравильных: $correctAnswers\nНеправильных: $incorrectAnswers"

        if (sixtyPercent <= correctAnswers.toDouble()) resultImgView.setImageResource(R.mipmap.victory)
        else resultImgView.setImageResource(R.mipmap.lose)

    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btnBackHome->changeFragment(HomeQuizFragment())
            R.id.btnReplay->{
                val gameMode = arrayOf("Легкий", "Средний", "Сложный")
                var selectedGameModeIndex = 0
                var selectedGameModeString = ""
                MaterialAlertDialogBuilder(requireContext()).setTitle("Выберите сложность")
                    .setSingleChoiceItems(gameMode,selectedGameModeIndex){ _, which ->
                        selectedGameModeIndex = which
                        selectedGameModeString = gameMode[which]
                    }
                    .setPositiveButton("Ok") { _, _ ->
                        var numbersOfQuestions = 10
                        when (selectedGameModeString){
                            "Средний" -> numbersOfQuestions = 15
                            "Сложный" -> numbersOfQuestions = 20
                        }
                        val playingFragment = PlayingQuizFragment.getNumbersOfQuestions(numbersOfQuestions)
                        changeFragment(playingFragment)
                    }
                    .setNegativeButton("Отмена") { dialog, _ ->
                        dialog.dismiss()
                    }.show()
            }
        }
    }
    private fun changeFragment(fragment: Fragment) {
        activity?.supportFragmentManager?.popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        activity?.supportFragmentManager?.beginTransaction()
            ?.remove(FinishQuizFragment())
            ?.replace(R.id.frgView, fragment)
            ?.commit()
    }

    companion object{
        fun getResultsOfGame(correctAnswers: Int, incorrectAnswers: Int): Fragment{
            val finishFragment = FinishQuizFragment()
            finishFragment.correctAnswers = correctAnswers.toString()
            finishFragment.incorrectAnswers = incorrectAnswers.toString()
            return finishFragment
        }
    }
}