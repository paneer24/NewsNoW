package com.example.newsnow.ui.Screens
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsnow.ui.Component.Loader
import com.example.newsnow.ui.Component.NewsList
import com.example.newsnow.ui.Component.NewsRowComponent
import com.example.newsnow.ui.Component.ShimmerNewsCardItem
import com.example.newsnow.ui.ViewModels.NewsViewModel
import com.example.utilities.ResourceState


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    newsViewModel: NewsViewModel = hiltViewModel()
) {
    val newsRes by newsViewModel.news.collectAsState()

    val pagerState = rememberPagerState(initialPage = 0) { 100 }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        VerticalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
                .padding(top = 24.dp),

            pageSpacing = 16.dp
        ) { page ->
            when (newsRes) {
                is ResourceState.Loading -> {
                    ShimmerNewsCardItem()
                }
                is ResourceState.Success -> {
                    val response = (newsRes as ResourceState.Success).data
                    if (response.articles.isNotEmpty()) {
                        NewsRowComponent(page, response.articles[page % response.articles.size])
                    }
                }
                is ResourceState.Error -> {
                    ErrorComponent(
                        message = (newsRes as ResourceState.Error).error.toString()
                    )
                }
            }
        }
    }
}
@Composable
fun ErrorComponent(message: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Error: $message",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.error
        )
    }
}
@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
