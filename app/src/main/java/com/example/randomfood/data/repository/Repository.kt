package com.example.randomfood.data.repository

import androidx.lifecycle.LiveData
import com.example.randomfood.data.database.model.FoodDbModel

interface Repository {

    fun getAllFood(): LiveData<List<FoodDbModel>>

    fun insert(food: FoodDbModel)

    fun update(food: FoodDbModel)

    fun deleteAll()
}