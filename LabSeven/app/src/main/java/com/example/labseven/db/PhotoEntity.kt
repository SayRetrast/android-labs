package com.example.labseven.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PhotoEntity (
    @PrimaryKey(autoGenerate = false) val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "url_sq") val url_sq: String,
)