package com.example.newscompose.screen.onBoarding.componants

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pageSize: Int,
    selectedPage: Int,
    selectedColor: Color = Color.Black.copy(alpha = 0.7F),
    unselectedColor: Color = Color.Black.copy(alpha = 0.3F),
){
    Row(
        modifier = modifier.padding(start = 20.dp, end = 20.dp)
        ,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(pageSize){page ->
            Box(
                modifier = Modifier
                    .padding(end = 5.dp)
                    .height(6.dp)
                    .weight(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = if (page == selectedPage) selectedColor else unselectedColor)
            )

        }
    }

}