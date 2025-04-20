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

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
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
    @Provides
    @Singleton
    fun provideApiServices(retrofit: Retrofit): ApiServices {
        return retrofit.create(ApiServices::class.java)
    }
    @Provides
    @Singleton
    fun providesNewsDataSource(apiServices: ApiServices): NewsDataSource {
        return  NewsDataSourceImplementation(apiServices)
    }
    @Provides
    @Singleton
    fun providesNewsRepository(newsDataSource: NewsDataSource): NewsRepo {
        return  NewsRepo(newsDataSource)
    }
}
