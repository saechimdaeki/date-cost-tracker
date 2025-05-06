package com.saechimdaeki.dateapplication.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.saechimdaeki.dateapplication.data.model.CostEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface CostDao {
    @Query("SELECT * FROM cost_entries ORDER BY date DESC")
    fun getAllEntries(): Flow<List<CostEntry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(entry: CostEntry)

    @Delete
    suspend fun deleteEntry(entry: CostEntry)
}