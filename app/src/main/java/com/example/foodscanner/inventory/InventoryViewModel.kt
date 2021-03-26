package com.example.foodscanner.inventory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodscanner.data.Food
import com.example.foodscanner.data.FoodDao

class InventoryViewModel : ViewModel() {

    lateinit var foodDao: FoodDao
    private val foodsLiveData = MutableLiveData<List<Food>>()
    private lateinit var foods: List<Food>

    fun getFoodsLiveData(): LiveData<List<Food>> = foodsLiveData

    fun loadFoods() {
        foodsLiveData.value = foodDao.readAllData()
    }

    fun removeAllFoods() {
        foodDao.removeAllData()
        loadFoods()
    }
}