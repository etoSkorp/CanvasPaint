package com.example.canvaspaint.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.canvaspaint.base.Event

abstract class BaseViewModel<VIEW_STATE> : ViewModel() {

    val viewState: MutableLiveData<VIEW_STATE> by lazy { MutableLiveData(initialViewState()) }

    abstract fun initialViewState(): VIEW_STATE

    abstract fun reduce(event: Event, previousState: VIEW_STATE): VIEW_STATE?

    fun processUIEvent(event: Event) {
        updateState(event)
    }

    fun processDataEvent(event: Event) {
        updateState(event)
    }

    private fun updateState(event: Event) {
        val newViewState = reduce(event, viewState.value ?: initialViewState())
        if (newViewState != null && newViewState != viewState.value) {
            viewState.value = newViewState
        }
    }
}