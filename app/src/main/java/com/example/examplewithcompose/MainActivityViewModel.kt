package com.example.examplewithcompose

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val _sharedFlow = MutableSharedFlow<Int>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    init {
        // collectors
        viewModelScope.launch {
            sharedFlow.collect {
                delay(2000)
                Log.d("Tuna", "$it")
            }
        }
        viewModelScope.launch {
            sharedFlow.collect {
                delay(3000)
                Log.d("Tuna", "$it")
            }
        }

        squareNumber(5)
    }

    private fun squareNumber(num: Int) {
        viewModelScope.launch {
            _sharedFlow.emit(num * num)
        }
    }

}