package com.example.labsixth.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.labsixth.MyApplication
import com.example.labsixth.db.daos.CrimeDao
import com.example.labsixth.db.entities.Crime

@Database(entities = [Crime::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun crimeDao(): CrimeDao
}

val myApplicationInstance = MyApplication

val db = Room.databaseBuilder(
    myApplicationInstance.applicationContext(),
    AppDatabase::class.java, "database-name"
).build()