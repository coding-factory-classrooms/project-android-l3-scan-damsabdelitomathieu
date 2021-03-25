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