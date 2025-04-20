package com.example.newsnow.data.api

import com.example.newsnow.data.entity.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
//This is like the restaurant's menu that shows what you can order
//It says "Hey, I can get you news headlines if you tell me which country you want news from"
//The @GET is telling us "I'll get this from the URL path 'v2/top-headlines'"
//It needs two things: country and apiKey (like your membership card number)

interface ApiServices {
    @GET("v2/top-headlines")
    suspend fun getNewsHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String ="48c3cb12387c4cd88245b001c7428134"
    ): Response<NewsResponse>
}

//48c3cb12387c4cd88245b001c7428134
//GET https://newsapi.org/v2/top-headlines?country=us&apiKey=48c3cb12387c4cd88245b001c7428134