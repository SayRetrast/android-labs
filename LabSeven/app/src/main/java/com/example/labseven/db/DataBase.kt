package com.example.labseven.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.labseven.MyApplication

@Database(entities = [PhotoEntity::class], version = 4)
abstract class AppDatabase: RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}

val myApplicationInstance = MyApplication

val db = Room.databaseBuilder(
    myApplicationInstance.applicationContext(),
    AppDatabase::class.java, "gallery"
).allowMainThreadQueries().fallbackToDestructiveMigration().build()