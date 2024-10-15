package com.example.newscompose.data

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class NewsSaveState(
    val title: String,
    @ColumnInfo(name = "is_saved")
    val isSaved: Boolean = false
)
