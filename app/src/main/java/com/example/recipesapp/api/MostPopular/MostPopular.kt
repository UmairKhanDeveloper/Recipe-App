package com.example.recipesapp.api.MostPopular


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MostPopular(
    @SerialName("meals")
    val meals: List<Meal>
)