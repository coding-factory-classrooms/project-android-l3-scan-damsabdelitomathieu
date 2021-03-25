package com.example.foodscanner.inventory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodscanner.data.Food
import com.example.foodscanner.databinding.ItemFoodBinding
import com.squareup.picasso.Picasso

class InventoryAdapter(private var foods: List<Food>) :
    RecyclerView.Adapter<InventoryAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFoodBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = foods[position]
        with(holder.binding) {
            foodDescriptionTextView.text = food.description
            foodScanDateTextView.text = food.latestScanDate
            Picasso.get().load(food.imageUrl).into(foodImageView)
            //foodImageView.setImageResource(food.imageUrl)
        }
    }

    override fun getItemCount(): Int = foods.size

    fun updateDataSet(foods: List<Food>) {
        this.foods = foods
        notifyDataSetChanged()
    }
}
