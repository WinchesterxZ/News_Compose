package com.example.newscompose.screen.componants

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.newscompose.R
import com.example.newscompose.data.NewsArticle

@Composable
fun NewsItem(article: NewsArticle , onSaveClicked: (String) -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }
    var isVisible by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .border(
                BorderStroke(0.5.dp, Color.Black),
                shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .height(350.dp)
    ) {
        Column {
            Column(
                modifier = Modifier.fillMaxSize()
                    .weight(0.70f)

            ) {
                // Image takes 75% of the available height
                ImageWithCustomRoundedCorners(
                    imageUrl = article.imageUrl,
                    modifier = Modifier
                        .fillMaxWidth(),
                    isVisible = isVisible,
                    onLoading = {
                        isVisible = true

                    },
                    onSuccess = {
                        isVisible = false
                    },
                    onError = {
                        isVisible = false
                    }
                )
            }


            // Two texts take 25% of the available height
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.30f)
                    .background(color = Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                CustomText(
                    text = article.title,
                    modifier = Modifier.padding(5.dp),
                    maxLines = 1,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                CustomText(
                    text = article.description,
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 5.dp),
                    maxLines = 2,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.W500
                )
            }
        }

        SettingsMenu(
            isExpanded = isExpanded,
            imageVector = if(article.isSaved) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
            iconModifier = Modifier.align(Alignment.TopEnd),
            onExpandClicked = { isExpanded = true },
            onDismissClicked = { isExpanded = false },
            onSaveClicked = {
                isExpanded = false
                onSaveClicked(article.title)
            })

        }




    }




@Composable
fun SettingsMenu(
    isExpanded: Boolean,
    imageVector: ImageVector,
    iconModifier: Modifier,
    onExpandClicked:()->Unit,
    onDismissClicked:()->Unit,
    onSaveClicked:()->Unit) {
    IconButton(modifier = iconModifier,onClick = onExpandClicked) {
        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Settings")
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = onDismissClicked,
            modifier = Modifier.background(color = Color.White)
        ) {
            DropdownMenuItem(
                text = {
                    Row (
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(imageVector = imageVector, contentDescription = "Save", tint = Color.Black)
                        Text(modifier = Modifier.padding(start = 5.dp),text = "Save" , fontSize = 15.sp, color = Color.Black)
                    }
                },
                onClick =onSaveClicked
            )
        }
    }

}


@Composable
private fun ImageWithCustomRoundedCorners(imageUrl:String?,modifier: Modifier, onLoading:() -> Unit,onSuccess:() -> Unit, onError:() -> Unit,isVisible: Boolean) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)

            .build(),
        contentDescription = "Image with Custom Rounded Corners",
        modifier = modifier,
        contentScale = ContentScale.FillBounds,
        placeholder = painterResource(R.drawable.ic_launcher_foreground),
        error = painterResource(R.drawable.ic_launcher_foreground),
        onLoading  = {
            onLoading()

        },

        onSuccess = {
            onSuccess()
        },
        onError = {
            onError()
        }
    )
    if (isVisible) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

}

@Composable
private fun CustomText(
    text: String,
    modifier: Modifier,
    maxLines: Int,
    style: TextStyle,
    fontWeight: FontWeight
) {
    Text(
        text = text,
        modifier = modifier,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        style = style,
        fontWeight = fontWeight,
        color = Color.Black
    )
}