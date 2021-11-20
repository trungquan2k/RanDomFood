package com.example.randomfood.data.repository

import androidx.lifecycle.LiveData
import com.example.randomfood.data.database.dao.FoodDao
import com.example.randomfood.data.database.model.FoodDbModel

class RoomRepository(private val roomDao: FoodDao) {

    val readAllData: LiveData<List<FoodDbModel>> = roomDao.getAllRooms()

    suspend fun addFood(item: List<FoodDbModel>) {
        roomDao.insert(item)
    }

    suspend fun updateFood(item: FoodDbModel) {
        roomDao.update(item)
    }

    suspend fun deleteFood(item: FoodDbModel) {
        roomDao.delete(item)
    }

    suspend fun deleteAllRecord() {
        roomDao.deleteAllRecord()
    }

    suspend fun getRandomFood() {
        roomDao.getFiveRandomFood()
    }

}