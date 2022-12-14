package com.template.memory.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.template.databinding.ItemGameCardBinding
import com.template.memory.domain.MemoryItem
import com.template.memory.domain.Shape

class GameItemAdapter(
    private val onClick: (MemoryItem) -> Unit,
    private val itemSizePixels: Int
) : ListAdapter<MemoryItem, GameItemAdapter.GameViewHolder>(
    object : DiffUtil.ItemCallback<MemoryItem>() {
        override fun areItemsTheSame(
            oldItem: MemoryItem,
            newItem: MemoryItem
        ): Boolean = oldItem.factRes == newItem.factRes

        override fun areContentsTheSame(
            oldItem: MemoryItem,
            newItem: MemoryItem
        ): Boolean = oldItem == newItem
    }
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GameViewHolder = LayoutInflater.from(parent.context)
        .let { inflater -> ItemGameCardBinding.inflate(inflater, parent as ViewGroup?, false) }
        .let { binding -> GameViewHolder(binding, onClick, itemSizePixels) }

    override fun onBindViewHolder(
        holder: GameViewHolder,
        position: Int
    ) = holder.bind(getItem(position))

    class GameViewHolder(
        private val binding: ItemGameCardBinding,
        private val onClick: (MemoryItem) -> Unit,
        private val itemSizePixels: Int
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MemoryItem) {
            with(binding) {
                setSize()
                setImage(item)
                root.setOnClickListener {
                    if (!item.isCenterView && item.shape == Shape.DEFAULT) {
                        onClick(item)
                    }
                }
                if (item.isCenterView) {
                    tvTitle.isVisible = true
                    ivContent.isVisible = false
                }
            }
        }

        private fun setSize() {
            itemView.layoutParams = itemView.layoutParams.apply {
                height = itemSizePixels
                width = itemSizePixels
            }
        }

        private fun setImage(item: MemoryItem) {
            val data = when (item.shape) {
                Shape.COMPLETED, Shape.DEFAULT -> item.shape.resId
                null -> item.imageRes
            }
            binding.ivContent.load(data)
        }
    }
}