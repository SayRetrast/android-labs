package com.example.labnine.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie (
    @PrimaryKey(autoGenerate = false) val imdbID: String,
    @ColumnInfo(name = "title") val Title: String,
    @ColumnInfo(name = "year") val Year: String,
    @ColumnInfo(name = "type") val Type: String,
    @ColumnInfo(name = "poster") val Poster: String,
    @ColumnInfo(name = "genre") val Genre: String?
)