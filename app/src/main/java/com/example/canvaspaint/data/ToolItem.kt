package com.example.canvaspaint.data

import androidx.annotation.ColorRes
import com.example.canvaspaint.base.Item

sealed class ToolItem : Item {
    //Рефакторинг под выбор стиля кисти для рисования
    data class LineModel(val lineType: Int) : ToolItem()

    data class SizeModel(val size: Int) : ToolItem()
    data class ColorModel(@ColorRes val color: Int) : ToolItem()
    data class ShapeModel(val shape: Int) : ToolItem()
    data class ToolModel(
        val type: TOOLS,
        val isSelected: Boolean = false,
        val selectedLine: LINE = LINE.NORMAL,
        val selectedSize: SIZE = SIZE.MEDIUM,
        val selectedColor: COLOR = COLOR.BLACK,
        val selectedShape: SHAPE = SHAPE.TRIANGLE
    ) : ToolItem()
}