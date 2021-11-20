package com.example.randomfood.routing

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * Class defining all possible screens in the app.
 */
sealed class Screen {
    object Home : Screen()
    object FoodDetail: Screen()
}

/**
 * Allows you to change the screen in the [MainActivity]
 *
 * Also keeps track of the current screen.
 */
object JetFoodRouter {
    var currentScreen: Screen by mutableStateOf(Screen.Home)

    fun navigateTo(destination: Screen) {
        currentScreen = destination
    }
}
