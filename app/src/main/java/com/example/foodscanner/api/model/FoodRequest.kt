package com.example.foodscanner.api.model

data class FoodRequest(
    val status: Int,
    val code: String,
    val status_verbos: String
)