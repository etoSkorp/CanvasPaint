package com.example.canvaspaint.ui

import com.example.canvaspaint.data.*

data class CanvasViewState(
    //Рефакторинг под выбор стиля кисти для рисования
    val line: LINE,

    val size: SIZE,
    val color: COLOR,
    val shape: SHAPE,
    val tools: TOOLS
    )





