package com.example.bt_data_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bt_data_db.dao.FoodDao
import com.example.bt_data_db.database_models.DatabaseFood


@Database(entities = [DatabaseFood::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val foodDao: FoodDao
}

private lateinit var INSTANCE: AppDatabase

fun getDatabase(context: Context): AppDatabase {
    synchronized(AppDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_db"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
    return INSTANCE
}
