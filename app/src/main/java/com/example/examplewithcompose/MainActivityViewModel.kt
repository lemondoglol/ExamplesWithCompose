package com.example.examplewithcompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examplewithcompose.data.repository.PreferenceDataStoreRepository
import com.example.examplewithcompose.retrofit_example.RetrofitRepository
import com.example.examplewithcompose.room_example.data_sources.labresults.LabResultRepository
import com.example.examplewithcompose.room_example.data_sources.labresults.local_storage.LabResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val preferenceDataStoreRepository: PreferenceDataStoreRepository,
    private val retrofitRepository: RetrofitRepository,
    private val labResultRepository: LabResultRepository,
) : ViewModel() {

    private val _labResults = MutableStateFlow<List<LabResult>>(emptyList())
    internal val labResults = _labResults.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            testingRoom()
        }
    }

    private suspend fun testingRoom() {
        labResultRepository.putLabResult(
            LabResult(labResultId = "1", labResultTitle = "title 1", labResultContent = "content 1")
        )
        labResultRepository.putLabResult(
            LabResult(labResultId = "2", labResultTitle = "title 2", labResultContent = "content 2")
        )

        labResultRepository.allLabResults.collect {
            _labResults.value = it
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