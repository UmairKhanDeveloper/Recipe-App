package com.example.recipesapp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.provider.Telephony.Mms.Intents
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.AddComment
import androidx.compose.material.icons.outlined.Addchart
import androidx.compose.material.icons.outlined.Feedback
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Print
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.StickyNote2
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.recipesapp.api.MostPopular.Meal

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PopularmealdetailScreen(
    navController: NavController,
    meal: Detail,
) {

    val context = LocalContext.current
    var boolean by remember { mutableStateOf(true) }
    var boolean1 by remember { mutableStateOf(false) }
    var boolean2 by remember { mutableStateOf(false) }
    var boolean3 by remember { mutableStateOf(false) }
    var boolean4 by remember { mutableStateOf(false) }
    var boolea5 by remember { mutableStateOf(false) }


    var bottomsheet by remember { mutableStateOf(false) }

    if (bottomsheet)
        ModalBottomSheet(onDismissRequest = { bottomsheet = false }) {
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = "",
                modifier = Modifier
                    .clickable { bottomsheet = false }
                    .align(Alignment.End)
                    .padding(end = 10.dp)
            )
            Column(
                modifier = Modifier.padding(start = 10.dp, end = 10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(
                                Screens.NutaritionsFactsScreen.route + "/${
                                    Uri.encode(
                                        meal.meals.strInstructions
                                    )
                                }"
                            )
                            bottomsheet = false
                        },
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = "",
                        tint = Color(0XFF999999)
                    )
                    Text(
                        text = "Nutrition Facts",
                        modifier = Modifier,
                        style = TextStyle(fontSize = 16.sp),
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Color(0XFFCCCCCC))
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Timer,
                        contentDescription = "",
                        tint = Color(0XFF999999)
                    )
                    Text(
                        text = "Open Cooking Mode",
                        style = TextStyle(fontSize = 16.sp),
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Color(0XFFCCCCCC))
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(Screens.AddNotesScreen.route)
                            bottomsheet = false
                        },
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.StickyNote2,
                        contentDescription = "",
                        tint = Color(0XFF999999)
                    )
                    Text(
                        text = "Add Notes",
                        style = TextStyle(fontSize = 16.sp),
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Color(0XFFCCCCCC))
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Share,
                        contentDescription = "",
                        tint = Color(0XFF999999)
                    )
                    Text(
                        text = "Share",
                        style = TextStyle(fontSize = 16.sp),
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Color(0XFFCCCCCC))
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Print,
                        contentDescription = "",
                        tint = Color(0XFF999999)
                    )
                    Text(
                        text = "Print",
                        style = TextStyle(fontSize = 16.sp),
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Color(0XFFCCCCCC))
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(Screens.FeecbackScreen.route)
                            bottomsheet = false
                        },
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Feedback,
                        contentDescription = "",
                        tint = Color(0XFF999999)
                    )
                    Text(
                        text = "Feedback For The Chef",
                        style = TextStyle(fontSize = 16.sp),
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Color(0XFFCCCCCC))
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.AddComment,
                        contentDescription = "",
                        tint = Color(0XFF999999)
                    )
                    Text(
                        text = "Add To Collections",
                        style = TextStyle(fontSize = 16.sp),
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Color(0XFFCCCCCC))


            }

        }

    Scaffold(bottomBar = {
        BottomAppBar(containerColor = Color(0XFFFFFFFF)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Box(
                    modifier = Modifier
                        .clickable {
                            bottomsheet = true
                            boolea5 = true
                        }
                        .clip(RoundedCornerShape(4.dp))
                        .height(50.dp)
                        .width(140.dp)
                        .border(
                            border = BorderStroke(2.dp, color = Color(0XFFCCCCCC)),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .background(
                            color = if (boolea5) Color(0XFFF58700) else Color.White,
                            shape = RoundedCornerShape(10.dp)
                        ),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircleOutline,
                            contentDescription = "", tint = Color(0XFF999999)
                        )
                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "Cooked?",
                            style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold),
                            fontSize = 18.sp
                        )
                    }

                }

                Spacer(modifier = Modifier.width(20.dp))

                Box(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clickable {}
                        .clip(RoundedCornerShape(4.dp))
                        .height(50.dp)
                        .width(140.dp)
                        .border(
                            border = BorderStroke(2.dp, color = Color(0XFFCCCCCC)),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .background(
                            color = if (boolean4) Color(0XFFFFE4C2) else Color.White,
                            shape = RoundedCornerShape(10.dp)
                        ),
                ) {
                    Text(
                        text = "Start Cooking",
                        modifier = Modifier.align(Alignment.Center),
                        style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold),
                        fontSize = 18.sp
                    )
                }

            }
        }
    }) {
        Column(
            modifier = Modifier
                .background(color = Color(0XFFFFFAF5))
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(483.dp)
            ) {
                AsyncImage(
                    model = meal.meals.strMealThumb,
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
                    Box(
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .size(40.dp)
                            .background(color = Color.White)
                            .clickable { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.Center)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .size(40.dp)
                            .background(color = Color.White)
                            .clickable { }
                    ) {
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
                    text = meal.meals.strMeal,
                    style = TextStyle(fontSize = 19.sp),
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Column(modifier = Modifier.padding(start = 12.dp)) {
                Row() {
                    Text(
                        text = "Category:", color = Color(0XFF666666),
                        style = TextStyle(fontSize = 14.sp),
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = meal.meals.strCategory,
                        color = Color(0XFF666666),
                        style = TextStyle(fontSize = 14.sp),
                        fontWeight = FontWeight.Normal
                    )
                }
                Row() {
                    Text(
                        text = "Area:", color = Color(0XFF666666),
                        style = TextStyle(fontSize = 14.sp),
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = meal.meals.strArea,
                        color = Color(0XFF666666),
                        style = TextStyle(fontSize = 14.sp),
                        fontWeight = FontWeight.Normal
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Box(
                    modifier = Modifier
                        .clickable {
                            boolean = true
                            boolean1 = false
                            boolean2 = false
                        }
                        .height(50.dp)
                        .width(100.dp)
                        .border(
                            border = BorderStroke(2.dp, color = Color(0XFFCCCCCC)),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .background(
                            color = if (boolean) Color(0XFFFFE4C2) else Color.White,
                            shape = RoundedCornerShape(10.dp)
                        ),
                ) {
                    Text(
                        text = "Cookware",
                        modifier = Modifier.align(Alignment.Center),
                        style = TextStyle(fontSize = 15.sp)
                    )
                }
                Box(
                    modifier = Modifier
                        .clickable {
                            boolean = false
                            boolean1 = true
                            boolean2 = false
                        }
                        .clip(RoundedCornerShape(4.dp))
                        .height(50.dp)
                        .width(100.dp)
                        .border(
                            border = BorderStroke(2.dp, color = Color(0XFFCCCCCC)),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .background(
                            color = if (boolean1) Color(0XFFFFE4C2) else Color.White,
                            shape = RoundedCornerShape(10.dp)
                        ),
                ) {
                    Text(
                        text = "Ingredients",
                        modifier = Modifier.align(Alignment.Center),
                        style = TextStyle(fontSize = 15.sp)
                    )
                }
                Box(
                    modifier = Modifier
                        .clickable {
                            boolean = false
                            boolean1 = false
                            boolean2 = true
                        }
                        .clip(RoundedCornerShape(4.dp))
                        .height(50.dp)
                        .width(110.dp)
                        .border(
                            border = BorderStroke(2.dp, color = Color(0XFFCCCCCC)),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .background(
                            color = if (boolean2) Color(0XFFFFE4C2) else Color.White,
                            shape = RoundedCornerShape(10.dp)
                        ),
                ) {
                    Text(
                        text = "Instructions",
                        modifier = Modifier.align(Alignment.Center),
                        style = TextStyle(fontSize = 15.sp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            if (boolean) {
                cookware()
            }
            if (boolean1) {
                Ingredients(meal = meal)
            }
            if (boolean2) {
                Instructions(meal = meal)
            }


        }

    }


}

@Composable
fun cookware() {
    Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
        Text(
            text = "can opener", style = TextStyle(
                fontSize = 20.sp, fontWeight = FontWeight.Medium
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Color(0XFFCCCCCC))

    }


}

@Composable
fun Ingredients(meal: Detail) {
    Column(modifier = Modifier.padding(start = 10.dp, bottom = 82.dp, end = 10.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = meal.meals.strIngredient1)
            Text(
                text = meal.meals.strMeasure1,
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
            Text(text = meal.meals.strIngredient2)
            Text(
                text = meal.meals.strMeasure2,
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
            Text(text = meal.meals.strIngredient3)
            Text(
                text = meal.meals.strMeasure3,
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
            Text(text = meal.meals.strIngredient4)
            Text(
                text = meal.meals.strMeasure4,
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
            Text(text = meal.meals.strIngredient5)
            Text(
                text = meal.meals.strMeasure5,
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
            Text(text = meal.meals.strIngredient6)
            Text(
                text = meal.meals.strMeasure6,
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
            Text(text = meal.meals.strIngredient7)
            Text(
                text = meal.meals.strMeasure7,
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
            Text(text = meal.meals.strIngredient8)
            Text(
                text = meal.meals.strMeasure8,
                color = Color(0XFF666666),
                style = TextStyle(fontSize = 12.sp),
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Color(0XFFCCCCCC))


    }
}


@Composable
fun Instructions(meal: Detail) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 82.dp, start = 10.dp, end = 10.dp),
        elevation = CardDefaults.elevatedCardElevation(3.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0XFFFFE4C2))
    ) {
        Text(
            text = meal.meals.strInstructions,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
            color = MaterialTheme.colorScheme.onBackground, fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }

}

