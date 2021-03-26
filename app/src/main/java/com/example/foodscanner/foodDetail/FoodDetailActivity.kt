package com.example.foodscanner.foodDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.foodscanner.ScannerViewModel
import com.example.foodscanner.data.Food
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

        val food = intent.getSerializableExtra("food")
        model.setFood(food as Food)
        model.getState().observe(this, { updateUi(it!!) })
    }

    private fun updateUi(state: Food) {
        binding.descriptionTextView.text = state.description
        binding.latestScanDateTextView.text = state.latestScanDate
        binding.textView.text = state.title

        /*
        Glide.with(this)
            .load(state.imageUrl)
            .into(binding.imageView)

         */

        Glide.with(this)
            .load("http://www.clicmarket.fr/4246-thickbox_default/24-canettes-de-coca-cola-24-x-33-cl.jpg")
            .into(binding.imageView)
    }
}