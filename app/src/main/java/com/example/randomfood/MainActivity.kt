package com.example.randomfood


import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.example.randomfood.appdrawer.HomePageScreen
import com.example.randomfood.routing.JetFoodRouter
import com.example.randomfood.routing.Screen
import com.example.randomfood.screen.FoodDetail
import com.example.randomfood.screen.ListFoodsItem
import com.example.randomfood.ui.theme.RandomFoodTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedObjectState: Bundle?) {
        super.onCreate(savedObjectState)
        setContent {
            RandomFoodTheme {
                HomePageScreen()
            }
        }
    }
}

@Composable
private fun MainActivityScreen(){
    Surface{
        when(JetFoodRouter.currentScreen){
            is Screen.Home -> ListFoodsItem()
            is Screen.FoodDetail-> FoodDetail()
        }
    }
}