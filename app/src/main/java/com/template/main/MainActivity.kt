package com.template.main

import android.content.Intent
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.template.R
import com.template.about.AboutAppActivity
import com.template.memory.MemoryActivity
import com.template.quiz.QuizActivity
import com.template.wallpaper.WallpaperActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mercury: ImageView
    private lateinit var venus: ImageView
    private lateinit var earth: ImageView
    private lateinit var mars: ImageView
    private lateinit var jupiter: ImageView
    private lateinit var saturn: ImageView
    private lateinit var uranus: ImageView
    private lateinit var neptune: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mercury = findViewById(R.id.mercury)
        mercury.setOnClickListener {
            startActivity(Intent(this, MemoryActivity::class.java))
        }
        venus = findViewById(R.id.venus)
        venus.setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java))
        }
        earth = findViewById(R.id.earth)
        earth.setOnClickListener {
            startActivity(Intent(this, AboutAppActivity::class.java))
        }
        mars = findViewById(R.id.mars)
        mars.setOnClickListener {
            startActivity(Intent(this, WallpaperActivity::class.java))
        }
        jupiter = findViewById(R.id.jupiter)
        jupiter.apply {
            setOnClickListener(this@MainActivity)
            makeBlackAndWhite()
        }
        saturn = findViewById(R.id.saturn)
        saturn.apply {
            setOnClickListener(this@MainActivity)
            makeBlackAndWhite()
        }
        uranus = findViewById(R.id.uranus)
        uranus.apply {
            setOnClickListener(this@MainActivity)
            makeBlackAndWhite()
        }
        neptune = findViewById(R.id.neptune)
        neptune.apply {
            setOnClickListener(this@MainActivity)
            makeBlackAndWhite()
        }
    }

    private fun ImageView.makeBlackAndWhite() {
        colorFilter = ColorMatrixColorFilter(ColorMatrix().apply { setSaturation(0f) })
    }

    // Unavailable menus action
    override fun onClick(v: View?) {
        Toast.makeText(this, "Coming soon...", Toast.LENGTH_SHORT).show()
    }

}