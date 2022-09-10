package com.example.examplewithcompose

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examplewithcompose.data.repository.PreferenceDataStoreRepository
import com.example.examplewithcompose.retrofit_example.RetrofitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val preferenceDataStoreRepository: PreferenceDataStoreRepository,
    private val retrofitRepository: RetrofitRepository,
) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            preferenceDataStoreRepository.writeToPreferenceStore()

            // testing retrofit
            val res = retrofitRepository.getUser1()
//            Log.d("Tuna", "Result: id ${res?.id} body ${res?.body}")
        }
    }

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