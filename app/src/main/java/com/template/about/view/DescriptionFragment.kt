package com.template.about.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.template.R

class DescriptionFragment(private val number: Int) : Fragment() {

    companion object{

        const val TAG = "DescriptionFragment"

    }
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = layoutInflater.inflate(R.layout.fragment_description, container, false)
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
            textView.setText(R.string.hublle)
            imageView.setImageResource(R.drawable.hubble)
        }
        if (number == 5){
            textView.setText(R.string.milky_way)
            imageView.setImageResource(R.drawable.milky_way)
        }
    }
}