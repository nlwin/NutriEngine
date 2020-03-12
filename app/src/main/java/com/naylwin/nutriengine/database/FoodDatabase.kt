package com.naylwin.eatrition.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Food::class, User::class, UserActivity::class], version=1, exportSchema = false)
abstract class FoodDatabase : RoomDatabase() {
    abstract val foodDao : FoodDao
    abstract val userDao : UserDao
    abstract val userActivityDao : UserActivityDao

    companion object {
        @Volatile
        private var INSTANCE : FoodDatabase? = null
        fun getInstance(context: Context): FoodDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FoodDatabase::class.java,
                        "food_history_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}

