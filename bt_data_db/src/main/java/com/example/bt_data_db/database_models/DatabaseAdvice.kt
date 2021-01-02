package com.example.bt_data_db.database_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bt_data_db.AppDatabase
import com.example.nbt_domain.models.Advice

/**
 * Database related models for domain models
 */

@Entity
data class DatabaseAdvice(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val advice: String
)

fun DatabaseAdvice.asDomainModel(): Advice {
    return Advice(id, advice)
}

fun Advice.toDatabaseAdvice() = DatabaseAdvice(id, message)

/**
 * Converts database models to domain models. Also protects database access from outside.
 */
class AdviceDaoWrapper {

    companion object {
        fun getLatestAdvice(database: AppDatabase): LiveData<Advice> {
            val data = database.adviceDao.getLatestAdvice()
            return Transformations.map(data) {
                it?.asDomainModel()
            }
        }

        fun insert(database: AppDatabase, advice: Advice) {
            database.adviceDao.insert(advice.toDatabaseAdvice())
        }
    }
}