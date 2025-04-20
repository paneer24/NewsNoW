package com.example.newsnow.ui.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsnow.data.AppConstants
import com.example.newsnow.data.entity.NewsResponse
import com.example.newsnow.ui.Repository.NewsRepo
import com.example.utilities.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepo: NewsRepo
): ViewModel() {
private val _news : MutableStateFlow<ResourceState<NewsResponse>> = MutableStateFlow(ResourceState.Loading())
    val news : StateFlow<ResourceState<NewsResponse>> = _news

    init {
        getNews(AppConstants.COUNTRY)
    }
     private fun getNews(country:String){
         viewModelScope.launch (Dispatchers.IO){
             newsRepo.getNewsHeadlines(country)
                 .collectLatest{//it is used to collect flow
newsResponse -> _news.value=newsResponse


                 }
         }

    }


companion object{
    const val TAG="NewsViewModel"
 }
}
