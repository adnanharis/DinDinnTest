package com.example.nbt_data_remote.data_transfer_models

import com.example.nbt_domain.models.Advice
import com.squareup.moshi.JsonClass

/**
 * Network related models for domain models
 */

@JsonClass(generateAdapter = true)
data class NetworkAdvice(var slip: Slip)

@JsonClass(generateAdapter = true)
data class Slip(val id: Long, var advice: String)


fun NetworkAdvice.asDomainModel(): Advice {
    return Advice(slip.id, slip.advice)
}