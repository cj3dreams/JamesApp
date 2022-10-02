package com.template.memory.data

import com.template.R
import com.template.memory.domain.MemoryColumn
import com.template.memory.domain.MemoryItem
import com.template.memory.domain.Offset

object DataProvider {

    private val shuffledItems = getAllItems().shuffled()

    fun provideColumnItems(): List<MemoryColumn> = listOf(
        MemoryColumn(
            items = getFirstColumnItems(),
            offset = Offset.BIG,
            showOffsetStart = false
        ),
        MemoryColumn(
            items = getSecondColumnItems(),
            offset = Offset.REGULAR
        ),
        MemoryColumn(
            items = getThirdColumnItems(),
            offset = Offset.ZERO
        ),
        MemoryColumn(
            items = getFourthColumnItems(),
            offset = Offset.REGULAR
        ),
        MemoryColumn(
            items = getFifthColumnItems(),
            offset = Offset.BIG
        ),
    )

    private fun getFirstColumnItems(): List<MemoryItem> = shuffledItems.subList(0, 3)

    private fun getSecondColumnItems(): List<MemoryItem> = shuffledItems.subList(3, 7)

    private fun getThirdColumnItems(): List<MemoryItem> = shuffledItems.subList(7, 11)
        .toMutableList()
        .apply { add(2, MemoryItem(isCenterView = true)) }

    private fun getFourthColumnItems(): List<MemoryItem> = shuffledItems.subList(11, 15)

    private fun getFifthColumnItems(): List<MemoryItem> = shuffledItems.subList(15, 18)

    private fun getAllItems(): List<MemoryItem> = listOf(
        MemoryItem(
            imageRes = R.drawable.james_webb_telescope,
            factRes = R.string.fact_webbs_telescope
        ),
        MemoryItem(
            imageRes = R.drawable.james_webb_telescope,
            factRes = R.string.fact_webbs_telescope
        ),
        MemoryItem(
            imageRes = R.drawable.james_webb_telescope,
            factRes = R.string.fact_webbs_telescope
        ),
        MemoryItem(
            imageRes = R.drawable.james_webb_telescope,
            factRes = R.string.fact_webbs_telescope
        ),
        MemoryItem(
            imageRes = R.drawable.james_webb_telescope,
            factRes = R.string.fact_webbs_telescope
        ),
        MemoryItem(
            imageRes = R.drawable.james_webb_telescope,
            factRes = R.string.fact_webbs_telescope
        ),
        MemoryItem(
            imageRes = R.drawable.james_webb_telescope,
            factRes = R.string.fact_webbs_telescope
        ),
        MemoryItem(
            imageRes = R.drawable.james_webb_telescope,
            factRes = R.string.fact_webbs_telescope
        ),
        MemoryItem(
            imageRes = R.drawable.james_webb_telescope,
            factRes = R.string.fact_webbs_telescope
        ),
        MemoryItem(
            imageRes = R.drawable.james_webb_telescope,
            factRes = R.string.fact_webbs_telescope
        ),
        MemoryItem(
            imageRes = R.drawable.james_webb_telescope,
            factRes = R.string.fact_webbs_telescope
        ),
        MemoryItem(
            imageRes = R.drawable.james_webb_telescope,
            factRes = R.string.fact_webbs_telescope
        ),
        MemoryItem(
            imageRes = R.drawable.james_webb_telescope,
            factRes = R.string.fact_webbs_telescope
        ),
        MemoryItem(
            imageRes = R.drawable.james_webb_telescope,
            factRes = R.string.fact_webbs_telescope
        ),
        MemoryItem(
            imageRes = R.drawable.james_webb_telescope,
            factRes = R.string.fact_webbs_telescope
        ),
        MemoryItem(
            imageRes = R.drawable.james_webb_telescope,
            factRes = R.string.fact_webbs_telescope
        ),
        MemoryItem(
            imageRes = R.drawable.james_webb_telescope,
            factRes = R.string.fact_webbs_telescope
        ),
        MemoryItem(
            imageRes = R.drawable.james_webb_telescope,
            factRes = R.string.fact_webbs_telescope
        )
    )

}