package com.template

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.template.ui.screens.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        window.statusBarColor = resources.getColor(R.color.primary_dark)

        supportFragmentManager.beginTransaction()
            .replace(R.id.frgView, HomeFragment())
            .commit()
    }
    fun mem(){

    }
}