package com.example.labseven.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PhotoDao {
    @Query("SELECT * FROM photoentity")
    fun getAll(): List<PhotoEntity>

    @Query("SELECT * FROM photoentity WHERE photoentity.id = :photoId")
    fun getPhotoById(photoId: String): PhotoEntity

    @Insert
    fun insert(vararg photoEntity: PhotoEntity)

    @Delete
    fun delete(photoEntity: PhotoEntity)

    @Query("DELETE FROM photoentity")
    fun deleteAll()
}