package com.template.quiz.ui.screens

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.google.android.material.snackbar.Snackbar
import com.template.R
import com.template.quiz.data.QuestionsConstants
import com.template.quiz.domain.model.QuestionModel
import kotlin.collections.shuffled


class PlayingQuizFragment : Fragment(), View.OnClickListener {
    private lateinit var questionTx: TextView
    private lateinit var questionImgView: ImageView
    private lateinit var answer1: Button
    private lateinit var answer2: Button
    private lateinit var answer3: Button
    private lateinit var answer4: Button
    private lateinit var currentQuestionTx: TextView

    private lateinit var listOfQuestionModel: List<QuestionModel>

    private var numbersOfQuestions = 10
    private var currentQuestion = 0
    private var incorrectAnswers = 0
    private var correctAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listOfQuestionModel = QuestionsConstants.getQuestions().shuffled()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = layoutInflater.inflate(R.layout.fragment_playing_quiz, container, false)

        questionTx = view.findViewById(R.id.questionTx)
        questionImgView = view.findViewById(R.id.questionImgView)
        answer1 = view.findViewById(R.id.answer1Btn)
        answer2 = view.findViewById(R.id.answer2Btn)
        answer3 = view.findViewById(R.id.answer3Btn)
        answer4 = view.findViewById(R.id.answer4Btn)
        currentQuestionTx = view.findViewById(R.id.currentQuestionTx)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpQuiz()
        answer1.setOnClickListener(this)
        answer2.setOnClickListener(this)
        answer3.setOnClickListener(this)
        answer4.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.answer1Btn -> {
                if(answer1.text == questionTx.tag) doCorrectTask()
                else doIncorrectTask()
                setUpQuiz()
            }
            R.id.answer2Btn ->  {
                if(answer2.text == questionTx.tag) doCorrectTask()
                else doIncorrectTask()
                setUpQuiz()
            }
            R.id.answer3Btn ->  {
                if(answer3.text == questionTx.tag) doCorrectTask()
                else doIncorrectTask()
                setUpQuiz()
            }
            R.id.answer4Btn ->  {
                if(answer4.text == questionTx.tag) doCorrectTask()
                else doIncorrectTask()
                setUpQuiz()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setUpQuiz() {
        if (currentQuestion == numbersOfQuestions) {
            val finishFragment = FinishQuizFragment.getResultsOfGame(correctAnswers, incorrectAnswers)
            activity?.supportFragmentManager?.popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.frgView, finishFragment)
                ?.commit()
        }
        else {
            val shuffledAnswersIndex = listOf(0, 1, 2, 3).shuffled()
            val questionsIndex = (0 until numbersOfQuestions).toList()
            val questionModel = listOfQuestionModel[questionsIndex[currentQuestion]]

            if (questionModel.imageOfQuestion != null ){
                questionImgView.setImageDrawable(Drawable
                    .createFromStream(context?.assets?.open("quiz_pictures/"
                            + questionModel.imageOfQuestion), null))
                questionImgView.visibility = View.VISIBLE
            }

            else questionImgView.visibility = View.GONE

            questionTx.text = questionModel.question
            questionTx.tag = questionModel.correctAnswer
            answer1.text = questionModel.answers[shuffledAnswersIndex[0]]
            answer2.text = questionModel.answers[shuffledAnswersIndex[1]]
            answer3.text = questionModel.answers[shuffledAnswersIndex[2]]
            answer4.text = questionModel.answers[shuffledAnswersIndex[3]]

            currentQuestion++
            currentQuestionTx.text = "$currentQuestion/$numbersOfQuestions"
        }
    }
    private fun doCorrectTask(){
        Snackbar.make(requireView(), "Правильно!", 1000).show()
        correctAnswers++
    }
    private fun doIncorrectTask(){
        Snackbar.make(requireView(), "Неправильно! Правильный ответ: ${questionTx.tag}", 2000).show()
        incorrectAnswers++
    }

    companion object{
        fun getNumbersOfQuestions(value: Int): Fragment{
            val playingFragment = PlayingQuizFragment()
            playingFragment.numbersOfQuestions = value
            return playingFragment
        }
    }
}