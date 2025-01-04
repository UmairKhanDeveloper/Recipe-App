package com.example.recipesapp.api.MostPopular

import com.example.recipesapp.api.RecentlyCreated.RecentlyCreated
import com.example.recipesapp.api.RecommendedPlan.RecommendedPlan

interface mealClient {
    suspend fun MostPopularMeal():MostPopular
    suspend fun RecentlyCreatedMeal(): RecentlyCreated
    suspend fun RecommendedPlanMeal(): RecommendedPlan

}