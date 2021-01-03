package com.example.nbt_data_remote.services

import com.example.nbt_data_remote.data_transfer_models.NetworkFoodContainer
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodService {
    @GET("/api/json/v1/1/filter.php")
    fun getFoodListAsync(@Query("c") category: String): Deferred<NetworkFoodContainer>
}