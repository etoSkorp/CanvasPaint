package com.example.canvaspaint

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.canvaspaint.ui.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    companion object {
        //Рефакторинг под выбор стиля кисти для рисования
        private const val LINE_VIEW = 0

        private const val SIZE_VIEW = 1
        private const val PALETTE_VIEW = 2
        private const val SHAPE_VIEW = 3
        private const val TOOLS_VIEW = 4
    }

    private val viewModel: CanvasViewModel by viewModel()

    private var toolsList: List<ToolsLayout> = listOf()

    //Рефакторинг под выбор стиля кисти для рисования
    private val lineLayout: ToolsLayout by lazy { findViewById(R.id.lineLayout) }

    private val brushSizeLayout: ToolsLayout by lazy { findViewById(R.id.brushSizeLayout) }
    private val paletteLayout: ToolsLayout by lazy { findViewById(R.id.paletteLayout) }
    private val shapeLayout: ToolsLayout by lazy { findViewById(R.id.shapeLayout) }
    private val toolsLayout: ToolsLayout by lazy { findViewById(R.id.toolsLayout) }

    private val ivToolbarBrush: ImageView by lazy { findViewById(R.id.ivToolbarBrush) }
    private val ivToolbarClear: ImageView by lazy { findViewById(R.id.ivToolbarClear) }
    private val drawView: DrawView by lazy { findViewById(R.id.drawView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Рефакторинг под выбор стиля кисти для рисования
        toolsList = listOf(lineLayout, brushSizeLayout, paletteLayout, shapeLayout,  toolsLayout)
        viewModel.viewState.observe(this, ::render)

        ivToolbarBrush.setOnClickListener {
            viewModel.processUIEvent(UIEvent.OnToolbarClicked)
        }

        //Рефакторинг под выбор стиля кисти для рисования
        lineLayout.setOnClickListener {
            viewModel.processUIEvent(UIEvent.OnLineTypeClicked(it))
        }

        brushSizeLayout.setOnClickListener {
            viewModel.processUIEvent(UIEvent.OnSizeClicked(it))
        }

        paletteLayout.setOnClickListener {
            viewModel.processUIEvent(UIEvent.OnPaletteClicked(it))
        }

        shapeLayout.setOnClickListener {
            viewModel.processUIEvent(UIEvent.OnShapeClicked(it))
        }

        toolsLayout.setOnClickListener {
            viewModel.processUIEvent(UIEvent.OnToolsClicked(it))
        }

        ivToolbarClear.setOnClickListener {
            drawView.clear()
        }

        drawView.setOnClickField {
            viewModel.processUIEvent(UIEvent.OnDrawViewClicked)
        }
    }

    private fun render(viewState: ViewState) {

        //Рефакторинг под выбор стиля кисти для рисования
        with(toolsList[LINE_VIEW]) {
            render(viewState.lineList)
            isVisible = viewState.isLineVisible
        }

        with(toolsList[SIZE_VIEW]) {
            render(viewState.sizeList)
            isVisible = viewState.isBrushSizeChangerVisible
        }

        with(toolsList[PALETTE_VIEW]) {
            render(viewState.colorList)
            isVisible = viewState.isPaletteVisible
        }

        with(toolsList[SHAPE_VIEW]) {
            render(viewState.shapeList)
            isVisible = viewState.isShapeVisible
        }

        with(toolsList[TOOLS_VIEW]) {
            render(viewState.toolsList)
            isVisible = viewState.isToolsVisible
        }

        drawView.render(viewState.canvasViewState)
    }
}