package com.example.recipesapp

import android.annotation.SuppressLint
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.room.Room
import coil.compose.AsyncImage
import com.example.recipesapp.db.FavoriteDataBase
import com.example.recipesapp.db.MainViewModel
import com.example.recipesapp.db.Repository
import com.example.recipesapp.db.ResultState
import com.example.recipesapp.mealDetailsId.RecipeDetailsId


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealDetailsScreen(navController: NavHostController, favid: Int?) {
    val context = LocalContext.current
    val favoriteDataBase =
        remember { Room.databaseBuilder(context, FavoriteDataBase::class.java, "demo.db").build() }
    val repository = remember { Repository(favoriteDataBase) }
    val viewModel = remember { MainViewModel(repository) }
    var boolean by remember { mutableStateOf(true) }
    var boolean1 by remember { mutableStateOf(false) }
    var boolean2 by remember { mutableStateOf(false) }
    var boolean3 by remember { mutableStateOf(false) }
    var boolean4 by remember { mutableStateOf(false) }
    var boolea5 by remember { mutableStateOf(false) }
    var bottomsheet by remember { mutableStateOf(false) }

    var favoriteMealDetails by remember { mutableStateOf<RecipeDetailsId?>(null) }
    var isloading by remember { mutableStateOf(false) }
    val state by viewModel.RecipeDetailsId.collectAsState()
    when (state) {
        is ResultState.Error -> {
            isloading = false
            val error = (state as ResultState.Error).error
            Text(text = "$error")
        }

        ResultState.Loading -> {
            isloading = true
        }

        is ResultState.Succses -> {
            isloading = false
            val success = (state as ResultState.Succses).response
            favoriteMealDetails = success
        }
    }



    LaunchedEffect(Unit) {
        viewModel.RecipeDetailsId(favid.toString())

    }


    LazyColumn(
        modifier = Modifier
            .background(color = Color(0XFFFFFAF5))
            .fillMaxSize()

    ) {
        favoriteMealDetails?.meals?.let {
            items(it) {
                Column(
                    modifier = Modifier
                        .background(color = Color(0XFFFFFAF5))
                        .fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(483.dp)
                    ) {
                        AsyncImage(
                            model = it.strMealThumb,
                            contentDescription = "image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(483.dp)
                                .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 30.dp)
                                .align(Alignment.TopCenter),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box(modifier = Modifier
                                .padding(start = 10.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .size(40.dp)
                                .background(color = Color.White)
                                .clickable { navController.popBackStack() }) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Back",
                                    modifier = Modifier
                                        .size(24.dp)
                                        .align(Alignment.Center)
                                )
                            }
                            Box(modifier = Modifier
                                .padding(end = 10.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .size(40.dp)
                                .background(color = Color.White)
                                .clickable { }) {
                                Icon(
                                    imageVector = Icons.Default.MoreVert,
                                    contentDescription = "Menu",
                                    modifier = Modifier
                                        .size(24.dp)
                                        .rotate(90f)
                                        .align(Alignment.Center)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 14.dp, end = 13.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = it.strMeal,
                            style = TextStyle(fontSize = 19.sp),
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Column(modifier = Modifier.padding(start = 12.dp)) {
                        Row() {
                            Text(
                                text = "Category:",
                                color = Color(0XFF666666),
                                style = TextStyle(fontSize = 14.sp),
                                fontWeight = FontWeight.Normal
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = it.strCategory,
                                color = Color(0XFF666666),
                                style = TextStyle(fontSize = 14.sp),
                                fontWeight = FontWeight.Normal
                            )
                        }
                        Row() {
                            Text(
                                text = "Area:",
                                color = Color(0XFF666666),
                                style = TextStyle(fontSize = 14.sp),
                                fontWeight = FontWeight.Normal
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = it.strArea,
                                color = Color(0XFF666666),
                                style = TextStyle(fontSize = 14.sp),
                                fontWeight = FontWeight.Normal
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.padding(start = 100.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Ingredients",
                        style = TextStyle(fontSize = 25.sp),
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Column(modifier = Modifier.padding(start = 10.dp, bottom = 82.dp, end = 10.dp)) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = it.strIngredient1)
                        Text(
                            text = it.strMeasure1,
                            color = Color(0XFF666666),
                            style = TextStyle(fontSize = 12.sp),
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Color(0XFFCCCCCC))
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = it.strIngredient2)
                        Text(
                            text = it.strMeasure2,
                            color = Color(0XFF666666),
                            style = TextStyle(fontSize = 12.sp),
                        )
                    }


                    Spacer(modifier = Modifier.height(20.dp))
                    HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Color(0XFFCCCCCC))
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = it.strIngredient3)
                        Text(
                            text = it.strMeasure3,
                            color = Color(0XFF666666),
                            style = TextStyle(fontSize = 12.sp),
                        )
                    }


                    Spacer(modifier = Modifier.height(20.dp))
                    HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Color(0XFFCCCCCC))
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = it.strIngredient4)
                        Text(
                            text = it.strMeasure4,
                            color = Color(0XFF666666),
                            style = TextStyle(fontSize = 12.sp),
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Color(0XFFCCCCCC))
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = it.strIngredient5)
                        Text(
                            text = it.strMeasure5,
                            color = Color(0XFF666666),
                            style = TextStyle(fontSize = 12.sp),
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Color(0XFFCCCCCC))
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = it.strIngredient6)
                        Text(
                            text = it.strMeasure6,
                            color = Color(0XFF666666),
                            style = TextStyle(fontSize = 12.sp),
                        )
                    }


                    Spacer(modifier = Modifier.height(20.dp))
                    HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Color(0XFFCCCCCC))
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = it.strIngredient7)
                        Text(
                            text = it.strMeasure7,
                            color = Color(0XFF666666),
                            style = TextStyle(fontSize = 12.sp),
                        )
                    }


                    Spacer(modifier = Modifier.height(20.dp))
                    HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Color(0XFFCCCCCC))
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = it.strIngredient8)
                        Text(
                            text = it.strMeasure8,
                            color = Color(0XFF666666),
                            style = TextStyle(fontSize = 12.sp),
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Color(0XFFCCCCCC))
                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.padding(start = 100.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Instructions",
                            style = TextStyle(fontSize = 25.sp),
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))


                        Text(
                            text = it.strInstructions,
                            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                            color = MaterialTheme.colorScheme.onBackground,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )



                }

            }
        }

    }
}


