package com.template.memory.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.google.android.material.snackbar.Snackbar
import com.template.R
import com.template.databinding.FragmentGameBinding
import com.template.memory.data.DataProvider
import com.template.memory.domain.MemoryColumn
import com.template.memory.domain.MemoryItem
import com.template.memory.ui.adapter.GameColumnAdapter
import com.template.memory.widget.GameItemSizeCalculator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class GameFragment : Fragment() {

    companion object {

        private const val EXTRA_GAME_MODE = "extra_game_mode"
        private const val WRONG_PARAM = -1

        private const val PERIOD_MILLIS = 1000L
        private const val TIMER_DELAY_MILLIS = 3000L
        private const val DELAY_HIDE_ITEMS = 1_000L

        fun getGameMode(value: Int) = GameFragment().apply {
            arguments = bundleOf(
                EXTRA_GAME_MODE to value
            )
        }

    }

    private val gameMode by lazy {
        arguments?.getInt(EXTRA_GAME_MODE, WRONG_PARAM)
    }

    private var bindingNullable: FragmentGameBinding? = null
    private val binding
        get() = bindingNullable!!

    private var passedSeconds = 0
    private var timer: Timer? = null
    private var content = emptyList<MemoryColumn>()
    private var gameAdapter: GameColumnAdapter? = null
    private var lastClickedItem: MemoryItem? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingNullable = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        content = DataProvider.provideColumnItems()
        gameAdapter = GameColumnAdapter(
            itemSizePixels = GameItemSizeCalculator.calculateSize(resources.displayMetrics),
            onClick = ::onItemClicked
        )

        binding.gameRecyclerView.apply {
            itemAnimator = null
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            adapter = gameAdapter
        }
        gameAdapter?.submitList(content)
    }

    private fun onItemClicked(item: MemoryItem) {
        lifecycleScope.launch {
            if (lastClickedItem == item) {
                return@launch
            }
            if (lastClickedItem == null) {
                updateItem(item, true, false)
                lastClickedItem = item
                return@launch
            }
            if (lastClickedItem?.imageRes == item.imageRes) {
                showSnackbar(item.factRes)
                updateItem(item, isVisible = true, isPlaceHolder = false)
                delay(DELAY_HIDE_ITEMS)
                lastClickedItem?.let(::updateItem)
                updateItem(item)
                lastClickedItem = null
            } else {
                updateItem(item, isVisible = true, isPlaceHolder = false)
                updateItem(lastClickedItem!!, isVisible = true, isPlaceHolder = true)
                lastClickedItem = item
            }
        }
    }

    private fun updateItem(
        item: MemoryItem,
        isVisible: Boolean = false,
        isPlaceHolder: Boolean = false
    ) {
        val columnIndex = content.indexOfFirst { it.id == item.parentId }
        val foundColumn = content[columnIndex]

        val newItems = foundColumn.items.toMutableList()
        val index = newItems.indexOfFirst { it.id == item.id }
        val itemModel = newItems[index]
        newItems[index] = itemModel.copy(isVisible = isVisible, isPlaceholder = isPlaceHolder)

        val newContent = content.toMutableList()
        newContent[columnIndex] = newContent[columnIndex].copy(items = newItems)
        updateData(newContent)
    }

    private fun updateData(updatedData: List<MemoryColumn>) {
        content = updatedData
        gameAdapter?.submitList(updatedData)
    }

    private fun navigateToNextScreen() {
        parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        timer?.cancel()
        val fragment = FinalFragment.getTimeFragment(passedSeconds, gameMode ?: WRONG_PARAM)
        parentFragmentManager.beginTransaction()
            .replace(R.id.frgView, fragment)
            .addToBackStack(FinalFragment.TAG)
            .commit()
    }

    override fun onResume() {
        super.onResume()
        timer = Timer()
        timer?.scheduleAtFixedRate(
            object : TimerTask() {
                override fun run() {
                    passedSeconds++
                }
            },
            TIMER_DELAY_MILLIS,
            PERIOD_MILLIS
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        timer?.cancel()
        bindingNullable = null
    }

    private fun showSnackbar(@StringRes textRes: Int) = Snackbar.make(
        requireView(),
        textRes,
        Snackbar.LENGTH_LONG
    ).show()

}