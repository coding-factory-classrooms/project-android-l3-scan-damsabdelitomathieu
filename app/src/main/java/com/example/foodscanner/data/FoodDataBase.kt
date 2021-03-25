package com.example.foodscanner.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Food::class], version = 1, exportSchema = false)
abstract class FoodDataBase: RoomDatabase() {

    abstract fun foodDao(): FoodDao

    companion object {
        @Volatile
        private var INSTANCE: FoodDataBase? = null

        fun getDataBase(context: Context): FoodDataBase {
            val tempInstence = INSTANCE
            if (tempInstence != null) {
                return tempInstence
            }

            synchronized(this) {
                Log.i("DATABASE", "initialisation de FoodDataBase")
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FoodDataBase::class.java,
                    "food_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}