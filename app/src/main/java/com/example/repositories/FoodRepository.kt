package com.example.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bt_data_db.AppDatabase
import com.example.bt_data_db.database_models.FoodDaoWrapper
import com.example.nbt_data_remote.Network
import com.example.nbt_data_remote.data_transfer_models.asDomainModel
import com.example.nbt_domain.models.Food
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class FoodRepository(private val database: AppDatabase, private val category: String) {

    val foodList: LiveData<List<Food>> = FoodDaoWrapper.getFoodListByCategory(database, category)

    /**
     * Refresh the data stored in the offline cache.
     */
    suspend fun refresh() {
        // On background thread
        withContext(Dispatchers.IO) {
            try {
                val networkFood = Network.foodService.getFoodListAsync(category).await()
                FoodDaoWrapper.update(database, networkFood.asDomainModel(category))
            } catch (t: Throwable) {
                // Network error
                Timber.e(t)
            }
        }
    }
}