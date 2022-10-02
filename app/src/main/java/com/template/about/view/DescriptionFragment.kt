package com.template.about.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.template.R

class DescriptionFragment(private val number: Int) : Fragment() {
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = layoutInflater.inflate(R.layout.fragment_solar_orbit, container, false)
        imageView = view.findViewById(R.id.teleskop_orbit_image)
        textView = view.findViewById(R.id.orbit_text1)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (number == 1){
            textView.setText(R.string.L2)
            imageView.setImageResource(R.drawable.orbitajwst)
        }
        if (number == 2){
            textView.setText(R.string.JWST)
            imageView.setImageResource(R.drawable.memory4)
        }
        if (number == 3){
            textView.setText(R.string.solar_system)
            imageView.setImageResource(R.drawable.solar_system)
        }
        if (number == 4){
            textView.setText(R.string.memory1)
            imageView.setImageResource(R.drawable.memory1)
        }
        if (number == 5){
            textView.setText(R.string.memory11)
            imageView.setImageResource(R.drawable.memory11)
        }
    }
}