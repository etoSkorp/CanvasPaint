package com.example.canvaspaint.data

import androidx.annotation.DrawableRes
import com.example.canvaspaint.R

enum class LINE(
    @DrawableRes
    val value: Int
) {
    NORMAL(R.drawable.normal_line),
    DASH(R.drawable.dash_line);

    companion object {
        private val map = values().associateBy(LINE::value)
        fun from(line: Int) = map[line] ?: NORMAL
    }
}