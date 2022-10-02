package com.template.quiz.ui.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.template.R


class HomeQuizFragment : Fragment(), View.OnClickListener {
    private lateinit var btnPlay: ImageView
    private lateinit var btnUserProfile: ImageView
    private lateinit var userNameTx: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = layoutInflater.inflate(R.layout.fragment_home_quiz, container, false)

        btnPlay = view.findViewById(R.id.btnPlay)
        btnUserProfile = view.findViewById(R.id.userPicClickable)
        userNameTx = view.findViewById(R.id.userNameTx)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnPlay.setOnClickListener(this)
        btnUserProfile.setOnClickListener(this)
        userNameTx.setOnClickListener(this)

        val sharedPrefsUserName = context?.getSharedPreferences("userName", Context.MODE_PRIVATE)
        val sharedPrefsUserPic = context?.getSharedPreferences("userPic", Context.MODE_PRIVATE)

        if (!sharedPrefsUserName?.contains("userName")!!)
            sharedPrefsUserName.edit()?.putString("userName", "User")?.apply()
        userNameTx.text = sharedPrefsUserName.getString("userName", "")

        if (!sharedPrefsUserPic?.contains("userPic")!!)
            sharedPrefsUserPic.edit()?.putInt("userPic", R.mipmap.astronaut)?.apply()
        btnUserProfile.setImageResource(sharedPrefsUserPic.getInt("userPic", R.mipmap.astronaut))
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btnPlay -> {
                val gameMode = resources.getStringArray(R.array.quiz_levels)
                var selectedGameModeIndex = 0
                var selectedGameModeString = ""
                MaterialAlertDialogBuilder(requireContext()).setTitle(R.string.select_game_level)
                    .setSingleChoiceItems(gameMode,selectedGameModeIndex){ _, which ->
                        selectedGameModeIndex = which
                        selectedGameModeString = gameMode[which]
                    }
                    .setPositiveButton("Ok") { _, _ ->
                        var numbersOfQuestions = 10
                        when (selectedGameModeString){
                            "Medium" -> numbersOfQuestions = 15
                            "High" -> numbersOfQuestions = 20
                        }
                        val playingFragment = PlayingQuizFragment.getNumbersOfQuestions(numbersOfQuestions)
                        changeFragment(playingFragment)
                    }
                    .setNegativeButton("Cancel") { dialog, _ ->
                        dialog.dismiss()
                    }.show()
            }
            R.id.userPicClickable, R.id.userNameTx -> changeFragment(ProfileQuizFragment())
        }
    }
    private fun changeFragment(fragment: Fragment){
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.frgView, fragment)
            ?.addToBackStack("backToMain")
            ?.commit()
    }
}