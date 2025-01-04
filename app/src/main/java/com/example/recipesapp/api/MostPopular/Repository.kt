package com.example.recipesapp.api.MostPopular

import com.example.recipesapp.api.RecentlyCreated.RecentlyCreated
import com.example.recipesapp.api.RecommendedPlan.RecommendedPlan

class Repository() :mealClient{
    override suspend fun MostPopularMeal(): MostPopular {
  return MealApiClient.MostPopularMeal()
    }

    override suspend fun RecentlyCreatedMeal(): RecentlyCreated {
     return MealApiClient.RecentlyCreatedMeal()
    }

    override suspend fun RecommendedPlanMeal(): RecommendedPlan {
        return MealApiClient.RecommendedPlanMeal()
    }

}