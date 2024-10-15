package com.example.newscompose.screen.onBoarding

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.newscompose.screen.onBoarding.componants.OnBoardingPage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(
    event: (OnBoardingEvents) -> Unit
) {
    val pagerState = rememberPagerState() // State to remember the pager’s current page
    val coroutineScope = rememberCoroutineScope()
    val isVisible = pagerState.currentPage == pages.size - 1
    HorizontalPager(
        state = pagerState,
        count = pages.size,
        modifier = Modifier.fillMaxSize() // Fill the remaining space for the content
    ) { pageIndex -> // Lambda for each page
        Log.d("OnBoardingPage", "OnBoardingPage: $pageIndex ")
        OnBoardingPage(
            page = pages[pageIndex],
            modifier = Modifier.fillMaxSize(),
            selectedPage = pagerState.currentPage,
            pageSize = pages.size,
            isVisible = isVisible
        ) {
            coroutineScope.launch {
                event(OnBoardingEvents.SaveAppEntry)
            }

        }
    }
}


