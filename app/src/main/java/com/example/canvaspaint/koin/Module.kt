package com.example.canvaspaint.koin

import com.example.canvaspaint.ui.CanvasViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        CanvasViewModel()
    }
}