package com.example.recipesapp

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.room.Room
import coil.compose.AsyncImage
import com.example.recipesapp.db.Favorite
import com.example.recipesapp.db.FavoriteDataBase
import com.example.recipesapp.db.MainViewModel
import com.example.recipesapp.db.Repository
import com.example.recipesapp.mealDetailsId.FavoriteMeal
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoritesScreen(navController: NavHostController) {
    val context = LocalContext.current
    val favoriteDataBase =
        remember { Room.databaseBuilder(context, FavoriteDataBase::class.java, "demo.db").fallbackToDestructiveMigration().build() }
    val repository = remember { Repository(favoriteDataBase) }
    val viewModel = remember { MainViewModel(repository) }
    var isloading by remember { mutableStateOf(false) }
    val favoriteState by viewModel.allrecipes.observeAsState()

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Favorites") },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0XFFFFFAF5))
        )
    }) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding(), bottom = it.calculateTopPadding())
                .padding(horizontal = 6.dp, vertical = 6.dp),
        ) {
            favoriteState?.let {
                items(it) {
                    FavoriteItem(favorite = it, navController, )
                }
            }
        }
    }
}


@Composable
fun FavoriteItem(
    favorite: Favorite,
    navController: NavController

    ) {
    Card(
        modifier = Modifier
            .height(211.dp)
            .width(150.dp)
            .padding(8.dp), colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box() {

                AsyncImage(
                    model = favorite.title,
                    contentDescription = favorite.des,
                    modifier = Modifier
                        .clickable {
                            navController.navigate("${Screens.MealDetailsScreen.route}/${favorite.favid}")
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
                        .size(30.dp)
                        .background(color = Color.White)

                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite, tint = Color.Red,
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.Center)
                    )

                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = favorite.des,
                style = TextStyle(fontSize = 11.sp, fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

        }
    }
}