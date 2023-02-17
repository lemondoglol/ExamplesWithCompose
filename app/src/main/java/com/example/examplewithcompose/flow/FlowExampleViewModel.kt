package com.example.examplewithcompose.flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class FlowExampleViewModel @Inject constructor(
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    private val _isEligible = MutableStateFlow(false)

    // combine 2 flows into 1
    internal val canAllowProcess: StateFlow<String> = combine(
        _isLoading, _isEligible
    ) { arg1, arg2 ->
        "isLoading: $arg1; isEligible: $arg2"
    }.stateIn(
        scope = viewModelScope, // how long this variable should survive
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = "not set yet"
    )

    internal fun updateIsLoading() {
        _isLoading.value = !_isLoading.value
    }

    internal fun updateIsEligible() {
        _isEligible.value = !_isEligible.value
    }
}