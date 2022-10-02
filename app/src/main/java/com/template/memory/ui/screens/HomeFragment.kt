package com.template.memory.ui.screens

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.template.R
import kotlin.properties.Delegates


class HomeFragment : Fragment() {
    private lateinit var sharedPrefStarts: SharedPreferences
    private lateinit var starsCountTx: TextView
    private lateinit var playBtn: Button
    private lateinit var spinnerGameMode: Spinner

    private var selectedSpinner by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPrefStarts = requireActivity().getSharedPreferences("starsCount", MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        starsCountTx = view.findViewById(R.id.homeStarsCountTx)
        spinnerGameMode = view.findViewById(R.id.homeSpinner)
        playBtn = view.findViewById(R.id.homePlayBtn)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        starsCountTx.text = sharedPrefStarts.getInt("starsCount", 0).toString()

        spinnerGameMode.adapter = ArrayAdapter.createFromResource(requireContext(),
            R.array.gameModeArray, android.R.layout.simple_spinner_dropdown_item)

        spinnerGameMode.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, v: View?, position: Int, p3: Long) {

                selectedSpinner = when (position){ 0 -> 2; 1 -> 4; 2 -> 6; 3 -> 8; else -> 0 }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        playBtn.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.frgView, GameMemoryFragment.getGameMode())
                ?.addToBackStack("backToMain")
                ?.commit()
        }
    }
}