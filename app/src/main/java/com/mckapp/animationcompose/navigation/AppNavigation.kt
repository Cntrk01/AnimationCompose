package com.mckapp.animationcompose.navigation

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mckapp.animationcompose.screens.ScreenA
import com.mckapp.animationcompose.screens.ScreenB

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController, 
        startDestination = Screen.ScreenA.name) 
    {
        composable(
            route = Screen.ScreenA.name
        ){
            ScreenA(navController = navController)
        }

        composable(
            route = Screen.ScreenB.name,
            enterTransition = {
                //fadeIn()
                //slideInHorizontally(
                //                    initialOffsetX = {-it},
                //                    animationSpec = tween(
                //                        300,
                //                        easing = LinearEasing
                //                    )
                //                )
//                slideInVertically(
//                    initialOffsetY = {-it},
//                    animationSpec = tween(
//                        300,
//                        easing = FastOutLinearInEasing
//                    )
//                )
                  //scaleIn()
                expandVertically()
            },
            exitTransition = {
                //fadeOut()
                //slideOutHorizontally(
                //                    targetOffsetX = {-it},
                //                    animationSpec = tween(
                //                        300,
                //                        easing = FastOutLinearInEasing
                //                    )
                //                )
//                slideOutVertically(
//                    targetOffsetY = {-it},
//                    animationSpec = tween(
//                        300,
//                        easing = FastOutLinearInEasing
//                    )
//                )
                //scaleOut()
                shrinkVertically()
            }
        ){
            ScreenB(navController = navController)
        }
    }
}