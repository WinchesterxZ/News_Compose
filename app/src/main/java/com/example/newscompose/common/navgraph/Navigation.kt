package com.example.newscompose.common.navgraph

import SplashScreen
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newscompose.screen.NewsApp
import com.example.newscompose.screen.onBoarding.OnBoardingScreen
import com.example.newscompose.viewmodel.NewsViewModel

@Composable
fun Navigation(
    afterSplashScreenDestination: String,
    onSkipClick: () -> Unit,
){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    ) {
        composable(Screens.SplashScreen.route) {
            SplashScreen(
                isLoaded = true,
                onFinished = {
                    navController.navigate(afterSplashScreenDestination)
                }
            )

        }
        composable(Screens.OnBoardingScreen.route) {
            OnBoardingScreen(
                event =  {
                    onSkipClick()
                    navController.navigate(Screens.NewsApp.route)
                }

            )

        }
        // NewsApp route
        composable(Screens.NewsApp.route) {
            val newsViewModel: NewsViewModel = hiltViewModel() // Obtain NewsViewModel via Hilt
            NewsApp(viewModel = newsViewModel) // Pass the viewModel to NewsApp
        }
    }
}