package com.example.labsixth.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Crime (
    @PrimaryKey() val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "date") val date: Date?,
    @ColumnInfo(name = "is_solver") val isSolved: Boolean = false
)