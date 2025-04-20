package com.example.newsnow.data.datasource

import com.example.newsnow.data.entity.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsDataSource {
    @GET("v2/top-headlines")
    suspend fun getNewsHeadlines(
        country: String,

    ): Response<NewsResponse>
}
