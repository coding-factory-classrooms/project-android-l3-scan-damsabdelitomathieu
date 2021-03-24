package com.example.foodscanner.inventory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodscanner.Food
import com.example.foodscanner.R
import com.example.foodscanner.ScannerViewModel
import com.example.foodscanner.databinding.ActivityInventoryBinding
import com.example.foodscanner.databinding.ActivityScannerBinding

class InventoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInventoryBinding
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
        binding = ActivityInventoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        adapter = InventoryAdapter(foods)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

    }
}