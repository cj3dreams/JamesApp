package com.template.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.template.R
import com.template.data.WallpapersData
import com.template.model.WallpaperModel
import com.template.ui.adapter.WallpapersAdapter
import java.util.*

class HomeWallpaperFragment : Fragment(), View.OnClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: WallpapersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_wallpaper_home, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = WallpapersAdapter(requireContext(),
            WallpapersData.getAllWallpapers(requireContext()),this)
        recyclerView.adapter = adapter


    }

    override fun onClick(v: View?) {
        when (v!!.id){
            R.id.itemClick -> {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.frgView, ApplyFragment.getWallpaperModel(v.tag as WallpaperModel))
                    ?.addToBackStack("backToMain")
                    ?.commit()
            }
        }
    }
}