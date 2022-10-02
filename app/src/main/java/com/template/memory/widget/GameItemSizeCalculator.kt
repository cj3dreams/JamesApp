package com.template.memory.widget

import android.util.DisplayMetrics

object GameItemSizeCalculator {

    private const val MARGIN_HORIZONTAL_DP = 20

    private const val COLUMNS_DIVIDER = 4.4F

    fun calculateSize(displayMetrics: DisplayMetrics): Int {
        val density = displayMetrics.density
        val screenWidthDp = displayMetrics.widthPixels / density
        val widthWithoutMargin = screenWidthDp - MARGIN_HORIZONTAL_DP
        val sizeDp = widthWithoutMargin / COLUMNS_DIVIDER
        return (sizeDp * density).toInt()
    }

}