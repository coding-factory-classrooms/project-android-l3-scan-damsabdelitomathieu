package com.example.foodscanner.inventory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.foodscanner.Food
import com.example.foodscanner.R
import com.example.foodscanner.ScannedFood
import com.example.foodscanner.ScannerViewModel
import com.example.foodscanner.dataBase.AppDatabase
import com.example.foodscanner.databinding.ActivityInventoryBinding
import com.example.foodscanner.databinding.ActivityScannerBinding
import com.example.foodscanner.databinding.ItemFoodBinding
import com.example.foodscanner.foodDetail.FoodDetailActivity

class InventoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInventoryBinding // fait le pont entre le xml et le code
    private val model: InventoryViewModel by viewModels()
    private  lateinit var adapter: InventoryAdapter

    private val foods = listOf(
        Food("riz lustucrue", "24/03/2021",
            "Riz long grain 10mn LUSTUCRU : le paquet de 900g",
        R.drawable.riz_lustucru),
        Food("riz lustucrue", "24/03/2021",
            "Riz long grain 10mn LUSTUCRU : le paquet de 900g",
            R.drawable.riz_lustucru),
        Food("riz lustucrue", "24/03/2021",
            "Riz long grain 10mn LUSTUCRU : le paquet de 900g",
            R.drawable.riz_lustucru),
        Food("riz lustucrue", "24/03/2021",
            "Riz long grain 10mn LUSTUCRU : le paquet de 900g",
            R.drawable.riz_lustucru),
        Food("riz lustucrue", "24/03/2021",
            "Riz long grain 10mn LUSTUCRU : le paquet de 900g",
            R.drawable.riz_lustucru),
        Food("riz lustucrue", "24/03/2021",
            "Riz long grain 10mn LUSTUCRU : le paquet de 900g",
            R.drawable.riz_lustucru),
        Food("riz lustucrue", "24/03/2021",
            "Riz long grain 10mn LUSTUCRU : le paquet de 900g",
            R.drawable.riz_lustucru),
        Food("riz lustucrue", "24/03/2021",
            "Riz long grain 10mn LUSTUCRU : le paquet de 900g",
            R.drawable.riz_lustucru),
        Food("riz lustucrue", "24/03/2021",
            "Riz long grain 10mn LUSTUCRU : le paquet de 900g",
            R.drawable.riz_lustucru),
        Food("riz lustucrue", "24/03/2021",
            "Riz long grain 10mn LUSTUCRU : le paquet de 900g",
            R.drawable.riz_lustucru),
        Food("riz lustucrue", "24/03/2021",
            "Riz long grain 10mn LUSTUCRU : le paquet de 900g",
            R.drawable.riz_lustucru),
        Food("riz lustucrue", "24/03/2021",
            "Riz long grain 10mn LUSTUCRU : le paquet de 900g",
            R.drawable.riz_lustucru)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInventoryBinding.inflate(layoutInflater) // vas me charger le fichier xml qui correspond as ActivityInventoryBinding
        val view = binding.root
        setContentView(view)

        adapter = InventoryAdapter(foods)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "ScannedFoodDao"
        ).build()

        val foodDao = db.scannedFoodDao()
        foodDao.insertAll(ScannedFood(1, "riz", "12/03/2021", "test", 1))
        val scannedFood: List<ScannedFood> = foodDao.getAll()

        for (scannedFood in scannedFood) {
            Log.i("InventoryActivity", "id = ${scannedFood.id}, title = ${scannedFood.title}, date = ${scannedFood.latestScanDate}," +
                    "desc = ${scannedFood.description}, img = ${scannedFood.image}")
        }
    }
}