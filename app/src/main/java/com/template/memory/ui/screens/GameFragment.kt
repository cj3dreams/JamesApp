package com.template.memory.ui.screens

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
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
import java.util.*

class GameFragment : Fragment() {

    companion object {

        private const val EXTRA_GAME_MODE = "extra_game_mode"
        private const val WRONG_PARAM = -1

        private const val PERIOD_MILLIS = 1000L
        private const val TIMER_DELAY_MILLIS = 3000L

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
    private var countTimer: CountDownTimer? = null
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

        countTimer = object : CountDownTimer(TIMER_DELAY_MILLIS, PERIOD_MILLIS) {
            override fun onTick(p0: Long) {}

            override fun onFinish() {
                cancel()
            }
        }
        countTimer?.start()
    }

    private fun onItemClicked(item: MemoryItem) {
        val isVisible = lastClickedItem == null || lastClickedItem?.imageRes != item.imageRes
        updateItem(item, isVisible)
        lastClickedItem = if (!isVisible) {
            lastClickedItem?.let(::updateItem)
            null
        } else {
            startGuessTimer()
            item
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
        newItems[index] = item.copy(isVisible = isVisible, isPlaceholder = isPlaceHolder)

        val newContent = content.toMutableList()
        newContent[columnIndex] = content[columnIndex].copy(items = newItems)
        updateData(newContent)
    }

    private fun startGuessTimer() {

    }

    private fun updateData(updatedData: List<MemoryColumn>) {
        content = updatedData
        gameAdapter?.submitList(updatedData)
    }

    private fun updateLastClickedItem() {
        var column: MemoryColumn? = null
        val updatedColumn = content.find { data ->
            data.items.find { it.id == lastClickedItem?.id } != null
        }?.let { columnWithItem ->
            column = columnWithItem
            val items = columnWithItem.items
            val newItems = items.toMutableList()
            val index = newItems.indexOf(lastClickedItem)
            lastClickedItem?.copy(isVisible = false)?.let { newItems[index] = it }
            columnWithItem.copy(items = newItems)
        }

        updatedColumn?.let { newColumn ->
            val index = content.indexOf(column)
            content.toMutableList().apply {
                set(index, newColumn)
            }
        }?.let(::updateData)
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