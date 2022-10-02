package com.template.about

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.template.R
import com.template.about.view.AboutDescriptionFragment
import com.template.about.view.DescriptionFragment

class AboutAppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_app)
        supportFragmentManager.beginTransaction().apply {
            add(R.id.frag_cont, AboutDescriptionFragment()).commit()
        }
    }
}