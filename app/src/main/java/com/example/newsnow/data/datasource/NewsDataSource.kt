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
//This is like a simplified version of the order pad
//Notice it only asks for the country - it's hiding the complexity of the apiKey
//It's an interface, meaning it just defines what we can do, not how to do it