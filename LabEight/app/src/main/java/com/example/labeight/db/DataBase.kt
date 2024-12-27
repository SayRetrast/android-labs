package com.example.labeight.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.labeight.MyApplication

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}

val myApplicationInstance = MyApplication

val db = Room.databaseBuilder(
    myApplicationInstance.applicationContext(),
    AppDatabase::class.java, "todo"
).allowMainThreadQueries().fallbackToDestructiveMigration().build()