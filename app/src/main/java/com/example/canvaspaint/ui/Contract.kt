package com.example.canvaspaint.ui

import com.example.canvaspaint.base.Event
import com.example.canvaspaint.data.TOOLS
import com.example.canvaspaint.data.ToolItem

data class ViewState(
    val canvasViewState: CanvasViewState,

    //Рефакторинг под выбор стиля кисти для рисования
    val lineList: List<ToolItem.LineModel>,

    val sizeList: List<ToolItem.SizeModel>,
    val colorList: List<ToolItem.ColorModel>,
    val shapeList: List<ToolItem.ShapeModel>,
    val toolsList: List<ToolItem.ToolModel>,

    //Рефакторинг под выбор стиля кисти для рисования
    val isLineVisible: Boolean,

    val isBrushSizeChangerVisible: Boolean,
    val isPaletteVisible: Boolean,
    val isShapeVisible: Boolean,
    val isToolsVisible: Boolean
)

sealed class UIEvent : Event {
    //Рефакторинг под выбор стиля кисти для рисования
    data class OnLineTypeClicked(val index: Int) : UIEvent()

    data class OnSizeClicked(val index: Int) : UIEvent()
    data class OnPaletteClicked(val index: Int) : UIEvent()
    data class OnShapeClicked(val index: Int) : UIEvent()
    data class OnToolsClicked(val index: Int) : UIEvent()
    object OnDrawViewClicked : UIEvent()
    object OnToolbarClicked : UIEvent()
}

sealed class DataEvent : Event {
    data class OnSetDefaultTools(val tool: TOOLS) : DataEvent()
}