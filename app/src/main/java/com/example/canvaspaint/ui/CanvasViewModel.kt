package com.example.canvaspaint.ui

import com.example.canvaspaint.base.BaseViewModel
import com.example.canvaspaint.base.Event
import com.example.canvaspaint.data.*

class CanvasViewModel : BaseViewModel<ViewState>() {
    override fun initialViewState(): ViewState =
        ViewState(
            canvasViewState = CanvasViewState(
                //Рефакторинг под выбор стиля кисти для рисования
                line = LINE.NORMAL,

                size = SIZE.MEDIUM,
                color = COLOR.BLACK,
                shape = SHAPE.TRIANGLE,
                tools = TOOLS.NORMAL
                ),
            //Рефакторинг под выбор стиля кисти для рисования
            lineList = enumValues<LINE>().map { ToolItem.LineModel(it.value) },

            sizeList = enumValues<SIZE>().map { ToolItem.SizeModel(it.value) },
            colorList = enumValues<COLOR>().map { ToolItem.ColorModel(it.value) },
            shapeList = enumValues<SHAPE>().map { ToolItem.ShapeModel(it.value) },
            toolsList = enumValues<TOOLS>().map { ToolItem.ToolModel(it) },

            //Рефакторинг под выбор стиля кисти для рисования
            isLineVisible = false,

            isBrushSizeChangerVisible = false,
            isPaletteVisible = false,
            isShapeVisible = false,
            isToolsVisible = false
            )

    init {
        processDataEvent(DataEvent.OnSetDefaultTools(tool = TOOLS.NORMAL))
    }

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UIEvent.OnToolbarClicked -> {
                return previousState.copy(
                    isToolsVisible = !previousState.isToolsVisible,
                    isLineVisible = false,
                    isBrushSizeChangerVisible = false,
                    isPaletteVisible = false,
                    isShapeVisible = false
                )
            }

            is UIEvent.OnToolsClicked -> {
                when (event.index) {
                    TOOLS.PALETTE.ordinal -> {
                        return previousState.copy(
                            isPaletteVisible = !previousState.isPaletteVisible,
                            isLineVisible = false,
                            isBrushSizeChangerVisible = false,
                            isShapeVisible = false
                        )
                    }

                    TOOLS.SHAPE.ordinal -> {
                        return previousState.copy(
                            isShapeVisible = !previousState.isShapeVisible,
                            isLineVisible = false,
                            isBrushSizeChangerVisible = false,
                            isPaletteVisible = false,
                        )
                    }

                    TOOLS.SIZE.ordinal -> {
                        return previousState.copy(
                            isBrushSizeChangerVisible = !previousState.isBrushSizeChangerVisible,
                            isPaletteVisible = false,
                            isShapeVisible = false,
                            isLineVisible = false
                        )
                    }

                    TOOLS.LINE.ordinal -> {
                        return previousState.copy(
                            isLineVisible = !previousState.isLineVisible,
                            isBrushSizeChangerVisible = false,
                            isPaletteVisible = false,
                            isShapeVisible = false
                        )
                    }

                    else -> {
                        val toolsList = previousState.toolsList.mapIndexed { index, toolModel ->
                            if (index == event.index) {
                                toolModel.copy(isSelected = true)
                            } else {
                                toolModel.copy(isSelected = false)
                            }
                        }
                        return previousState.copy(
                            toolsList = toolsList,
                            isLineVisible = false,
                            isBrushSizeChangerVisible = false,
                            isPaletteVisible = false,
                            isShapeVisible = false,
                            canvasViewState = previousState.canvasViewState.copy(tools = TOOLS.values()[event.index])
                        )
                    }
                }
            }

            is UIEvent.OnLineTypeClicked -> {
                val selectedLine = LINE.values()[event.index]
                val toolsList = previousState.toolsList.map {
                    if (it.type == TOOLS.LINE) {
                        it.copy(selectedLine = selectedLine)
                    } else {
                        it
                    }
                }
                return previousState.copy(
                    toolsList = toolsList,
                    isLineVisible = !previousState.isLineVisible,
                    canvasViewState = previousState.canvasViewState.copy(line = selectedLine)
                )
            }

            is UIEvent.OnSizeClicked -> {
                val selectedSize = SIZE.values()[event.index]
                val toolsList = previousState.toolsList.map {
                    if (it.type == TOOLS.SIZE) {
                        it.copy(selectedSize = selectedSize)
                    } else {
                        it
                    }
                }
                return previousState.copy(
                    toolsList = toolsList,
                    isBrushSizeChangerVisible = !previousState.isBrushSizeChangerVisible,
                    canvasViewState = previousState.canvasViewState.copy(size = selectedSize)
                )
            }

            is UIEvent.OnPaletteClicked -> {
                val selectedColor = COLOR.values()[event.index]
                val toolsList = previousState.toolsList.map {
                    if (it.type == TOOLS.PALETTE) {
                        it.copy(selectedColor = selectedColor)
                    } else {
                        it
                    }
                }
                return previousState.copy(
                    toolsList = toolsList,
                    isPaletteVisible = !previousState.isPaletteVisible,
                    canvasViewState = previousState.canvasViewState.copy(color = selectedColor)
                )
            }

            is UIEvent.OnShapeClicked -> {
                val selectedShape = SHAPE.values()[event.index]
                val toolsList = previousState.toolsList.map {
                    if (it.type == TOOLS.SHAPE) {
                        it.copy(selectedShape = selectedShape)
                    } else {
                        it
                    }
                }
                return previousState.copy(
                    toolsList = toolsList,
                    isShapeVisible = !previousState.isShapeVisible,
                    canvasViewState = previousState.canvasViewState.copy(shape = selectedShape)
                )
            }

            is UIEvent.OnDrawViewClicked -> {
                return previousState.copy(
                    isLineVisible = false,
                    isBrushSizeChangerVisible = false,
                    isPaletteVisible = false,
                    isShapeVisible = false,
                    isToolsVisible = false
                )
            }

            is DataEvent.OnSetDefaultTools -> {
                val toolsList = previousState.toolsList.map { toolModel ->
                    if (toolModel.type == event.tool) {
                        toolModel.copy(isSelected = true)
                    } else {
                        toolModel.copy(isSelected = false)
                    }
                }
                return previousState.copy(toolsList = toolsList)
            }
            else -> return null
        }
    }
}