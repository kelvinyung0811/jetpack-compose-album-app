package com.example.keysoc_album_app.ui.album

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.keysoc_album_app.data.api.model.Album

@Composable
fun AlbumCard(album: Album) {
    val viewModel = viewModel(modelClass = AlbumViewModel::class.java)
    val bookmark by viewModel.bookmark.observeAsState(listOf())

    Surface(
        contentColor = Color.Black
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box {
                Image(
                    painter = rememberImagePainter(data = album.artworkUrl100),
                    contentDescription = null,
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp),
                    contentScale = ContentScale.FillBounds
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Text(
                    text = "Artist: ${album.artistName}",
                    fontSize = 15.sp
                )

                Text(
                    text = "Collection: ${album.artistName}",
                    fontSize = 15.sp
                )

                Text(
                    text = "Price: ${album.collectionPrice}" + " " + album.currency,
                    fontSize = 15.sp
                )

                Text(
                    text = "Country: ${album.country}",
                    fontSize = 15.sp
                )

                Text(
                    text = "Release Date: ${album.releaseDate}",
                    fontSize = 15.sp
                )

                Text(
                    text = "Genre: ${album.primaryGenreName}",
                    fontSize = 15.sp
                )

                Text(
                    text = album.copyright,
                    fontSize = 12.sp,
                    textAlign = TextAlign.End
                )

                Box(
                    modifier = Modifier
                        .align(End)
                ) {
                    BookmarkButton(
                        album,
                        viewModel,
                        bookmark.any { it.album.collectionId == album.collectionId })
                }
            }
        }
    }
}

@Composable
fun BookmarkButton(album: Album, viewModel: AlbumViewModel, isBookmarked: Boolean) {
    var isFavorite = isBookmarked

    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            isFavorite = !isFavorite
            viewModel.handleFavButtonOnClick(album, isFavorite)
        },
        modifier = Modifier.testTag("FavButton")
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