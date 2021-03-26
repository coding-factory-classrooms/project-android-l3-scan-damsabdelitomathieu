package com.example.foodscanner.inventory

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodscanner.ScannerActivity
import com.example.foodscanner.data.Food
import com.example.foodscanner.data.FoodDataBase
import com.example.foodscanner.databinding.ActivityInventoryBinding
import com.example.foodscanner.foodDetail.FoodDetailActivity


class InventoryActivity : AppCompatActivity(), CellClickListener {

    private lateinit var binding: ActivityInventoryBinding // fait le pont entre le xml et le code
    private val model: InventoryViewModel by viewModels()
    private lateinit var adapter: InventoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityInventoryBinding.inflate(layoutInflater) // vas me charger le fichier xml qui correspond as ActivityInventoryBinding
        val view = binding.root
        setContentView(view)

        model.foodDao = FoodDataBase.getDataBase(this).foodDao()
        model.loadFoods()

        adapter = InventoryAdapter(listOf(), this)

        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        model.getFoodsLiveData().observe(this, { foods -> updateFoods(foods!!) })
        model.loadFoods()

        binding.removeFloatingButton.setOnClickListener() {
            removeAllFoods()
        }

        binding.addFloatingButton.setOnClickListener() {
            addNewFood()
        }
    }

    private fun addNewFood() {
        val intent = Intent(this, ScannerActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

    private fun removeAllFoods() {
        model.removeAllFoods()
    }

    private fun updateFoods(foods: List<Food>) {
        adapter.updateDataSet(foods)
    }

    override fun onCellClickListener(food: Food) {
        val intent = Intent(this, FoodDetailActivity::class.java)
        intent.putExtra("food", food)
        startActivity(intent)
        //setupActionBarWithNavController(findNavController(intent))
    }
}