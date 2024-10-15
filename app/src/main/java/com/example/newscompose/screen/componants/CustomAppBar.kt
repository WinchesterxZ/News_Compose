package com.example.newscompose.screen.componants

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(onSearchClick: () -> Unit){
    CenterAlignedTopAppBar(
        title = {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "News" , fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground, fontSize = 22.sp)
                Text(text = "Cloud",fontWeight = FontWeight.Bold, color = Color.Yellow, style = TextStyle(
                    shadow = Shadow(
                        color = MaterialTheme.colorScheme.onBackground,
                        blurRadius = 0.5f
                    )
                ),
                    fontSize = 22.sp)

            }
        },
        actions = {
            CustomIcon(
                icon = Icons.Default.Search,
                contentDescription = "Icon Search"
            ) {
                onSearchClick()
            }
        }
    )
}
