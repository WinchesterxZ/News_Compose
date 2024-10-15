package com.example.newscompose

import SplashScreen
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.getDrawable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newscompose.common.navgraph.Navigation
import com.example.newscompose.ui.theme.NewsComposeTheme

import com.google.accompanist.drawablepainter.rememberDrawablePainter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    /*@Inject
    lateinit var useCases: AppEntryUseCases*/
    override fun onCreate(savedInstanceState: Bundle?) {
        //installSplashScreen()
        super.onCreate(savedInstanceState)

       /* val newsRepository = NewsRepository(RetrofitInstance.api)
        val viewModelFactory = NewsViewModelFactory(newsRepository)

        val vm = ViewModelProvider(this,viewModelFactory)[NewsViewModel::class.java]*/
        enableEdgeToEdge()
        setContent {
            NewsComposeTheme {
                //NewsApp(viewModel = vm)
                val mainViewModel : MainViewModel = hiltViewModel()
                // val onBoardingViewModel : OnBoardingViewModel = hiltViewModel()
                // Show the splash screen while loading
                if (mainViewModel.isLoading) {
                    SplashScreen(
                        isLoaded = mainViewModel.isLoading,
                    )
                } else {
                    // Once loading is complete, navigate to the appropriate screen
                    Navigation(
                        afterSplashScreenDestination = mainViewModel.afterSplashScreenEnds
                    ) {
                        mainViewModel.saveAppEntry()
                    }
                }
            }
        }
    }

}



