package com.example.examplewithcompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(

) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    internal val isLoading: StateFlow<Boolean> = _isLoading

    private val _isEligible = MutableStateFlow(false)
    internal val isEligible: StateFlow<Boolean> = _isEligible

    // combine 2 flows into 1
    internal val canAllowProcess: StateFlow<Boolean> = combine(
        _isLoading, _isEligible
    ) { arg1, arg2 ->
        arg1 && arg2
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

}