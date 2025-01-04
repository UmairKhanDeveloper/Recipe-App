package com.example.recipesapp.api.RecommendedPlan


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecommendedPlan(
    @SerialName("meals")
    val meals: List<RecommendedPlanMeal>
)