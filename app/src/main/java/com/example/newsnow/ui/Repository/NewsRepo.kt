package com.example.newsnow.ui.Repository

import com.example.newsnow.data.datasource.NewsDataSource
import com.example.newsnow.data.entity.NewsResponse
import com.example.utilities.ResourceState
import com.example.utilities.ResourceState.Error
import com.example.utilities.ResourceState.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class NewsRepo @Inject constructor(
    private val newsDataSource: NewsDataSource)
{
fun getNewsHeadlines(country: String): Flow<ResourceState<NewsResponse>> {
    return flow{
emit(     ResourceState.Loading())

        val response=newsDataSource.getNewsHeadlines(country)
        if (response.isSuccessful && response.body()!=null){
            emit(ResourceState.Success(response.body()!!))
        }else{
            emit(ResourceState.Error("Error fetching news data"))
        }
    }
        .catch { e ->
            emit(ResourceState.Error(e.localizedMessage ?: "Error fetching news data"))
        }
}
}