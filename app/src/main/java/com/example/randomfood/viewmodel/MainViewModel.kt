package com.example.randomfood.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.randomfood.data.database.AppDatabase
import com.example.randomfood.data.database.model.FoodDbModel
import com.example.randomfood.data.repository.RoomRepository
import com.example.randomfood.domain.FoodModel
import com.example.randomfood.routing.JetFoodRouter
import com.example.randomfood.routing.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<FoodDbModel>>
    private val repository: RoomRepository

    init {
        val foodDao = AppDatabase.getInstance(application).roomDao()
        repository = RoomRepository(roomDao = foodDao)
        readAllData = repository.readAllData
    }



    // Navigate FoodDetail
    private var _foodEntry = MutableLiveData(FoodModel())
    fun onNoteClick(food: FoodModel){
        _foodEntry.value = food
        JetFoodRouter.navigateTo(Screen.FoodDetail)
    }


    fun addFood(item: List<FoodDbModel>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFood(item = item)
        }
    }

    fun updateFood(item: FoodDbModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFood(item)
        }
    }

    fun deleteFood(item: FoodDbModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFood(item = item)
        }
    }

    fun deleteAllRecord() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllRecord()
        }
    }
}
