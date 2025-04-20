package com.example.newsnow.di

import com.example.newsnow.data.AppConstants
import com.example.newsnow.data.api.ApiServices
import com.example.newsnow.data.datasource.NewsDataSource
import com.example.newsnow.data.datasource.NewsDataSourceImplementation
import com.example.newsnow.ui.Repository.NewsRepo
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

//Let me explain this AppModule in simple terms. Think of it as a factory that creates all the tools needed for your app to fetch news data.
//we use modules to define 2rd party depencies
@Module
@InstallIn(SingletonComponent::class)//this enables those dependencies all over the app
class AppModule {
    @Provides
    @Singleton
    //we have created one dependency and one retrofot object and we can use this anywhere in our app and dagger will inject this object after doing all the work it is decoupled so we can use it anywhere
fun provideRetrofit(): Retrofit {

        val httpLoggingInterceptor=HttpLoggingInterceptor().apply {
            level=HttpLoggingInterceptor.Level.BASIC
        }
    val httpClient= OkHttpClient().newBuilder().addInterceptor(httpLoggingInterceptor)

    httpClient.apply {
        readTimeout(60, TimeUnit.SECONDS)
    }
val moshi= Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    return Retrofit.Builder()
        .baseUrl(AppConstants.APP_BASE_URL)
        .client(httpClient.build())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()


}
//    This is creating a waiter who knows:
//
//    How to take your order
//    How to bring it back to you
//    The restaurant's menu (API endpoints)
    @Provides
    @Singleton
    fun provideApiServices(retrofit: Retrofit): ApiServices {
        //this will create an instance of api services
        return retrofit.create(ApiServices::class.java)


    }


//    This is like having a manager who:
//
//    Takes orders from customers
//    Passes them to the waiter
//    Makes sure everything runs smoothly
    @Provides
    @Singleton
    fun providesNewsDataSource(apiServices: ApiServices): NewsDataSource {
        return  NewsDataSourceImplementation(apiServices)
    }


//    This is like the delivery person who:
//
//    Gets the food from the restaurant
//    Delivers it to your home
//    Handles any delivery issues
    @Provides
    @Singleton
    fun providesNewsRepository(newsDataSource: NewsDataSource): NewsRepo {
        return  NewsRepo(newsDataSource)
    }
}












//Let me explain this AppModule like setting up a restaurant kitchen from scratch.
//
//The Module Setup:
//
//kotlinCopy@Module
//@InstallIn(SingletonComponent::class)
//class AppModule
//This is like saying "Here's our kitchen setup manual, and we'll use these tools throughout our entire restaurant (app)"
//
//Setting up the Network (provideRetrofit):
//
//kotlinCopy@Provides
//@Singleton
//fun provideRetrofit(): Retrofit {
//    // Logging setup
//    val httpLoggingInterceptor = HttpLoggingInterceptor()...
//    // Client setup
//    val httpClient = OkHttpClient()...
//    // JSON handler setup
//    val moshi = Moshi.Builder()...
//
//    return Retrofit.Builder()...
//}
//This is like setting up the entire kitchen infrastructure:
//
//httpLoggingInterceptor: Like a security camera that watches all communication
//httpClient: Like the main kitchen setup with rules (60 seconds timeout)
//moshi: Like a translator who converts between different languages (JSON to Kotlin)
//The final Retrofit.Builder: Like assembling all kitchen equipment together
//
//
//Creating API Service (provideApiServices):
//
//kotlinCopy@Provides
//@Singleton
//fun provideApiServices(retrofit: Retrofit): ApiServices {
//    return retrofit.create(ApiServices::class.java)
//}
//This is like hiring a chef (ApiServices) who knows how to use all the kitchen equipment (retrofit)
//
//Setting up Data Source (providesNewsDataSource):
//
//kotlinCopy@Provides
//@Singleton
//fun providesNewsDataSource(apiServices: ApiServices): NewsDataSource {
//    return NewsDataSourceImplementation(apiServices)
//}
//This is like assigning a manager (NewsDataSource) who knows how to work with the chef (ApiServices)
//
//Creating Repository (providesNewsRepository):
//
//kotlinCopy@Provides
//@Singleton
//fun providesNewsRepository(newsDataSource: NewsDataSource): NewsRepo {
//    return NewsRepo(newsDataSource)
//}
//This is like creating the main order processing system (NewsRepo) that works with the manager (NewsDataSource)
//The Flow in Real Terms:
//CopyRestaurant Setup (AppModule)
//↓
//Kitchen Equipment (Retrofit)
//↓
//Chef (ApiServices)
//↓
//Manager (NewsDataSource)
//↓
//Order System (NewsRepo)
//Important Concepts:
//
//@Singleton:
//
//Like saying "We only need one of these in our entire restaurant"
//Saves resources by reusing the same instance
//
//
//@Provides:
//
//Like a recipe that tells how to create each component
//Dagger Hilt follows these recipes when something needs these components
//
//
//Dependencies Chain:
//
//kotlinCopyprovideRetrofit() → provideApiServices() → providesNewsDataSource() → providesNewsRepository()
//Each function needs the previous one's output, like a cooking process:
//CopySetup Kitchen → Hire Chef → Assign Manager → Create Order System