package com.example.newscompose.network

import com.example.newscompose.data.NewsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("v2/everything")
    suspend fun getNewsArticles(
        @Query("q") query : String,
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int =1,
        @Query("pageSize") pageSize :Int =10
    ): NewsResponse
}

object RetrofitInstance{
    val api:NewsApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)
    }
}