package com.template.memory.domain

import androidx.annotation.DrawableRes
import com.template.R

enum class Shape(@DrawableRes val resId: Int) {
    COMPLETED(R.drawable.hexagon_gray),
    DEFAULT(R.drawable.hexagon_gold)
}