package com.template.memory.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.template.databinding.ItemGameColumnBinding
import com.template.memory.domain.MemoryColumn
import com.template.memory.domain.MemoryItem

class GameColumnAdapter(
    private val itemSizePixels: Int,
    private val onClick: (MemoryItem) -> Unit
) : ListAdapter<MemoryColumn, GameColumnAdapter.GameColumnViewHolder>(
    object : DiffUtil.ItemCallback<MemoryColumn>() {
        override fun areItemsTheSame(
            oldItem: MemoryColumn,
            newItem: MemoryColumn
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: MemoryColumn,
            newItem: MemoryColumn
        ): Boolean = oldItem == newItem
    }
) {

    companion object {

        private const val ITEM_OFFSET_PERCENTAGE_BY_ITEM = 0.25
        private const val ITEM_DIVIDER_PERCENTAGE_BY_ITEM = 0.1F

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GameColumnViewHolder = LayoutInflater.from(parent.context)
        .let { inflater -> ItemGameColumnBinding.inflate(inflater, parent as ViewGroup?, false) }
        .let { binding -> GameColumnViewHolder(binding, itemSizePixels, onClick) }

    override fun onBindViewHolder(
        holder: GameColumnViewHolder,
        position: Int
    ) = holder.bind(getItem(position))

    class GameColumnViewHolder(
        private val binding: ItemGameColumnBinding,
        private val itemSizePixels: Int,
        onClick: (MemoryItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private val gameItemAdapter = GameItemAdapter(onClick, itemSizePixels)

        init {
            binding.rvItems.apply {
                adapter = gameItemAdapter
                itemAnimator = null
            }
        }

        fun bind(item: MemoryColumn) = with(binding.root) {
            updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = item.offset.getPixels(resources.displayMetrics)
                if (item.showOffsetStart) {
                    marginStart =
                        -(itemSizePixels * (ITEM_OFFSET_PERCENTAGE_BY_ITEM - ITEM_DIVIDER_PERCENTAGE_BY_ITEM)).toInt()
                }
            }
            gameItemAdapter.submitList(item.items)
        }
    }
}