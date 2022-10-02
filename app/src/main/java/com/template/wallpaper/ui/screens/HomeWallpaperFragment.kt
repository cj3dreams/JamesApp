package com.template.wallpaper.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.template.R
import com.template.wallpaper.data.WallpapersData
import com.template.wallpaper.model.WallpaperModel
import com.template.wallpaper.ui.adapter.WallpapersAdapter
import com.template.wallpaper.ui.screens.ApplyFragment

class HomeWallpaperFragment : Fragment(), View.OnClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var wallpapersAdapter: WallpapersAdapter

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
        wallpapersAdapter = WallpapersAdapter(WallpapersData.getAllWallpapers(requireContext()),this)
        recyclerView.adapter = wallpapersAdapter

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