package com.example.nbt_data_remote.data_transfer_models

import com.example.nbt_domain.models.Food
import com.squareup.moshi.JsonClass

/**
 * Network related models for domain models
 */

@JsonClass(generateAdapter = true)
data class NetworkFoodContainer(val meals: List<NetworkFood>)

@JsonClass(generateAdapter = true)
data class NetworkFood(val idMeal: String, val strMeal: String, val strMealThumb: String)

fun NetworkFoodContainer.asDomainModel(category: String): List<Food> {
    return meals.map {
        Food(it.idMeal, it.strMeal, it.strMealThumb, category)
    }
}