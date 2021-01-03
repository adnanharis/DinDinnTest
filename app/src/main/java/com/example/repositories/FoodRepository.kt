package com.example.repositories

import androidx.lifecycle.LiveData
import com.example.bt_data_db.AppDatabase
import com.example.bt_data_db.database_models.FoodDaoWrapper
import com.example.nbt_data_remote.Network
import com.example.nbt_data_remote.data_transfer_models.asDomainModel
import com.example.nbt_domain.models.Food
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class FoodRepository(private val database: AppDatabase) {

    val foodList: LiveData<List<Food>> = FoodDaoWrapper.getFoodList(database)

    /**
     * Refresh the data stored in the offline cache.
     */
    suspend fun refresh() {
        // On background thread
        withContext(Dispatchers.IO) {
            try {
                val networkFood = Network.foodService.getFoodListAsync().await()
                FoodDaoWrapper.update(database, networkFood.asDomainModel())
            } catch (t: Throwable) {
                // Network error
                Timber.e(t)
            }
        }
    }
}