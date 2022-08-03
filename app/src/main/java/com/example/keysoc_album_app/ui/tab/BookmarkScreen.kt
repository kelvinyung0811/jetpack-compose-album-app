package com.example.keysoc_album_app.ui.tab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.keysoc_album_app.data.api.model.Bookmark
import com.example.keysoc_album_app.ui.album.AlbumCard

@Composable
fun BookmarkScreen() {
    val viewModel = viewModel(modelClass = BookmarkScreenViewModel::class.java)
    val state by viewModel.bookmark.collectAsState()

    Surface(
        contentColor = Color.Black
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.isEmpty()) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center)
                    )
                }
            }

            items(state) { bookmark: Bookmark ->
                AlbumCard(album = bookmark.album)
            }
        }
    }
}