package com.example.recipesapp

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNotesScreen(navController: NavHostController) {
    var booleaN by remember { mutableStateOf(false) }
    var booleaN1 by remember { mutableStateOf(false) }
    val context = LocalContext.current

    var textfeild by remember { mutableStateOf("") }
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Add Notes", style = TextStyle(fontSize = 16.sp),
                    fontWeight = FontWeight.Bold
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0XFFFFFAF5)),
            navigationIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    modifier = Modifier.clickable { navController.popBackStack() })
            },
            actions = {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "",
                    modifier = Modifier
                        .rotate(90f)

                )
            })
    }) {
        Column(
            modifier = Modifier
                .padding(top = it.calculateTopPadding(), start = 10.dp)
                .fillMaxSize()
                .background(color = Color(0XFFFFFAF5))
        ) {
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                color = Color(0XFFCCCCCC)
            )
            TextField(
                value = textfeild,
                onValueChange = {
                    textfeild = it
                }, placeholder = {
                    Text(text = "Add Note")
                },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(280.dp))

            Row(
                modifier = Modifier.padding(start = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Box(
                    modifier = Modifier
                        .clickable {
                            booleaN = true
                            booleaN1 = false
                        }
                        .clip(RoundedCornerShape(4.dp))
                        .height(50.dp)
                        .width(150.dp)
                        .border(
                            border = BorderStroke(2.dp, color = Color(0XFFCCCCCC)),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .background(
                            color = if (booleaN) Color(0XFFF58700) else Color.White,
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
                        Text(
                            text = "Cancel",
                            style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold),
                            fontSize = 18.sp
                        )
                    }

                }

                Spacer(modifier = Modifier.width(20.dp))

                Box(
                    modifier = Modifier
                        .clickable {

                            booleaN1 = true
                            booleaN = false
                            navController.popBackStack()
                            Toast
                                .makeText(context, "Notes added", Toast.LENGTH_LONG)
                                .show()
                        }
                        .clip(RoundedCornerShape(4.dp))
                        .height(50.dp)
                        .width(150.dp)
                        .border(
                            border = BorderStroke(2.dp, color = Color(0XFFCCCCCC)),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .background(
                            color = if (booleaN1) Color(0XFFF58700) else Color.White,
                            shape = RoundedCornerShape(10.dp)
                        ),
                ) {
                    Text(
                        text = "Ok",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .clickable {},
                        style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold),
                        fontSize = 18.sp
                    )
                }
            }


        }
    }
}

