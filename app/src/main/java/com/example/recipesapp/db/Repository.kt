package com.example.recipesapp.db

import androidx.lifecycle.LiveData
import com.example.recipesapp.api.MostPopular.MealApiClient
import com.example.recipesapp.api.MostPopular.MostPopular
import com.example.recipesapp.api.MostPopular.mealClient
import com.example.recipesapp.api.RecentlyCreated.RecentlyCreated
import com.example.recipesapp.api.RecommendedPlan.RecommendedPlan
import com.example.recipesapp.mealDetailsId.RecipeDetailsId


class Repository(private val favoriteDataBase: FavoriteDataBase) : mealClient {
    override suspend fun MostPopularMeal(): MostPopular {
        return MealApiClient.MostPopularMeal()
    }

    override suspend fun RecentlyCreatedMeal(): RecentlyCreated {
        return MealApiClient.RecentlyCreatedMeal()
    }

    override suspend fun RecommendedPlanMeal(): RecommendedPlan {
        return MealApiClient.RecommendedPlanMeal()
    }

    override suspend fun RecipeDetailsId(id: String): RecipeDetailsId {
        return MealApiClient.RecipeDetailsId(id)
    }



    fun getFavData(): LiveData<List<Favorite>> {
        return favoriteDataBase.getDao().getAllNotes()
    }

    suspend fun Insert(favorite: Favorite) {
        favoriteDataBase.getDao().Insert(favorite)
    }
    suspend fun Delete(favorite: Favorite){
        favoriteDataBase.getDao().Delete(favorite)
    }
}