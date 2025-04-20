package com.example.newsnow.data.datasource

import com.example.newsnow.data.api.ApiServices
import com.example.newsnow.data.entity.NewsResponse
import retrofit2.Response
import javax.inject.Inject

class NewsDataSourceImplementation @Inject constructor(private val apiService: ApiServices):NewsDataSource {
    override suspend fun getNewsHeadlines(country: String): Response<NewsResponse> {
return apiService.getNewsHeadlines(country)
    }
//we have an interface api service which provide us some response
    //we create a data source implementation with respective data source of that function

}//inside the data source implementation we are injecting api service dependenci and calling the respective function


//class NewsDataSourceImplementation - This is creating a new class
//@Inject constructor - This tells Dagger Hilt "When someone needs a NewsDataSource, you can create this class, but first..."
//private val apiService: ApiServices - "...I need you to give me an ApiServices to work with"
//: NewsDataSource - "I promise to implement everything defined in NewsDataSource interface"
//
//Think of it like a Chef (NewsDataSourceImplementation) who needs kitchen equipment (apiService) to cook:
//
//The @Inject is like telling the restaurant manager "When you hire a chef, make sure they have their kitchen equipment"
//private val apiService is like the chef's personal set of tools