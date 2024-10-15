package com.example.newscompose.repository

import com.example.newscompose.data.NewsResponse
import com.example.newscompose.network.NewsApiService

class NewsRepository(
    private val newsApiService: NewsApiService

) {
    suspend fun getNews(query:String , page:Int): NewsResponse{
        return newsApiService.getNewsArticles(
            query = query,
            page =1,
            apiKey = "",
            pageSize = 10
        )

    }
}