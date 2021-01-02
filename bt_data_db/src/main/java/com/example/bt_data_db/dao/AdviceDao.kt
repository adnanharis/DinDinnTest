package com.example.bt_data_db.database_models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AdviceDao {
    @Query("SELECT * FROM databaseadvice ORDER BY id DESC LIMIT 1")
    fun getLatestAdvice(): LiveData<DatabaseAdvice>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(advice: DatabaseAdvice)
}