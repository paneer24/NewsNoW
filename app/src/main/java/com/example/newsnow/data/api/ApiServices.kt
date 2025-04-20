package com.example.newsnow.data.api

import com.example.newsnow.data.entity.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiServices {
    @GET("v2/top-headlines")
    suspend fun getNewsHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String ="48c3cb12387c4cd88245b001c7428134"
    ): Response<NewsResponse>
}

