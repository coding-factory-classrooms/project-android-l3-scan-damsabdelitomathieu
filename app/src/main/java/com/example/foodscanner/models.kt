package com.example.foodscanner

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Food(
    val title: String,
    val latestScanDate: String,
    val description: String,
    val imageId: Int
)

@Entity(tableName = "food_table")
data class ScannedFood (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String?,
    /*
    @ColumnInfo(name = "latest_scan_date") val latestScanDate: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "image_id") val imageId: String?
    */
)