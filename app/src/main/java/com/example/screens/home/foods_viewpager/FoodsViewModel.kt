package com.example.screens.home.foods_viewpager

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.bt_data_db.getDatabase
import com.example.repositories.FoodRepository
import kotlinx.coroutines.launch

class FoodsViewModel(application: Application, private val category: String) :
    AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val foodRepository = FoodRepository(database, category)

    init {
        viewModelScope.launch {
            foodRepository.refresh()
        }
    }

    val foodList = foodRepository.foodList

    class Factory(private val app: Application, private val category: String) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FoodsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FoodsViewModel(app, category) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}