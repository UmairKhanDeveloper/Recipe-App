package com.example.recipesapp

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddComment
import androidx.compose.material.icons.outlined.Feedback
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Print
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.StickyNote2
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingScreen(navController: NavHostController) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = "Setting",
                style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold)
            )
        })
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(
                    top = it.calculateTopPadding(), bottom = it.calculateTopPadding()
                )
                .padding(10.dp)
        ) {
            Card(
                modifier = Modifier
                    .height(227.dp)
                    .width(398.dp),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0XFFFFE4C2))
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Upgrade to Pro",
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Get exclusive recipes, nutritional+\n information, advanced filters, and more.",
                        fontSize = 13.sp,
                        color = Color(0XFF666666)
                    )
                    Spacer(modifier = Modifier.height(26.dp))
                    Button(
                        onClick = {
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0XFFF58700)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(57.dp)
                    ) {
                        Text(
                            text = "Upgrade Now!",
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }


                }


            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
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
            Row(modifier = Modifier
                .clickable {
                }
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically) {
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

}