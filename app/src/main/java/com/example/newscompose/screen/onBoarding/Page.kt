package com.example.newscompose.screen.onBoarding

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.newscompose.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image:Int,
    @ColorRes val backgroundColor: Color
)

val pages = listOf(
    Page(
        title = "Get Updated\nDaily News",
        description = "Simple Ui Allows You To \n" +
                "Access Updated Daily News \n " +
                "With Easy And Simple Way",
        image = R.drawable.daily,
        backgroundColor = Color(0xFF3e7dbc)
    ),
    Page(
        title = "Save Your\n" +
                "Favorite News",
        description = "Simple Ui Allows You To \n" +
                "Save Updated Daily News \n " +
                "With Easy And Simple Way",
        image = R.drawable.save,
        backgroundColor = Color(0xFF3e7dbc)

    ),
    Page(
        title = "Search For\n" +
                "Favorite News",
        description = "Simple Ui Allows You To \n" +
                "Search Updated Daily News \n " +
                "With Easy And Simple Way",
        image = R.drawable.search,
        backgroundColor = Color(0xFF3e7dbc)

    ),

)
