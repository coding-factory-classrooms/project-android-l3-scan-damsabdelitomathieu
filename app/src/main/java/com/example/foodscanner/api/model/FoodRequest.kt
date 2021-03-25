package com.example.foodscanner.api.model

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

data class FoodRequest(
    @SerializedName("status")
    val status: Int,
    @SerializedName("code")
    val code: String,
    @SerializedName("product")
    val product: JsonElement?,
    @SerializedName("status_verbose")
    val status_verbose: String,
) {

    fun isFound(): Boolean {
        return status == 1
    }

    fun getFoodName(): String {
        if (this.product != null) {
            if (this.product.asJsonObject.get("product_name") != null)
                return this.product.asJsonObject.get("product_name").toString()
        }

        return ""
    }

    fun getBrands(): String {
        if (this.product != null) {
            if (this.product.asJsonObject.get("brands") != null)
                return this.product.asJsonObject.get("brands").toString()
        }

        return ""
    }

    fun getImageUrl(): String {
        if (this.product != null) {
            if (this.product.asJsonObject.get("image_front_url") != null)
                return this.product.asJsonObject.get("image_front_url").toString()
        }

        return ""
    }

    fun getSmallImageUrl(): String {
        if (this.product != null) {
            if (this.product.asJsonObject.get("image_nutrition_small_url") != null)
                return this.product.asJsonObject.get("image_nutrition_small_url").toString()
        }

        return ""
    }
}