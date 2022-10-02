package com.template.memory.data

import com.template.R
import com.template.memory.domain.MemoryColumn
import com.template.memory.domain.MemoryItem
import com.template.memory.domain.Offset
import java.util.*

object DataProvider {

    private val shuffledItems by lazy {
        val items = getAllItems().shuffled().take(9)
        (items + items).shuffled()
            .map { it.copy(id = UUID.randomUUID().toString()) }
    }

    fun provideColumnItems(): List<MemoryColumn> = listOf(
        createItem(::getFirstColumnItems, Offset.BIG, false),
        createItem(::getSecondColumnItems, Offset.REGULAR),
        createItem(::getThirdColumnItems, Offset.ZERO),
        createItem(::getFourthColumnItems, Offset.REGULAR),
        createItem(::getFifthColumnItems, Offset.BIG),
    )

    private fun createItem(
        items: (String) -> List<MemoryItem>,
        offset: Offset,
        showOffsetStart: Boolean = true
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
        .apply {
            add(
                2,
                MemoryItem(
                    isCenterView = true,
                    factRes = R.string.app_name, // stub
                    imageRes = R.drawable.sun // stub
                )
            )
        }
        .map { it.copy(parentId = id) }

    private fun getFourthColumnItems(id: String): List<MemoryItem> = shuffledItems.subList(11, 15)
        .map { it.copy(parentId = id) }

    private fun getFifthColumnItems(id: String): List<MemoryItem> = shuffledItems.subList(15, 18)
        .map { it.copy(parentId = id) }

    private fun getAllItems(): List<MemoryItem> = listOf(
        MemoryItem(
            imageRes = R.drawable.memory1,
            factRes = R.string.memory1
        ),
        MemoryItem(
            imageRes = R.drawable.memory2,
            factRes = R.string.memory2
        ),
        MemoryItem(
            imageRes = R.drawable.memory3,
            factRes = R.string.memory3
        ),
        MemoryItem(
            imageRes = R.drawable.memory4,
            factRes = R.string.memory4
        ),
        MemoryItem(
            imageRes = R.drawable.memory5,
            factRes = R.string.memory5
        ),
        MemoryItem(
            imageRes = R.drawable.memory6,
            factRes = R.string.memory6
        ),
        MemoryItem(
            imageRes = R.drawable.memory7,
            factRes = R.string.memory7
        ),
        MemoryItem(
            imageRes = R.drawable.memory8,
            factRes = R.string.memory8
        ),
        MemoryItem(
            imageRes = R.drawable.memory9,
            factRes = R.string.memory9
        ),
        MemoryItem(
            imageRes = R.drawable.memory10,
            factRes = R.string.memory10
        ),
        MemoryItem(
            imageRes = R.drawable.memory11,
            factRes = R.string.memory11
        ),
        MemoryItem(
            imageRes = R.drawable.memory12,
            factRes = R.string.memory12
        ),
        MemoryItem(
            imageRes = R.drawable.memory13,
            factRes = R.string.memory13
        ),
        MemoryItem(
            imageRes = R.drawable.memory14,
            factRes = R.string.memory14
        )
    )
}