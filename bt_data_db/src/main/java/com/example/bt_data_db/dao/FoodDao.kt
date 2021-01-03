package com.example.bt_data_db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bt_data_db.database_models.DatabaseFood

@Dao
interface FoodDao {
    @Query("SELECT * FROM databasefood")
    fun getFoodList(): LiveData<List<DatabaseFood>>

    @Query("SELECT * FROM databasefood WHERE category LIKE :category")
    fun getFoodListByCategory(category: String): LiveData<List<DatabaseFood>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg foods: DatabaseFood)

    @Query("DELETE FROM databasefood")
    fun deleteAll()
}