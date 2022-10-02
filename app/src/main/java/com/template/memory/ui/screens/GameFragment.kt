package com.template.memory.ui.screens

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        gameAdapter = GameColumnAdapter(
            itemSizePixels = GameItemSizeCalculator.calculateSize(resources.displayMetrics),
            onClick = ::onItemClicked
        )

        binding.gameRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            adapter = gameAdapter
        }
        gameAdapter?.submitList(DataProvider.provideColumnItems())

        countTimer = object : CountDownTimer(TIMER_DELAY_MILLIS, PERIOD_MILLIS) {
            override fun onTick(p0: Long) {}

            override fun onFinish() {
                cancel()
            }
        }
        countTimer?.start()
    }

    private fun onItemClicked(item: MemoryItem) {
        content.map { column ->
            val index = column.items.indexOfFirst { it.id == item.id }
            if (index != WRONG_PARAM) {
                val newItems = column.items.toMutableList()
                newItems[index] = column.items[index].copy(
                    isPlaceholder = false
                )
                column.copy(items = newItems)
            } else {
                column
            }
        }.let { gameAdapter?.submitList(it) }
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

    private fun snackBar(text: String) = Snackbar.make(requireView(), text, 1200).show()

}