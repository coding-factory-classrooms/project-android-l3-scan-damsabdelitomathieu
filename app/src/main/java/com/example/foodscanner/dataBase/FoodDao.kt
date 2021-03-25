package com.example.foodscanner.dataBase

import androidx.room.*
import com.example.foodscanner.Food
import com.example.foodscanner.ScannedFood
import kotlinx.coroutines.flow.Flow


@Dao
interface FoodDao {
    /*@Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg foods: ScannedFood)

    @Delete
    fun delete(food: ScannedFood)

    @Query("SELECT * FROM food")
    fun getAll(): Flow<List<ScannedFood>>*/

    @Query("SELECT * FROM food_table")
    fun getAll(): Flow<List<ScannedFood>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(food: Food)

    @Query("DELETE FROM food_table")
    suspend fun deleteAll()


}