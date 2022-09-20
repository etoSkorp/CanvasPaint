package com.example.canvaspaint.data

import androidx.annotation.DrawableRes
import com.example.canvaspaint.R

enum class TOOLS(
    @DrawableRes
    val value: Int
) {
    NORMAL(R.drawable.normal_line),
    DASH(R.drawable.dash_line),
    SIZE(R.drawable.ic_baseline_scatter_plot_24),
    PALETTE(R.drawable.circle_filled),
    SHAPE(R.drawable.triangle),
    //Рефакторинг под выбор стиля кисти для рисования
    LINE(R.drawable.normal_line);
}