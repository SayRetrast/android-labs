package com.example.labsixth.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.labsixth.db.entities.Crime

@Dao
interface CrimeDao {
    @Query("SELECT * FROM crime")
    fun getAll(): List<Crime>

    @Query("SELECT * FROM crime WHERE crime.id = :crimeId")
    fun getCrimeById(crimeId: Int): Crime

    @Insert
    fun insert(vararg crime: Crime)

    @Delete
    fun delete(crime: Crime)
}