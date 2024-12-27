package com.example.labnine.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getAll(): List<Movie>

    @Insert
    fun insert(vararg movie: Movie)

    @Query("DELETE FROM Movie WHERE imdbID IN (:ids)")
    fun deleteMoviesByIds(ids: List<String>)
}