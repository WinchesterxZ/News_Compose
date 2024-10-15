package com.example.newscompose.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "news_articles")
data class NewsArticle(
    @PrimaryKey
    val title: String,
    val description: String,
    @ColumnInfo(name = "image_url")
    @SerializedName("urlToImage")
    val imageUrl: String,
    val url: String,
    @ColumnInfo(name = "is_saved")
    @Expose(serialize = false, deserialize = false) // Retrofit won't process this field
    val isSaved: Boolean = false
)
