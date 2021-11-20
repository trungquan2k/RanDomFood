package com.example.randomfood.domain


const val NEW_FOOD_ID = -1L
const val NEW_PRICE=10000
/**
 * Model class that represents one Note
 */
data class FoodModel(
    val id: Long = NEW_FOOD_ID,
    val name: String = "",
    val price: Int = NEW_PRICE,
    val image: String? = null,
)
