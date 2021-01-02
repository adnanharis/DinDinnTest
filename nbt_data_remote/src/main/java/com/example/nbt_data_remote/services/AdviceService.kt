package com.example.nbt_data_remote.services

import com.example.nbt_data_remote.data_transfer_models.NetworkAdvice
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface AdviceService {
    @GET("/advice")
    fun getTodaysMessage(): Deferred<NetworkAdvice>
}