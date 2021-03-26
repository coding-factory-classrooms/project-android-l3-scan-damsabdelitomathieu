package com.example.foodscanner.foodDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodscanner.data.Food

class FoodDetailViewModel() : ViewModel(){

    private val state = MutableLiveData<Food>()

    fun getState(): LiveData<Food> = state

    fun setFood(newFood: Food) {
        state.value = newFood
    }
}