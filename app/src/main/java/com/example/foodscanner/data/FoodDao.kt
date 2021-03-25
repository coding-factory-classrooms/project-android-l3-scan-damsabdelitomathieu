package com.example.foodscanner.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFood(food: Food)

    @Query("SELECT * FROM foods_table ORDER BY id ASC")
    fun readAllData(): List<Food>
}