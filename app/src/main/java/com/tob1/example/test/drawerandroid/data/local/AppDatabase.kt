package com.tob1.example.test.drawerandroid.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tob1.example.test.drawerandroid.data.models.People


@Database(entities = [People::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun peopleDao() : PeopleDao

    companion object{
        private const val NAME_DATABASE = "name-data"

        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDataBase(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    NAME_DATABASE)
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}