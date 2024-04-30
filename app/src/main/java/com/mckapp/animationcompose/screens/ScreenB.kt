package com.mckapp.animationcompose.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.mckapp.animationcompose.util.colorGreen

@Composable
fun ScreenB(
    navController: NavController
) {
    CommonScreen(
        onClick = {
            navController.popBackStack()
        },
        icon = Icons.Rounded.ArrowBack,
        label = "Screen B",
        backgroundColor = colorGreen
    )
}