package com.example.foodscanner.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "foods_table")
data class Food(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val latestScanDate: String,
    val description: String,
    val imageUrl: String,
)