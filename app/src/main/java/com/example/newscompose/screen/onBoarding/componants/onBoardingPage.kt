package com.example.newscompose.screen.onBoarding.componants

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newscompose.screen.onBoarding.Page

@Composable
fun OnBoardingPage(
    page: Page,
    modifier: Modifier = Modifier,
    selectedPage: Int,
    pageSize: Int,
    isVisible: Boolean,
    onSkipClicked: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = page.backgroundColor)
            .padding(top = 16.dp), // Fills the entire screen
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp), // Leave space for the button
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Page indicator using weight 0.1 (10%)
            PageIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.1f), // 10% height
                pageSize = pageSize,
                selectedPage = selectedPage, // Update based on pager's current page
            )
                    // Title
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.2f),
                        text = page.title,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 35.sp
                        ),
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    // Image with proportional height
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f),
                        painter = painterResource(id = page.image),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )

                    // Description text
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.2f)
                            .padding(top = 25.dp),
                        text = page.description,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

        // Button aligned to the bottom of the screen
       if (isVisible){
           NewsCustomButton(
               modifier = Modifier
                   .align(Alignment.BottomCenter) // Aligns the button at the bottom
                   .padding(bottom = 25.dp) // Padding for spacing
                   .height(48.dp), // Fixed height for the button
               text = "Get Started",
               onClick = onSkipClicked
           )
       }
    }
}







