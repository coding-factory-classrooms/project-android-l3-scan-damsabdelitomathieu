package com.example.foodscanner.api

import com.example.foodscanner.api.model.FoodRequest
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path


interface SimpleApi {

    @GET("api/v0/product/{barCode}.json")
    suspend fun getFood(
        @Path("barCode") barCode: String
    ): Response<FoodRequest>
}