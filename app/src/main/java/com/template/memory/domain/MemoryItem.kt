package com.template.memory.domain

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import java.util.*

data class MemoryItem(
    val id: String = UUID.randomUUID().toString(),
    val isVisible: Boolean = true,
    @DrawableRes val imageRes: Int = 0,
    @StringRes val factRes: Int = 0,
    val isPlaceholder: Boolean = true,
    val isCenterView: Boolean = false
)