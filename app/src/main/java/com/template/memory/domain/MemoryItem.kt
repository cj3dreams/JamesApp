package com.template.memory.domain

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import java.util.*

data class MemoryItem(
    val id: String = UUID.randomUUID().toString(),
    @DrawableRes val imageRes: Int,
    @StringRes val factRes: Int,
    val shape: Shape? = Shape.DEFAULT,
    val isCenterView: Boolean = false,
    val parentId: String = ""
)