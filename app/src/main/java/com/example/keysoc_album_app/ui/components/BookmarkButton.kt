package com.example.keysoc_album_app.ui.components

import android.util.Log
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.keysoc_album_app.data.api.model.Album

@Composable
fun BookmarkButton(album: Album) {
    val viewModel = viewModel(modelClass = BookmarkButtonViewModel::class.java)
//    var isFavorite by remember { mutableStateOf(false) }
    viewModel.checkIsBookmarked(album = album)
    var isFavorite = viewModel.isBookmarked.value ?: false
    Log.v("isBookmark", viewModel.isBookmarked.value.toString())

    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            isFavorite = !isFavorite
            viewModel.bookmarkAlbum(isFavorite, album)
        }
    ) {
        Icon(
            tint = Color.Red,
            modifier = Modifier.graphicsLayer {
                scaleX = 1.3f
                scaleY = 1.3f
            },
            imageVector = if (isFavorite) {
                Icons.Filled.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = null
        )
    }
}