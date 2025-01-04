package com.example.recipesapp.api.MostPopular

import com.example.recipesapp.api.MostPopular.Constant.BASE_URL
import com.example.recipesapp.api.MostPopular.Constant.TIMEOUT
import com.example.recipesapp.api.RecentlyCreated.RecentlyCreated
import com.example.recipesapp.api.RecommendedPlan.RecommendedPlan
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

object MealApiClient {
    @OptIn(ExperimentalSerializationApi::class)
    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(
                Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                    explicitNulls = false
                    prettyPrint = true
                }
            )
        }

        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    println(message)
                }

            }
        }
        install(HttpTimeout) {
            socketTimeoutMillis = TIMEOUT
            requestTimeoutMillis = TIMEOUT
            connectTimeoutMillis = TIMEOUT

        }
    }

    suspend fun MostPopularMeal(): MostPopular {
        return client.get(BASE_URL).body()
    }

    suspend fun RecentlyCreatedMeal():RecentlyCreated {
        return client.get("https://www.themealdb.com/api/json/v1/1/search.php?f=m").body()
    }
    suspend fun RecommendedPlanMeal(): RecommendedPlan {
        return client.get("https://www.themealdb.com/api/json/v1/1/search.php?f=b").body()
    }
}