package com.template.wallpaper.ui.screens

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.template.R
import com.template.wallpaper.data.WallpapersData
import com.template.wallpaper.model.WallpaperModel
import com.template.wallpaper.WallpaperActivity

class ApplyFragment : Fragment() {
    private lateinit var wallpaperImgView: ImageView
    private lateinit var wallpaperNameTx: TextView
    private lateinit var applyBtn: Button
    private lateinit var wallpaperModel: WallpaperModel
    private lateinit var loadingAnimationDrawable: CircularProgressDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val act = activity as WallpaperActivity
        act.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_wallpaper_apply, container, false)

        wallpaperImgView = view.findViewById(R.id.applyImgView)
        wallpaperNameTx = view.findViewById(R.id.applyWallpaperName)
        applyBtn = view.findViewById(R.id.applyButton)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingAnimationDrawable = CircularProgressDrawable(requireContext()).apply {
            setStyle(CircularProgressDrawable.DEFAULT)
            setColorSchemeColors(Color.GRAY, Color.DKGRAY, Color.LTGRAY)
            setStyle(CircularProgressDrawable.LARGE)
        }

        wallpaperNameTx.text = WallpapersData.getWallpapersNameIfExist(wallpaperModel.fileName)
            ?: resources.getString(R.string.app_name)

        glide(requireContext(), wallpaperImgView, Uri.parse("file:///android_asset/wallpapers/${wallpaperModel.fileName}"))

        applyBtn.setOnClickListener {
           val wallpaperManager = WallpaperManager.getInstance(requireContext())
            try {
                wallpaperManager.setStream(requireContext().resources.assets.open("wallpapers/${wallpaperModel.fileName}"))
                Toast.makeText(requireContext(), "Wallpaper has been set!", Toast.LENGTH_SHORT).show()
            }catch (e: Exception){
                Log.e("Wallpaper manager error", e.message.toString())
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        val act = activity as WallpaperActivity
        act.supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun glide(context: Context?, imageView: ImageView, pathToImage: Uri?) {
        context?.let {
            Glide.with(it).load(pathToImage)
                .thumbnail(
                    Glide.with(it).load(loadingAnimationDrawable))
                .into(imageView)
        }
    }

    companion object{
        fun getWallpaperModel(element: WallpaperModel): Fragment {
            val applyFragment = ApplyFragment()
            applyFragment.wallpaperModel = element

            return applyFragment
        }
    }
}