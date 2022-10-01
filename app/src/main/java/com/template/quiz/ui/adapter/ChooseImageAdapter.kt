package com.template.quiz.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.template.R
import com.template.quiz.domain.model.ChooseImageModel

class ChooseImageAdapter(private val context: Context,
    private val list: MutableList<ChooseImageModel>, val onClickListener: View.OnClickListener)
    : RecyclerView.Adapter<ChooseImageAdapter.ChooseImageViewHolder>() {

    class ChooseImageViewHolder(view: View): RecyclerView.ViewHolder(view){
        val itemClick = view.findViewById(R.id.itemClick) as CardView
        val itemPic = view.findViewById(R.id.itemPic) as ImageView
        val itemName = view.findViewById(R.id.itemTx) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseImageViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_choose_image, parent, false)

        return ChooseImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChooseImageViewHolder, position: Int) {
        val itemData = list[position]
        holder.itemName.text = itemData.nameOfImg
        holder.itemPic.setImageResource(itemData.pathToImage)
        try{
            holder.itemClick.setOnClickListener(onClickListener)
            holder.itemClick.tag = itemData.pathToImage
        }catch (e: Exception){
            Log.e("Error", e.message.toString())
        }
    }

    override fun getItemCount() = list.size

}