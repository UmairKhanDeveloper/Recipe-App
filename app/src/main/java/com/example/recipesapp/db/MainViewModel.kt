package com.example.recipesapp.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.api.MostPopular.MostPopular
import com.example.recipesapp.api.RecentlyCreated.RecentlyCreated
import com.example.recipesapp.api.RecommendedPlan.RecommendedPlan
import com.example.recipesapp.mealDetailsId.FavoriteMeal
import com.example.recipesapp.mealDetailsId.RecipeDetailsId
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {
    private val _PopularMeal = MutableStateFlow<ResultState<MostPopular>>(ResultState.Loading)
    val PopularMeal: StateFlow<ResultState<MostPopular>> = _PopularMeal.asStateFlow()

    suspend fun MostPopularMeal() {
        viewModelScope.launch {
            _PopularMeal.value = ResultState.Loading
            try {
                val response = repository.MostPopularMeal()
                _PopularMeal.value = ResultState.Succses(response)
            } catch (e: Exception) {
                _PopularMeal.value = ResultState.Error(e)

            }
        }
    }

    private val _RecentlyMeal = MutableStateFlow<ResultState<RecentlyCreated>>(ResultState.Loading)
    val RecentlyMeal: StateFlow<ResultState<RecentlyCreated>> = _RecentlyMeal.asStateFlow()

    suspend fun RecentlyCreatedMeal() {
        viewModelScope.launch {
            _RecentlyMeal.value = ResultState.Loading
            try {
                val response = repository.RecentlyCreatedMeal()
                _RecentlyMeal.value = ResultState.Succses(response)
            } catch (e: Exception) {
                _RecentlyMeal.value = ResultState.Error(e)

            }
        }
    }

    private val _RecommendedPlan =
        MutableStateFlow<ResultState<RecommendedPlan>>(ResultState.Loading)
    val RecommendedPlan: StateFlow<ResultState<RecommendedPlan>> = _RecommendedPlan.asStateFlow()

    suspend fun RecommendedPlanMeal() {
        viewModelScope.launch {
            _RecommendedPlan.value = ResultState.Loading
            try {
                val response = repository.RecommendedPlanMeal()
                _RecommendedPlan.value = ResultState.Succses(response)
            } catch (e: Exception) {
                _RecommendedPlan.value = ResultState.Error(e)

            }
        }
    }

    private val _RecipeDetailsId =
        MutableStateFlow<ResultState<RecipeDetailsId>>(ResultState.Loading)
    val RecipeDetailsId: StateFlow<ResultState<RecipeDetailsId>> = _RecipeDetailsId.asStateFlow()

    suspend fun RecipeDetailsId(id: String) {
        viewModelScope.launch {
            _RecipeDetailsId.value = ResultState.Loading
            try {
                val response = repository.RecipeDetailsId(id)
                _RecipeDetailsId.value = ResultState.Succses(response)
            } catch (e: Exception) {
                _RecipeDetailsId.value = ResultState.Error(e)

            }
        }
    }

    val allrecipes: LiveData<List<Favorite>> = repository.getFavData()


    fun Insert(favorite: Favorite) {
        viewModelScope.launch {
            try {
                repository.Insert(favorite)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun Delete(favorite: Favorite) {
        viewModelScope.launch {
            try {
                repository.Delete(favorite)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}