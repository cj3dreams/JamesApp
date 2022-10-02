package com.template.about

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.template.R
import com.template.about.view.AboutDescriptionFragment

class AboutAppActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_app)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frag_cont, AboutDescriptionFragment()).commit()
        }
    }
}