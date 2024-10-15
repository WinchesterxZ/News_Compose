package com.example.newscompose

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newscompose.common.navgraph.Screens
import com.example.newscompose.domain.usecases.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel@Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
):ViewModel() {

    // Represent loading state
    var isLoading by mutableStateOf(true)
        private set

    var afterSplashScreenEnds by mutableStateOf("")
        private set // Prevent external modification
    init {
        viewModelScope.launch {
            appEntryUseCases.readAppEntry().collect{startingFromHomeScreen ->
                Log.d("abdany", ": $startingFromHomeScreen")
                afterSplashScreenEnds = if(startingFromHomeScreen){
                    Log.d("abdany", ": a7aaaaaaaaaaaa")
                    Screens.NewsApp.route
                }else{
                    Screens.OnBoardingScreen.route
                }
                isLoading = false
            }
        }
    }
    fun saveAppEntry(){
        viewModelScope.launch {
            appEntryUseCases.saveAppEntry()
        }
    }


}