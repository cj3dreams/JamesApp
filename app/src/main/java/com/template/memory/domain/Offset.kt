package com.template.memory.domain

import android.util.DisplayMetrics
import com.template.memory.widget.GameItemSizeCalculator

enum class Offset(private val value: Float) {
    BIG(1F),
    REGULAR(0.5F),
    ZERO(0F);

    fun getPixels(displayMetrics: DisplayMetrics): Int =
        (GameItemSizeCalculator.calculateSize(displayMetrics) * value).toInt()
}