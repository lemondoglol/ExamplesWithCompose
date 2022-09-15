package com.example.examplewithcompose.room_example.data_sources.labresults.local_storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LabResultDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun put(labResult: LabResult)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putAll(labResults: List<LabResult>)

    // note: no suspend here when returning Flow
    @Query("Select * From $LAB_RESULT_TABLE_NAME Where labResultId=:labResultId")
    fun get(labResultId: String): Flow<List<LabResult>>

    // note: no suspend here when returning Flow
    @Query("Select * From $LAB_RESULT_TABLE_NAME")
    fun getAllLabResults(): Flow<List<LabResult>>

    @Query("Delete From $LAB_RESULT_TABLE_NAME")
    suspend fun clearTable()
}