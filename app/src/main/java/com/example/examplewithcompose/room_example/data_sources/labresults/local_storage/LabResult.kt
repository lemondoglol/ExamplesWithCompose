package com.example.examplewithcompose.room_example.data_sources.labresults.local_storage

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = LAB_RESULT_TABLE_NAME)
data class LabResult(
    @PrimaryKey val labResultId: String,
    val labResultTitle: String,
    val labResultContent: String,
) {
    // if you want to convert to other data structure
    fun convertToSomeThing() = Unit
}

const val LAB_RESULT_TABLE_NAME = "labResults"