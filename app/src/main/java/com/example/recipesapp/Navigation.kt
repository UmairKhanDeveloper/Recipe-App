package com.example.recipesapp

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocalGroceryStore
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.LocalGroceryStore
import androidx.compose.material.icons.outlined.RestaurantMenu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.recipesapp.api.MostPopular.Meal
import com.example.recipesapp.api.RecentlyCreated.RecentlyMeal
import com.example.recipesapp.api.RecommendedPlan.RecommendedPlanMeal
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.MealPlanScreen.route) {
        composable(Screens.MealPlanScreen.route) {
            MealPlanScreen(navController)
        }
        composable(Screens.GroceriesScreen.route) {
            GroceriesScreen(navController)
        }
        composable(Screens.FavoritesScreen.route) {
            FavoritesScreen(navController)
        }
        composable(Screens.NutaritionsFactsScreen.route + "/{strInstructions}", arguments = listOf(
            navArgument("strInstructions") {
                type = NavType.StringType
            }
        )) {
            val strInstructions = it.arguments?.getString("strInstructions")
            NutaritionsFactsScreen(navController, strInstructions)
        }
        composable(Screens.SettingScreen.route) {
            SettingScreen(navController)
        }
        composable(Screens.AddNotesScreen.route) {
            AddNotesScreen(navController)
        }
        composable(Screens.FeecbackScreen.route) {
            FeecbackScreen(navController)
        }

        composable(Screens.PopularmealdetailScreen.route + "/{meal}") { backStackEntry ->
            val mealJson = backStackEntry.arguments?.getString("meal") ?: "{}"
            val meal = try {
                Json.decodeFromString<Detail>(mealJson)
            } catch (e: Exception) {
                null
            }
            if (meal != null) {
                PopularmealdetailScreen(navController, meal)
            }
        }
        composable(Screens.RecentlyCreateddetailScreen.route + "/{RecentlyDetail}") { backStackEntry ->
            val RecentlyDetailJson = backStackEntry.arguments?.getString("RecentlyDetail") ?: "{}"
            val RecentlyDetail = try {
                Json.decodeFromString<RecentlyDetail>(RecentlyDetailJson)
            } catch (e: Exception) {
                null
            }
            if (RecentlyDetail != null) {
                RecentlyCreateddetailScreen(navController, RecentlyDetail)
            }
        }
        composable(Screens.RecommendedPlandetailScreen.route + "/{RecommendedDetail}") { backStackEntry ->
            val RecommendedDetailJson =
                backStackEntry.arguments?.getString("RecommendedDetail") ?: "{}"
            val RecommendedDetail = try {
                Json.decodeFromString<RecommendedDetail>(RecommendedDetailJson)
            } catch (e: Exception) {
                null
            }
            if (RecommendedDetail != null) {
                RecommendedPlandetailScreen(navController, RecommendedDetail)
            }
        }

    }
}

@Serializable
data class Detail(
    val meals: Meal
)

@Serializable
data class RecentlyDetail(
    val meals: RecentlyMeal
)

@Serializable
data class RecommendedDetail(
    val meals: RecommendedPlanMeal
)


sealed class Screens(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
) {
    object MealPlanScreen : Screens(
        "MealPlan",
        "MealPlan",
        selectedIcon = Icons.Filled.RestaurantMenu,
        unselectedIcon = Icons.Outlined.RestaurantMenu,
    )

    object GroceriesScreen : Screens(
        "Groceries",
        "Groceries",
        selectedIcon = Icons.Filled.LocalGroceryStore,
        unselectedIcon = Icons.Outlined.LocalGroceryStore
    )

    object FavoritesScreen : Screens(
        "Favorites",
        "Favorites",
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.FavoriteBorder,
    )

    object SettingScreen : Screens(
        "SettingScreen",
        "Setting",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )

    object PopularmealdetailScreen : Screens(
        "PopularmealdetailScreen",
        "PopularmealdetailScreen",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )

    object RecentlyCreateddetailScreen : Screens(
        "RecentlyCreateddetailScreen",
        "RecentlyCreateddetailScreen",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )

    object RecommendedPlandetailScreen : Screens(
        "RecommendedPlandetailScreen",
        "RecommendedPlandetailScreen",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )

    object NutaritionsFactsScreen : Screens(
        "  NutaritionsFactsScreen",
        "NutaritionsFactsScreen",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )

    object AddNotesScreen : Screens(
        "  AddNotesScreen",
        "AddNotesScreen",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )

    object FeecbackScreen : Screens(
        "  FeecbackScreen",
        "FeecbackScreen",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )


}

@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        Screens.MealPlanScreen,
        Screens.GroceriesScreen,
        Screens.FavoritesScreen,
        Screens.SettingScreen,
    )
    NavigationBar(containerColor = Color.White) {
        val navStack by navController.currentBackStackEntryAsState()
        val currentRoute = navStack?.destination?.route

        items.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        navController.graph?.let {
                            it.route?.let { it1 -> popUpTo(it1) }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Image(
                        imageVector = if (currentRoute == screen.route) screen.selectedIcon else screen.unselectedIcon,
                        contentDescription = screen.title,
                        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(
                            if (currentRoute == screen.route) Color(0XFFF58700) else Color(
                                0XFFB3B3B3
                            )
                        )

                    )
                }, label = {
                    Text(text = screen.title)

                }, colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.White,
                    selectedTextColor = Color(0XFFF58700),
                    selectedIconColor = Color(0XFFF58700),
                    unselectedTextColor = Color(0XFFB3B3B3),
                )


            )
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavEntry() {
    val navController = rememberNavController()
    var showBottomNav by remember { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    showBottomNav = when {
        currentRoute == null -> true
        currentRoute.startsWith(Screens.PopularmealdetailScreen.route) -> false
        currentRoute.startsWith(Screens.RecentlyCreateddetailScreen.route) -> false
        currentRoute.startsWith(Screens.RecommendedPlandetailScreen.route) -> false
        currentRoute.startsWith(Screens.NutaritionsFactsScreen.route) -> false
        currentRoute.startsWith(Screens.AddNotesScreen.route) -> false
        currentRoute.startsWith(Screens.FeecbackScreen.route) -> false
        else -> true
    }


    Scaffold(
        bottomBar = {
            if (showBottomNav) {
                BottomNavigation(navController = navController)
            }
        }
    ) { innerPadding ->
        Navigation(navController = navController)
    }
}
