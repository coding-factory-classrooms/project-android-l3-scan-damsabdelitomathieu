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
<<<<<<< HEAD
                Log.i("DATABASE", "initialisation de FoodDataBase")
=======
>>>>>>> test
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FoodDataBase::class.java,
                    "food_database"
<<<<<<< HEAD
                ).build()
=======
                ).allowMainThreadQueries().build()
>>>>>>> test

                INSTANCE = instance
                return instance
            }
        }
    }
}