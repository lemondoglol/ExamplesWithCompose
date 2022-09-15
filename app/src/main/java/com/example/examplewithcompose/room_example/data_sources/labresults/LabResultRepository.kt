package com.example.examplewithcompose.room_example.data_sources.labresults

import com.example.examplewithcompose.room_example.data_sources.labresults.local_storage.LabResult
import kotlinx.coroutines.flow.StateFlow

interface LabResultRepository {
    val allLabResults: StateFlow<List<LabResult>>

    suspend fun putLabResult(labResult: LabResult)
}