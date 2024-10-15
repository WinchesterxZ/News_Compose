package com.example.newscompose.screen.componants

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarM3(
    onBackPressed : () -> Unit
){
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(true) }
    SearchBar(
        colors = SearchBarDefaults.colors(
            inputFieldColors = SearchBarDefaults.inputFieldColors(
                cursorColor = Color.Black
            ),
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            dividerColor = Color.Transparent
        ),
        query = query,
        onQueryChange = {
            query = it
        },
        onSearch = { newQuery->
            Log.d("SearchBarM3: ", "search $newQuery")
        },
        active = active,
        onActiveChange = {
            active = it
        },
        placeholder = {
            Text("Search For News")
        },
        leadingIcon = {
            CustomIcon(
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Icon Back"
            ) {
                active = false
                query = ""
                onBackPressed()
            }
        },
        trailingIcon = {
            if(active) {
                CustomIcon(
                    icon = Icons.Default.Close,
                    contentDescription = "Icon Close"
                ){
                    if (query.isNotEmpty()) {
                        query = ""
                    } else {
                        active = false
                    }
                }
            }else null
        }
    ) {

    }
}

@Composable
 fun CustomIcon(
    modifier: Modifier?= null,
    icon : ImageVector,
    contentDescription : String,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier ?: Modifier,
        onClick = onClick
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription
        )
    }
}
