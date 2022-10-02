package com.template.memory.domain

import java.util.*

data class MemoryColumn(
    val id: String = UUID.randomUUID().toString(),
    val items: List<MemoryItem>,
    val offset: Offset,
    val showOffsetStart: Boolean = true
)