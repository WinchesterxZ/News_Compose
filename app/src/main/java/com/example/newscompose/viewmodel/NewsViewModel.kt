package com.example.newscompose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newscompose.NewsApplication
import com.example.newscompose.data.NewsArticle
import com.example.newscompose.data.NewsDatabase
import com.example.newscompose.data.NewsSaveState
import com.example.newscompose.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {
    val newsList = mutableStateListOf<NewsArticle>()
    var isLoading by mutableStateOf(false)
    private var newsDao = NewsDatabase.getDaoInstance(NewsApplication.getInstance())

    init {
        fetchNews()
    }

    fun toggleBookmark(articleTitle : String) {
        val newList = newsList.toMutableList()
        val itemIndex = newList.indexOfFirst { it.title == articleTitle }
        newList[itemIndex] = newList[itemIndex].copy(isSaved = !newList[itemIndex].isSaved)
        viewModelScope.launch {
           val updatedNews = saveFavoriteNews(articleTitle, newList[itemIndex].isSaved)
            newsList.clear()
            newsList.addAll(updatedNews)
        }
    }
    private suspend fun saveFavoriteNews(title: String, isSaved: Boolean) = withContext(Dispatchers.IO) {
        newsDao.updateNews(
            NewsSaveState(
                title = title,
                isSaved = isSaved
            )
        )
        return@withContext newsDao.getAllNews()
    }

    private suspend fun updateLocalDatabase(query: String = "Sports",
    ){
        val response = newsRepository.getNews(
            query = query,
            page = 1
        )
        val favoriteNews = newsDao.getSavedNews()
        newsDao.addAllNews(response.articles)
        newsDao.updateAll(
            favoriteNews.map {
                NewsSaveState(
                    title = it.title,
                    true,
                )
            }
        )
    }

     private fun fetchNews(
    ) {
         if (isLoading) return // Prevent duplicate calls if already loading

         viewModelScope.launch {
             isLoading = true
             try {
                 updateLocalDatabase()
             } catch (e: Exception) {
                 if(newsDao.getAllNews().isEmpty()){
                     throw e
                 }
             } finally {
                 isLoading = false
                 newsList.addAll(newsDao.getAllNews())
             }
         }
    }
}