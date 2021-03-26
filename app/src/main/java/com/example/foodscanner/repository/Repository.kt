package com.example.foodscanner.repository

import com.example.foodscanner.api.RetrofitInstance
import com.example.foodscanner.api.model.FoodRequest
import retrofit2.Response

class Repository {

    suspend fun getFood(barCode: String): Response<FoodRequest> {
        return RetrofitInstance.api.getFood(barCode)
    }
}