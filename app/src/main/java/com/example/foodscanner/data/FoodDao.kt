package com.example.foodscanner.data

<<<<<<< HEAD
import android.util.Log
import androidx.lifecycle.LiveData
=======
>>>>>>> test
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
<<<<<<< HEAD
    suspend fun addFood(food: Food) {
        Log.i("DATABASE", "addFood() in FoodDao -> food = $food")
    }

    @Query("SELECT * FROM foods_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Food>>
=======
    fun addFood(food: Food)

    @Query("SELECT * FROM foods_table ORDER BY latestScanDate DESC")
    fun readAllData(): List<Food>

    @Query("DELETE FROM foods_table")
    fun removeAllData()
>>>>>>> test
}