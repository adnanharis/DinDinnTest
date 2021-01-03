package com.example.bt_data_db.database_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bt_data_db.AppDatabase
import com.example.nbt_domain.models.Food

/**
 * Database related models for domain models
 */

@Entity
data class DatabaseFood(
    @PrimaryKey
    val id: String,
    val name: String,
    val imageUrl: String,
    val category: String
)

private fun List<DatabaseFood>.asDomainModel(): List<Food> {
    return map {
        Food(it.id, it.name, it.imageUrl, it.category)
    }
}

private fun List<Food>.toDatabaseModel(): Array<DatabaseFood> {
    return map {
        DatabaseFood(it.id, it.name, it.imageUrl, it.category)
    }.toTypedArray()
}

/**
 * Converts database models to domain models. Also protects database access from outside.
 */
class FoodDaoWrapper {

    companion object {
        fun getFoodList(database: AppDatabase): LiveData<List<Food>> {
            val data = database.foodDao.getFoodList()
            return Transformations.map(data) {
                it?.asDomainModel()
            }
        }

        fun getFoodListByCategory(database: AppDatabase, category: String): LiveData<List<Food>> {
            val data = database.foodDao.getFoodListByCategory(category)
            return Transformations.map(data) {
                it?.asDomainModel()
            }
        }

        fun update(database: AppDatabase, foods: List<Food>) {
            database.foodDao.insertAll(*foods.toDatabaseModel())
        }
    }
}