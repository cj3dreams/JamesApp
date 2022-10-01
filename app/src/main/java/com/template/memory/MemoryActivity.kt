package com.template.memory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.template.R
import com.template.memory.ui.screens.HomeFragment

class MemoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory)

        supportActionBar?.hide()
        window.statusBarColor = resources.getColor(R.color.primary_dark)

        supportFragmentManager.beginTransaction()
            .replace(R.id.frgView, HomeFragment())
            .commit()
    }
    fun mem(){

    }
}