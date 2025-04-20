package com.example.newsnow.data.entity

data class NewsResponse(
    val status:String,
    val totalResults:Int,
    val articles:List<Article>

)
data class Article(
    val author: String?,        // Made nullable
    val title: String,         // Usually not null
    val description: String?,   // Made nullable
    val url: String,           // Usually not null
    val urlToImage: String?,    // Made nullable
    val publishedAt: String,    // Usually not null
    val content: String?,       // Made nullable
    val source: Source?

)
data class Source(
    val id:String?,
    val name:String

)
