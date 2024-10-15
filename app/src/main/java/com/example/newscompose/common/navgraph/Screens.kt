package com.example.newscompose.common.navgraph

import kotlinx.serialization.Serializable

sealed interface Screens {
    val route: String

    @Serializable
    data object SplashScreen : Screens {
        override val route = "splash_screen"
    }

    @Serializable
    data object OnBoardingScreen : Screens {
        override val route = "on_boarding_screen"
    }

    @Serializable
    data object NewsApp : Screens {
        override val route = "news_app"
    }

    // Add other screens similarly
    @Serializable
    data object SearchScreen : Screens {
        override val route = "search_screen"
    }

    @Serializable
    data object BookmarkScreen : Screens {
        override val route = "bookmark_screen"
    }

    @Serializable
    data object DetailsScreen : Screens {
        override val route = "details_screen"
    }
}
