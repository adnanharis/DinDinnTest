package com.example.repositories

import androidx.lifecycle.LiveData
import com.example.bt_data_db.AppDatabase
import com.example.bt_data_db.database_models.AdviceDaoWrapper
import com.example.nbt_data_remote.Network
import com.example.nbt_data_remote.data_transfer_models.asDomainModel
import com.example.nbt_domain.models.Advice
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class AdviceRepository(private val database: AppDatabase) {

    val advice: LiveData<Advice> = AdviceDaoWrapper.getLatestAdvice(database)

    /**
     * Refresh the data stored in the offline cache.
     */
    suspend fun refresh() {
        // On background thread
        withContext(Dispatchers.IO) {
            try {
                val networkAdvice = Network.adviceService.getTodaysMessage().await()
                AdviceDaoWrapper.insert(database, networkAdvice.asDomainModel())
            } catch (t: Throwable) {
                // Network error
                Timber.e(t)
            }
        }
    }
}