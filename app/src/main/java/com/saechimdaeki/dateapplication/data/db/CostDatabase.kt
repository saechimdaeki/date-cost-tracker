package com.saechimdaeki.dateapplication.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.saechimdaeki.dateapplication.data.model.CostEntry

@Database(entities = [CostEntry::class], version = 1)
abstract class CostDatabase : RoomDatabase() {
    abstract fun costDao(): CostDao
}