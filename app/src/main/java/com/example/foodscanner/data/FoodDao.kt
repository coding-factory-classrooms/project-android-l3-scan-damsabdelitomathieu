package com.example.foodscanner.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFood(food: Food)

    @Query("SELECT * FROM foods_table ORDER BY latestScanDate DESC")
    fun readAllData(): List<Food>

    @Query("DELETE FROM foods_table")
    fun removeAllData()
}