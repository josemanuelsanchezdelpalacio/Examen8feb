package com.dam2jms.examen8feb.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dam2jms.examen8feb.models.ViewModelFirstScreen
import com.dam2jms.examen8feb.screens.FirstScreen
import com.dam2jms.examen8feb.screens.SecondScreen

@Composable
fun appNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.FirstScreen.route) {
        composable(route = AppScreens.FirstScreen.route) { FirstScreen(navController, mvvm = ViewModelFirstScreen()) }
        composable(route = AppScreens.SecondScreen.route + "/{text}", arguments = listOf(navArgument(name = "text") {type= NavType.StringType})){ SecondScreen(navController, mvvm = ViewModelFirstScreen(), it.arguments?.getString("text"))}
    }
}
