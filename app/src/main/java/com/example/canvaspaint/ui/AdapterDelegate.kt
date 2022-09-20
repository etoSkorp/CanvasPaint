package com.example.canvaspaint.ui

import android.graphics.PorterDuff
import android.widget.ImageView
import android.widget.TextView
import com.example.canvaspaint.R
import com.example.canvaspaint.base.Item
import com.example.canvaspaint.data.TOOLS
import com.example.canvaspaint.data.ToolItem
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer

fun sizeChangeAdapterDelegate(
    onSizeClick: (Int) -> Unit
): AdapterDelegate<List<Item>> =
    adapterDelegateLayoutContainer<ToolItem.SizeModel, Item>(
        R.layout.item_size
    ) {
        val tvTextSize: TextView = findViewById(R.id.tvBrushSize)
        itemView.setOnClickListener { onSizeClick(adapterPosition) }
        bind { list ->
            tvTextSize.text = item.size.toString()
            itemView.setBackgroundResource(R.drawable.tool_size_item)
            when (item.size) {
                4 -> {
                    tvTextSize.textSize = 12.toFloat()
                }
                16 -> {
                    tvTextSize.textSize = 14.toFloat()
                }
                32 -> {
                    tvTextSize.textSize = 17.toFloat()
                }
            }
        }
    }

fun colorAdapterDelegate(
    onClick: (Int) -> Unit
): AdapterDelegate<List<Item>> =
    adapterDelegateLayoutContainer<ToolItem.ColorModel, Item>(
        R.layout.item_palette
    ) {
        val color: ImageView = findViewById(R.id.color)
        itemView.setOnClickListener { onClick(adapterPosition) }
        bind { list ->
            color.setColorFilter(
                context.resources.getColor(item.color, null),
                PorterDuff.Mode.SRC_IN
            )
        }
    }

fun shapeAdapterDelegate(
    onClick: (Int) -> Unit
): AdapterDelegate<List<Item>> =
    adapterDelegateLayoutContainer<ToolItem.ShapeModel, Item>(
        R.layout.item_shape
    ) {
        val shape: ImageView = findViewById(R.id.ivShape)
        itemView.setOnClickListener { onClick(adapterPosition) }
        bind { list ->
            shape.setImageResource(item.shape)
        }
    }

fun lineAdapterDelegate(
    onClick: (Int) -> Unit
): AdapterDelegate<List<Item>> =
    adapterDelegateLayoutContainer<ToolItem.LineModel, Item>(
        R.layout.item_tools
    ) {
        val line: ImageView = findViewById(R.id.ivTool)
        itemView.setOnClickListener { onClick(adapterPosition) }
        bind { list ->
            line.setImageResource(item.lineType)
        }
    }

fun toolsAdapterDelegate(
    onToolsClick: (Int) -> Unit
): AdapterDelegate<List<Item>> =
    adapterDelegateLayoutContainer<ToolItem.ToolModel, Item>(
        R.layout.item_tools
    ) {
        val ivTool: ImageView by lazy { findViewById(R.id.ivTool) }

        bind { list ->
            ivTool.setImageResource(item.type.value)

            when (item.type) {
                TOOLS.SIZE -> {
                    ivTool.setImageResource(R.drawable.ic_baseline_scatter_plot_24)
                }

                TOOLS.PALETTE -> {
                    ivTool.setColorFilter(
                        context.resources.getColor(item.selectedColor.value, null),
                        PorterDuff.Mode.SRC_IN
                    )
                }

                TOOLS.SHAPE -> {
                    ivTool.setImageResource(item.selectedShape.value)
                }
                
                TOOLS.LINE -> {
                    ivTool.setImageResource(item.selectedLine.value)
                }

                else -> {
                    if (item.isSelected) {
                        ivTool.setBackgroundResource(R.drawable.tools_selected)
                    } else {
                        ivTool.setBackgroundResource(R.color.transparent)
                    }
                }
            }

            itemView.setOnClickListener {
                onToolsClick(adapterPosition)
            }
        }
    }