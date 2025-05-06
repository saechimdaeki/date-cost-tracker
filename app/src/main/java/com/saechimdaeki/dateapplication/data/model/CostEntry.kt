package com.saechimdaeki.dateapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cost_entries")
data class CostEntry(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val category: String,
    val amount: Int,
    val date: String // ISO-8601 형식 (yyyy-MM-dd)
)