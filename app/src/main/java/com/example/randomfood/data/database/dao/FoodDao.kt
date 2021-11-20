package com.example.randomfood.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.randomfood.data.database.model.FoodDbModel

@Dao
interface FoodDao {
    @Query("SELECT * FROM FoodDb ")
    fun getAllRooms(): LiveData<List<FoodDbModel>>


    @Query("SELECT * FROM FoodDb where id = :id")
    fun getById(id: Int): FoodDbModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE) //conflict the data
    suspend fun insert(item : List<FoodDbModel>)

    @Update
    suspend fun update(item: FoodDbModel)

    @Delete
    suspend fun delete(item: FoodDbModel)

    @Query("DELETE FROM FoodDb")
    suspend fun deleteAllRecord()


    @Query("SELECT * FROM FoodDb ORDER BY RANDOM() LIMIT 5")
    suspend fun getFiveRandomFood(): List<FoodDbModel?>?
}