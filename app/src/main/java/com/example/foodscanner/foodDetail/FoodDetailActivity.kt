package com.example.foodscanner.foodDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.foodscanner.databinding.ActivityFoodDetailBinding
import com.example.foodscanner.databinding.ActivityInventoryBinding
import com.example.foodscanner.inventory.InventoryViewModel

class FoodDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFoodDetailBinding
    private val model: FoodDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFoodDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}