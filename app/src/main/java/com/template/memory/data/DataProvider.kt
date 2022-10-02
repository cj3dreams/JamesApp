package com.template.memory.data

import com.template.R
import com.template.memory.domain.MemoryColumn
import com.template.memory.domain.MemoryItem
import com.template.memory.domain.Offset
import java.util.*

object DataProvider {

    private val shuffledItems = getAllItems().shuffled()

    fun provideColumnItems(): List<MemoryColumn> = listOf(
        createItem(::getFirstColumnItems, Offset.BIG, true),
        createItem(::getSecondColumnItems, Offset.REGULAR),
        createItem(::getThirdColumnItems, Offset.ZERO),
        createItem(::getFourthColumnItems, Offset.REGULAR),
        createItem(::getFifthColumnItems, Offset.BIG),
    )

    private fun createItem(
        items: (String) -> List<MemoryItem>,
        offset: Offset,
        showOffsetStart: Boolean = false
    ): MemoryColumn {
        val id = UUID.randomUUID().toString()
        return MemoryColumn(
            id = id,
            items = items(id),
            offset = offset,
            showOffsetStart = showOffsetStart
        )
    }

    private fun getFirstColumnItems(id: String): List<MemoryItem> = shuffledItems.subList(0, 3)
        .map { it.copy(parentId = id) }

    private fun getSecondColumnItems(id: String): List<MemoryItem> = shuffledItems.subList(3, 7)
        .map { it.copy(parentId = id) }

    private fun getThirdColumnItems(id: String): List<MemoryItem> = shuffledItems.subList(7, 11)
        .toMutableList()
        .apply { add(2, MemoryItem(isCenterView = true)) }
        .map { it.copy(parentId = id) }

    private fun getFourthColumnItems(id: String): List<MemoryItem> = shuffledItems.subList(11, 15)
        .map { it.copy(parentId = id) }

    private fun getFifthColumnItems(id: String): List<MemoryItem> = shuffledItems.subList(15, 18)
        .map { it.copy(parentId = id) }

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