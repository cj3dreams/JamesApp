package com.template.about

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.template.R

class AboutAppActivity : AppCompatActivity() {
    private lateinit var solarOrbitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_app)
        solarOrbitButton = findViewById(R.id.orbit_button)
        solarOrbitButton.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.frag_cont, SolarOrbit()).commit()
                solarOrbitButton.visibility = View.INVISIBLE
            }
        }
    }
}