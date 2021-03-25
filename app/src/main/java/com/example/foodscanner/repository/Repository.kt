package com.example.foodscanner.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.foodscanner.api.RetrofitInstance
import com.example.foodscanner.api.model.FoodRequest
import com.example.foodscanner.data.Food
import com.example.foodscanner.data.FoodDao
import retrofit2.Response

class Repository {

    suspend fun getFood(barCode: String): Response<FoodRequest> {
        return RetrofitInstance.api.getFood(barCode)
    }
}

class FoodRepository(private val foodDao: FoodDao) {

    val readAllData: LiveData<List<Food>> = foodDao.readAllData()

    suspend fun addFood(food: Food) {
        Log.i("DATABASE", "addFood() in Repository -> food = $food")
        foodDao.addFood(food)
    }

    fun getData() : LiveData<List<Food>> {
        return foodDao.readAllData()
    }
}