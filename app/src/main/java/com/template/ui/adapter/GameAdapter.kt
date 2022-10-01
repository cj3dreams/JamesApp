package com.template.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.template.R

class GameAdapter(private val context: Context, private val list: List<String>,
    private val onClickListener: View.OnClickListener): RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    class GameViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val itemClick = view.findViewById(R.id.itemClick) as CardView
        val itemTx = view.findViewById(R.id.itemTx) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_game_card, parent, false)

        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val itemData = list[position]
        if (itemData.contains("hide")) holder.itemClick.background =
            AppCompatResources.getDrawable(context, android.R.color.transparent)
        else {
            holder.itemTx.text = itemData
            try {
                holder.itemClick.setOnClickListener(onClickListener)
                holder.itemClick.tag = position
            } catch (e: Exception) {
                Log.e("Game Adapter Error", e.message.toString())
            }
        }
    }

    override fun getItemCount() = list.size
}