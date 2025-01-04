package com.example.recipesapp

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.recipesapp.api.MostPopular.MainViewModel
import com.example.recipesapp.api.MostPopular.Meal
import com.example.recipesapp.api.MostPopular.MostPopular
import com.example.recipesapp.api.MostPopular.Repository
import com.example.recipesapp.api.MostPopular.ResultState
import com.example.recipesapp.api.RecentlyCreated.RecentlyCreated
import com.example.recipesapp.api.RecentlyCreated.RecentlyMeal
import com.example.recipesapp.api.RecommendedPlan.RecommendedPlan
import com.example.recipesapp.api.RecommendedPlan.RecommendedPlanMeal
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealPlanScreen(navController: NavHostController) {

    val repository = remember { Repository() }
    val viewModel = remember { MainViewModel(repository) }
    var mostpopularmeal by remember { mutableStateOf<MostPopular?>(null) }
    var isloading by remember { mutableStateOf(false) }
    var isloadingrecently by remember { mutableStateOf(false) }
    var isloadingrecommended by remember { mutableStateOf(false) }
    var BottomSheet by remember { mutableStateOf(false) }
    var boolean = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("SignUp", Context.MODE_PRIVATE)
    sharedPreferences.edit().putBoolean("userId", true).apply()
    var RecentlyCreatedmeal by remember { mutableStateOf<RecentlyCreated?>(null) }

    var RecommendedPlanmeal by remember { mutableStateOf<RecommendedPlan?>(null) }
    val Recommendedstate by viewModel.RecommendedPlan.collectAsState()
    when (Recommendedstate) {
        is ResultState.Error -> {
            isloadingrecommended = false
            val error = (Recommendedstate as ResultState.Error).error
            Text(text = "$error")
        }

        ResultState.Loading -> {
            isloadingrecommended = true
        }

        is ResultState.Succses -> {
            isloadingrecommended = false
            val success = (Recommendedstate as ResultState.Succses).response
            RecommendedPlanmeal = success
        }
    }

    LaunchedEffect(Unit) {
        isloading = true
        try {
            viewModel.RecommendedPlanMeal()
        } catch (e: Exception) {
        } finally {
            isloading = false
        }
    }


    val sharedPreferencesId = sharedPreferences.getBoolean("userId", false)

    val state by viewModel.PopularMeal.collectAsState()

    val staterecently by viewModel.RecentlyMeal.collectAsState()

    when (staterecently) {
        is ResultState.Error -> {
            isloadingrecently = false
            val error = (staterecently as ResultState.Error).error
            Text(text = "$error")

        }

        ResultState.Loading -> {
            isloadingrecently = true
        }

        is ResultState.Succses -> {
            isloading = false
            val success = (staterecently as ResultState.Succses).response
            RecentlyCreatedmeal = success

        }
    }

    LaunchedEffect(Unit) {
        isloading = true
        try {
            viewModel.RecentlyCreatedMeal()
        } catch (e: Exception) {
        } finally {
            isloading = false
        }
    }

    when (state) {
        is ResultState.Error -> {
            isloading = false
            val error = (state as ResultState.Error).error
            Text(text = "Error: $error")
        }

        ResultState.Loading -> {
            isloading = true
            CircularProgressIndicator()
        }

        is ResultState.Succses -> {
            isloading = false
            val success = (state as ResultState.Succses).response
            mostpopularmeal = success
        }
    }


    LaunchedEffect(Unit) {
        isloading = true
        try {
            viewModel.MostPopularMeal()
        } catch (e: Exception) {
        } finally {
            isloading = false
        }
    }


    if (sharedPreferencesId) {
        Scaffold(topBar = {
            TopAppBar(title = { }, navigationIcon = {
                Icon(imageVector = Icons.Default.Clear, contentDescription = "")
            }, actions = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "")

            }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0XFFFFFAF5)))

        }) {
            Column(
                modifier = Modifier
                    .background(color = Color(0XFFFFFAF5))
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(top = it.calculateTopPadding(), bottom = it.calculateTopPadding())
            ) {
                Column() {
                    Text(
                        text = "Build a meal plan",
                        style = TextStyle(fontSize = 30.sp),
                        modifier = Modifier.padding(start = 10.dp),
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp, end = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Most Popular",
                            style = TextStyle(fontSize = 19.sp, fontWeight = FontWeight.Medium),
                            textAlign = TextAlign.Center,
                        )
                        Text(text = "See All", color = Color(0XFFF58700))
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                        ) {
                            mostpopularmeal?.meals?.let { meals ->
                                items(meals) { meal ->
                                    RecipeList(meal = meal, navController)
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp, end = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Recently Created",
                            style = TextStyle(fontSize = 19.sp, fontWeight = FontWeight.Medium),
                            textAlign = TextAlign.Center,
                        )
                        Text(text = "See All", color = Color(0XFFF58700))
                    }
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                    ) {
                        RecentlyCreatedmeal?.meals?.let {

                            items(it) {
                                RecentlyCreatedRecipeList(recentlyMeal = it, navController)
                            }
                        }

                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp, end = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Recommended Plan",
                            style = TextStyle(fontSize = 19.sp, fontWeight = FontWeight.Medium),
                            textAlign = TextAlign.Center,
                        )
                        Text(text = "See All", color = Color(0XFFF58700))
                    }
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                    ) {
                        RecommendedPlanmeal?.meals?.let {
                            items(it) {
                                RecommendedPlanRecipeList(recommendedPlan = it, navController)
                            }
                        }

                    }
                }

            }

            if (BottomSheet) {
                ModalBottomSheet(onDismissRequest = { BottomSheet = false }) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Close Bottom Sheet",
                            modifier = Modifier
                                .padding(10.dp)
                                .align(Alignment.TopEnd)
                                .clickable { }
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(start = 15.dp, end = 15.dp, bottom = 50.dp)
                    ) {
                        Text(
                            text = "Build your first meal plan",
                            style = TextStyle(fontSize = 30.sp),
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "Add a few recipes to cook this week," +
                                    " \nand we'll build you an easy-to-shop" +
                                    "\ngrocery list."
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        Button(
                            onClick = { BottomSheet = false },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(id = R.color.orange),
                                contentColor = Color.Black
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(text = "Got It!")
                        }
                    }
                }
            }

        }

    }


    if (!sharedPreferencesId) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 18.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Your personalized meal plan",
                    style = TextStyle(fontSize = 30.sp),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center, color = Color.Black
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Plan your meals for the entire week in" +
                            "\n minutes. Build your first meal plan to" +
                            "\n get started!",
                    style = TextStyle(fontSize = 19.sp),
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(30.dp))
                OutlinedButton(
                    onClick = {
                        BottomSheet = true
                    },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(42.dp),
                ) {
                    Text(
                        text = stringResource(id = R.string.bulid_meal),
                        style = TextStyle(fontSize = 20.sp),
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.black)
                    )
                }
            }
        }
    }
}

@Composable
fun RecipeList(meal: Meal, navController: NavController) {
    Card(
        modifier = Modifier
            .height(211.dp)
            .width(150.dp)
            .padding(8.dp), colors = CardDefaults.cardColors(containerColor = Color(0XFFFFFAF5))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box() {

                AsyncImage(
                    model = meal.strMealThumb,
                    contentDescription = meal.strMeal,
                    modifier = Modifier
                        .clickable {

                            val mealsJson = Json.encodeToString(Detail(meal))
                            navController.navigate(
                                Screens.PopularmealdetailScreen.route + "/${Uri.encode(mealsJson)}"
                            )
                        }
                        .clip(RoundedCornerShape(20.dp))
                        .height(150.dp), contentScale = ContentScale.Crop

                )
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .clip(
                            RoundedCornerShape(12.dp)
                        )
                        .size(25.dp)
                        .background(color = Color.White)

                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "",
                        modifier = Modifier
                            .clickable {
                            }
                            .size(20.dp)
                            .align(Alignment.Center)
                    )

                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = meal.strMeal,
                style = TextStyle(fontSize = 11.sp, fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

        }
    }
}

@Composable
fun RecentlyCreatedRecipeList(recentlyMeal: RecentlyMeal, navController: NavController) {
    Card(
        modifier = Modifier
            .height(211.dp)
            .width(150.dp)
            .padding(8.dp), colors = CardDefaults.cardColors(containerColor = Color(0XFFFFFAF5))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box() {

                AsyncImage(
                    model = recentlyMeal.strMealThumb,
                    contentDescription = recentlyMeal.strMeal,
                    modifier = Modifier
                        .clickable {

                            val mealsJson = Json.encodeToString(RecentlyDetail(recentlyMeal))
                            navController.navigate(
                                Screens.PopularmealdetailScreen.route + "/${Uri.encode(mealsJson)}"
                            )
                        }
                        .clip(RoundedCornerShape(20.dp))
                        .height(150.dp), contentScale = ContentScale.Crop

                )
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .clip(
                            RoundedCornerShape(12.dp)
                        )
                        .size(25.dp)
                        .background(color = Color.White)

                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.Center)
                            .clickable { }
                    )

                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = recentlyMeal.strMeal,
                style = TextStyle(fontSize = 11.sp, fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

        }
    }
}

@Composable
fun RecommendedPlanRecipeList(recommendedPlan: RecommendedPlanMeal, navController: NavController) {
    Card(
        modifier = Modifier
            .height(211.dp)
            .width(150.dp)
            .padding(8.dp), colors = CardDefaults.cardColors(containerColor = Color(0XFFFFFAF5))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box() {

                AsyncImage(
                    model = recommendedPlan.strMealThumb,
                    contentDescription = recommendedPlan.strMeal,
                    modifier = Modifier
                        .clickable {

                            val mealsJson = Json.encodeToString(RecommendedDetail(recommendedPlan))
                            navController.navigate(
                                Screens.PopularmealdetailScreen.route + "/${Uri.encode(mealsJson)}"
                            )
                        }
                        .clip(RoundedCornerShape(20.dp))
                        .height(150.dp), contentScale = ContentScale.Crop

                )
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .clip(
                            RoundedCornerShape(12.dp)
                        )
                        .size(25.dp)
                        .background(color = Color.White)

                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.Center)
                    )

                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = recommendedPlan.strMeal,
                style = TextStyle(fontSize = 11.sp, fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

        }
    }
}



