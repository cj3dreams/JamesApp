package com.template.about

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.template.R
import com.template.about.view.AboutDescriptionFragment

class AboutAppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_app)
        supportFragmentManager.beginTransaction().apply {
            add(R.id.frag_cont, AboutDescriptionFragment()).commit()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.fragments.size == 1) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}