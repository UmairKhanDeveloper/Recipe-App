package com.example.recipesapp.mealDetailsId


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeDetailsId(
    @SerialName("meals")
    val meals: List<FavoriteMeal>
)