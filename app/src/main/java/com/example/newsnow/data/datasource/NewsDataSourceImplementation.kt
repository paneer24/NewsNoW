package com.example.newsnow.data.datasource

import com.example.newsnow.data.api.ApiServices
import com.example.newsnow.data.entity.NewsResponse
import retrofit2.Response
import javax.inject.Inject

class NewsDataSourceImplementation @Inject constructor(private val apiService: ApiServices):NewsDataSource {
    override suspend fun getNewsHeadlines(country: String): Response<NewsResponse> {
return apiService.getNewsHeadlines(country)
    }

}
