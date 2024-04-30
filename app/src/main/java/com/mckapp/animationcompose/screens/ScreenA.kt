package com.mckapp.animationcompose.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.mckapp.animationcompose.navigation.Screen
import com.mckapp.animationcompose.util.colorBlue

@Composable
fun ScreenA(
    navController: NavController
) {
    CommonScreen(
        onClick = {
            navController.navigate(route = Screen.ScreenB.name)
        },
        icon = Icons.Rounded.ArrowForward,
        label = "Screen A",
        backgroundColor = colorBlue
    )
}