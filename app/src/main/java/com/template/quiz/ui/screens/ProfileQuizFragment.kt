package com.template.quiz.ui.screens

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.template.R
import com.template.quiz.domain.model.ChooseImageModel
import com.template.quiz.ui.adapter.ChooseImageAdapter

class ProfileQuizFragment : Fragment(), View.OnClickListener {
    private lateinit var profileImgView: ImageView
    private lateinit var profileUserName: EditText
    private lateinit var profileSave: Button
    private lateinit var shower: AlertDialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_profile_quiz, container, false)

        profileImgView = view.findViewById(R.id.profileImgView)
        profileUserName = view.findViewById(R.id.profileUserNameTx)
        profileSave = view.findViewById(R.id.profileSaveBtn)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPrefsUserName = context?.getSharedPreferences("userName", Context.MODE_PRIVATE)
        val sharedPrefsUserPic = context?.getSharedPreferences("userPic", Context.MODE_PRIVATE)

        profileImgView.setImageResource(sharedPrefsUserPic!!.getInt("userPic", R.mipmap.astronaut))
        profileUserName.setText(sharedPrefsUserName?.getString("userName", "").toString())

        profileSave.setOnClickListener(this)
        profileImgView.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.profileSaveBtn -> {
                val sharedPrefsUserName = context?.getSharedPreferences("userName", Context.MODE_PRIVATE)
                if(profileUserName.text.toString().isNotEmpty()) {
                    sharedPrefsUserName?.edit()?.putString("userName", profileUserName.text.toString())?.apply()
                    Snackbar.make(v, "Сохранено", 1500).show()
                }
            }
            R.id.profileImgView ->{
                val dialog = AlertDialog.Builder(requireContext())
                val view = layoutInflater.inflate(R.layout.dialog_layout, null, false)
                dialog.setView(view)

                val adapter = ChooseImageAdapter(requireContext(),
                    mutableListOf(
                        ChooseImageModel("Man", R.mipmap.man),
                        ChooseImageModel("Boy", R.mipmap.boy),
                        ChooseImageModel("Woman", R.mipmap.woman),
                        ChooseImageModel("Astronaut", R.mipmap.astronaut)
                    ), this)
                val recyclerView = view.findViewById(R.id.recyclerViewDialog) as RecyclerView
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
                dialog.setView(view)
                shower = dialog.show()
                shower
            }
            R.id.itemClick ->{
                val tag = v.tag as Int
                val sharedPrefsUserPic = context?.getSharedPreferences("userPic", Context.MODE_PRIVATE)
                sharedPrefsUserPic?.edit()?.putInt("userPic", tag)?.apply()
                shower.cancel()
                profileImgView.setImageResource(sharedPrefsUserPic!!.getInt("userPic", R.mipmap.astronaut))
            }
        }
    }
}