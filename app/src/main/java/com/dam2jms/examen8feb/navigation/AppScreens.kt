package com.dam2jms.examen8feb.navigation
sealed class AppScreens (val route: String){

    object FirstScreen: AppScreens(route = "first_screen")
    object SecondScreen: AppScreens(route = "second_screen")
}
