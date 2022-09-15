package com.example.examplewithcompose.room_example.data_sources.labresults.local_storage

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        LabResult::class
    ],
    version = 1
)
abstract class MemoryAppDatabase : RoomDatabase() {
    abstract fun labResultsDao(): LabResultDao
}