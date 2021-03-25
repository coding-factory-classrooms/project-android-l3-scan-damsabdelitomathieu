package com.example.foodscanner.dataBase

import androidx.room.*
import com.example.foodscanner.Food
import com.example.foodscanner.ScannedFood
import kotlinx.coroutines.flow.Flow


@Dao
interface ScannedFoodDao {
    @Query("SELECT * FROM food_table")
    fun getAll(): List<ScannedFood> //flow

    //@Insert(onConflict = OnConflictStrategy.IGNORE)
    //suspend fun insert(food: Food)
    @Insert
    fun insertAll(vararg ScannedFoods: ScannedFood)

    @Query("DELETE FROM food_table")
    suspend fun deleteAll()


}