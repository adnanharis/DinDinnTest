package com.example.bt_data_db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bt_data_db.database_models.DatabaseFood

@Dao
interface FoodDao {
    @Query("SELECT * FROM databasefood")
    fun getFoodList(): LiveData<List<DatabaseFood>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg foods: DatabaseFood)

    @Query("DELETE FROM databasefood")
    fun deleteAll()
}