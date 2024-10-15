package com.example.newscompose.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NewsDao {
    @Query("SELECT * FROM news_articles")
    suspend fun getAllNews(): List<NewsArticle>
    @Query("SELECT * FROM news_articles WHERE is_saved = 1")
    suspend fun getSavedNews(): List<NewsArticle>
    //@Query("UPDATE news_articles SET is_saved = is_saved WHERE title = title")
   // suspend fun updateNews(news:NewsArticle)
    @Update(entity = NewsArticle::class,onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNews(newsSaved: NewsSaveState)
    @Update(entity = NewsArticle::class)
    suspend fun updateAll(newsSaved: List<NewsSaveState>)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAllNews(news: List<NewsArticle>)

}