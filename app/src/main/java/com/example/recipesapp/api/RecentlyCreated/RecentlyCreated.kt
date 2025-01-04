package com.example.recipesapp.api.RecentlyCreated


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecentlyCreated(
    @SerialName("meals")
    val meals: List<RecentlyMeal>
)