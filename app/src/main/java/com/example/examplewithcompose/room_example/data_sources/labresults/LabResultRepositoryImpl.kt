package com.example.examplewithcompose.room_example.data_sources.labresults

import com.example.examplewithcompose.room_example.data_sources.labresults.local_storage.LabResult
import com.example.examplewithcompose.room_example.data_sources.labresults.local_storage.LabResultDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LabResultRepositoryImpl @Inject constructor(
    private val labResultDao: LabResultDao,
) : LabResultRepository {

    // testing only, should inject this
    private val myScope = CoroutineScope(Dispatchers.IO)

    override val allLabResults: StateFlow<List<LabResult>> =
        labResultDao.getAllLabResults().stateIn(
            myScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )

    override suspend fun putLabResult(labResult: LabResult) = withContext(Dispatchers.IO) {
        labResultDao.put(labResult)
    }
}