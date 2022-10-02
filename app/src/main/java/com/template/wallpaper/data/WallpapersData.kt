package com.template.wallpaper.data

import android.content.Context
import com.template.wallpaper.model.WallpaperModel

object WallpapersData {

    private val wallpaperNames = listOf(

        WallpaperModel("Space","wallpaper_1.png"),

        WallpaperModel("Just a Beautiful Nature", "wallpaper_3.jpg"),

    )

    fun getWallpapersNameIfExist(fileName: String?): String? {
        var name: String? = null
        if(!fileName.isNullOrEmpty()) wallpaperNames.forEach {
            if (it.fileName == fileName) name = it.name
        }
        return name
    }

    fun getAllWallpapers(context: Context): List<WallpaperModel> {

        val wallpapersAssets = context.resources.assets.list("wallpapers")
        wallpapersAssets?.sortWith { o1, o2 ->
            val n1: Int = o1.substring(o1.indexOf("_") + 1, o1.indexOf(".")).toInt()
            val n2: Int = o2.substring(o2.indexOf("_") + 1, o2.indexOf(".")).toInt()
            n1.compareTo(n2)
        }

        val list = mutableListOf<WallpaperModel>()
        if(!wallpapersAssets.isNullOrEmpty())
            wallpapersAssets.forEach {
                list.add(WallpaperModel(fileName = it))
            }

        return list
    }

}