package com.lwbd.lwbdpoc.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.launch

abstract class BaseViewModel<I : BaseIntent, S : BaseState> : ViewModel() {

    // Mutable StateFlow to hold the state, starting with the initial state
    protected val tempState: MutableStateFlow<S> by lazy { MutableStateFlow(initialState()) }
    val state: StateFlow<S> = tempState


    // Abstract function to provide initial state for the feature
    abstract fun initialState(): S
//
    // Abstract function to handle intents
    abstract fun handleIntent(intent: I)

    // Process the received intent by launching a coroutine
    open fun processIntent(intent: I) {
        viewModelScope.launch {
            handleIntent(intent)
        }
    }

    // Function to update the state
    protected fun setState(newState: S) {
        tempState.value = newState
    }

    // Optional: override to handle clearing up resources
    override fun onCleared() {
        super.onCleared()
        // Add any additional cleanup if needed
    }
}