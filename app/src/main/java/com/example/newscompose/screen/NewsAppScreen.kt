package com.example.newscompose.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newscompose.data.NewsArticle
import com.example.newscompose.screen.componants.CustomAppBar
import com.example.newscompose.screen.componants.NewsItem
import com.example.newscompose.screen.componants.SearchBarM3
import com.example.newscompose.viewmodel.NewsViewModel

@Composable
fun NewsApp(viewModel: NewsViewModel){
    val newsList = viewModel.newsList
    val isLoading = viewModel.isLoading
    var isSearchActive by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()

    Scaffold(
        topBar = {
            if(isSearchActive) SearchBarM3(){
                isSearchActive = false
            } else CustomAppBar(
                onSearchClick = {
                    isSearchActive = true
                }
            )

        }
    ) {innerPadding->

     if(isLoading){
         Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
             CircularProgressIndicator()
         }
     }else{
         NewsList(
             state = listState,
             paddingValues = PaddingValues(
                 top = innerPadding.calculateTopPadding() ,
                 start = innerPadding.calculateStartPadding(LayoutDirection.Ltr)  + 16.dp,
                 end = innerPadding.calculateEndPadding(LayoutDirection.Ltr) + 16.dp,
                 bottom = innerPadding.calculateBottomPadding()
             ),
             news = newsList,
             onSavedClick = { articleTitle ->
                 viewModel.toggleBookmark(articleTitle)
             }
         ) {
             //viewModel.fetchNews()
         }
     }
    }
}

@Composable
fun NewsList(
    state: LazyListState,
    paddingValues: PaddingValues,
    news:List<NewsArticle>,
    onSavedClick:(String)->Unit,
    onLoadMore:()->Unit ={}
){
    LazyColumn(
        state = state,
        contentPadding = paddingValues
    ) {
        itemsIndexed(news,key = {index,_ -> index}){index , item ->
            NewsItem(article = item){
                onSavedClick(item.title)
            }
            if (index == news.size -1){
                onLoadMore()
            }
        }

    }
}

