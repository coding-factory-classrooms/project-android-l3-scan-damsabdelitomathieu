package com.example.foodscanner.inventory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodscanner.data.Food
import com.example.foodscanner.R
import com.example.foodscanner.databinding.ActivityInventoryBinding

class InventoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInventoryBinding // fait le pont entre le xml et le code
    private val model: InventoryViewModel by viewModels()
    private  lateinit var adapter: InventoryAdapter

    private val foods = listOf(
        Food(0,"riz lustucrue",
            "24/03/2021",
            "Riz long grain 10mn LUSTUCRU : le paquet de 900g",
            "R.drawable.riz_lustucru"),
        Food(0,"riz lustucrue",
            "24/03/2021",
            "Riz long grain 10mn LUSTUCRU : le paquet de 900g",
            "R.drawable.riz_lustucru"),
        Food(0,"riz lustucrue",
            "24/03/2021",
            "Riz long grain 10mn LUSTUCRU : le paquet de 900g",
            "R.drawable.riz_lustucru"),
        Food(0,"riz lustucrue",
            "24/03/2021",
            "Riz long grain 10mn LUSTUCRU : le paquet de 900g",
            "R.drawable.riz_lustucru"),
        Food(0,"riz lustucrue",
            "24/03/2021",
            "Riz long grain 10mn LUSTUCRU : le paquet de 900g",
            "R.drawable.riz_lustucru"),
        Food(0,"riz lustucrue",
            "24/03/2021",
            "Riz long grain 10mn LUSTUCRU : le paquet de 900g",
            "R.drawable.riz_lustucru"),
        Food(0,"riz lustucrue",
            "24/03/2021",
            "Riz long grain 10mn LUSTUCRU : le paquet de 900g",
            "R.drawable.riz_lustucru"),
        Food(0,"riz lustucrue",
            "24/03/2021",
            "Riz long grain 10mn LUSTUCRU : le paquet de 900g",
            "R.drawable.riz_lustucru"),
        Food(0,"riz lustucrue",
            "24/03/2021",
            "Riz long grain 10mn LUSTUCRU : le paquet de 900g",
            "R.drawable.riz_lustucru"),
    )

    /*
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
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInventoryBinding.inflate(layoutInflater) // vas me charger le fichier xml qui correspond as ActivityInventoryBinding
        val view = binding.root
        setContentView(view)

        adapter = InventoryAdapter(foods)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}