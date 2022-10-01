package com.template.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.template.R
import com.template.about.AboutAppActivity
import com.template.memory.MemoryActivity
import com.template.quiz.QuizActivity
import com.template.wallpaper.WallpaperActivity

class MainActivity : AppCompatActivity() {
    private lateinit var sun: ImageView
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
        sun = findViewById(R.id.sun)
        sun.setOnClickListener {
            Toast.makeText(this, "sun", Toast.LENGTH_SHORT).show()
        }
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
        jupiter.setOnClickListener {
            Toast.makeText(this, "earth", Toast.LENGTH_SHORT).show()
        }
        saturn = findViewById(R.id.saturn)
        saturn.setOnClickListener {
            Toast.makeText(this, "earth", Toast.LENGTH_SHORT).show()
        }
        uranus = findViewById(R.id.uranus)
        uranus.setOnClickListener {
            Toast.makeText(this, "earth", Toast.LENGTH_SHORT).show()
        }
        neptune = findViewById(R.id.neptune)
        neptune.setOnClickListener {
            Toast.makeText(this, "earth", Toast.LENGTH_SHORT).show()
        }
    }
}