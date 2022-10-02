package com.template.wallpaper.ui.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.template.R
import com.template.wallpaper.model.WallpaperModel

class WallpapersAdapter(
    private val list: List<WallpaperModel>,
    private val onClickListener: View.OnClickListener
) : RecyclerView.Adapter<WallpapersAdapter.WallpapersViewHolder>() {

    class WallpapersViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemClick = view.findViewById(R.id.itemClick) as CardView
        val wallpaperImgView = view.findViewById(R.id.wallpaperImgView) as ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpapersViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_wallpaper, parent, false)
        return WallpapersViewHolder(view)
    }

    override fun onBindViewHolder(holder: WallpapersViewHolder, position: Int) {
        val itemData = list[position]
        runCatching {
            holder.itemClick.tag = itemData
            holder.itemClick.setOnClickListener(onClickListener)
            val context = holder.itemView.context
            val loadingAnimationDrawable = CircularProgressDrawable(context)
            loadingAnimationDrawable.setStyle(CircularProgressDrawable.DEFAULT)
            loadingAnimationDrawable.setColorSchemeColors(Color.GRAY, Color.DKGRAY, Color.LTGRAY)
            loadingAnimationDrawable.setStyle(CircularProgressDrawable.LARGE)

            glide(
                context, holder.wallpaperImgView,
                Uri.parse("file:///android_asset/wallpapers/${itemData.fileName}"),
                loadingAnimationDrawable
            )
        }
    }

    override fun getItemCount() = list.size

    private fun glide(context: Context?, imageView: ImageView, pathToImage: Uri?, loadingAnim: Drawable) {
        context?.let {
            Glide.with(it).load(pathToImage)
                .thumbnail(
                    Glide.with(it).load(loadingAnim))
                .into(imageView)
        }
    }
}