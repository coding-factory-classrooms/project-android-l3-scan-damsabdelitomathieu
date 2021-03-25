package com.example.foodscanner.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foodscanner.Food
import com.example.foodscanner.ScannedFood

@Database(entities = arrayOf(ScannedFood::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun scannedFoodDao(): ScannedFoodDao
}