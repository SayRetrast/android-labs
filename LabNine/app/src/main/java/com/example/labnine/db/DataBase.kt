package com.example.labnine.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.labnine.MyApplication

@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}

val myApplicationInstance = MyApplication

val db = Room.databaseBuilder(
    myApplicationInstance.applicationContext(),
    AppDatabase::class.java, "movie"
).allowMainThreadQueries().fallbackToDestructiveMigration().build()