package com.template.about.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.template.R

class AboutDescriptionFragment() : Fragment() {

    companion object {

        const val TAG = "AboutDescriptionFragment"

    }

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View =  layoutInflater.inflate(R.layout.fragment_about_discription, container, false)
        button1 = view.findViewById(R.id.orbit_button)
        button1.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction().apply {
                add(R.id.frag_cont, DescriptionFragment(1), DescriptionFragment.TAG)
                    .addToBackStack(DescriptionFragment.TAG)
                    .commit()
            }
        }
        button2 = view.findViewById(R.id.button2)
        button2.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction().apply {
                add(R.id.frag_cont, DescriptionFragment(2), DescriptionFragment.TAG)
                    .addToBackStack(DescriptionFragment.TAG)
                    .commit()
            }
        }
        button3 = view.findViewById(R.id.button3)
        button3.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction().apply {
                add(R.id.frag_cont, DescriptionFragment(3), DescriptionFragment.TAG)
                    .addToBackStack(DescriptionFragment.TAG)
                    .commit()
            }
        }
        button4 = view.findViewById(R.id.button4)
        button4.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction().apply {
                add(R.id.frag_cont, DescriptionFragment(4), DescriptionFragment.TAG)
                    .addToBackStack(DescriptionFragment.TAG)
                    .commit()
            }
        }
        button5 = view.findViewById(R.id.button5)
        button5.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction().apply {
                add(R.id.frag_cont, DescriptionFragment(5), DescriptionFragment.TAG)
                    .addToBackStack(DescriptionFragment.TAG)
                    .commit()
            }
        }
        return view
    }
}