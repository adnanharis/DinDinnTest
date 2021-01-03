package com.example.nbt_data_remote.services

import com.example.nbt_data_remote.data_transfer_models.NetworkFoodContainer
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface FoodService {
    @GET("/api/json/v1/1/filter.php?c=Seafood")
    fun getFoodListAsync(): Deferred<NetworkFoodContainer>
}