package com.example.keysoc_album_app.ui.tab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.keysoc_album_app.data.api.model.Album
import com.example.keysoc_album_app.ui.album.AlbumCardView

@Composable
fun AlbumScreen() {
    val albumScreenViewModel = viewModel(modelClass = AlbumScreenViewModel::class.java)
    val state by albumScreenViewModel.albums.collectAsState()
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

            item {
                Text(
                    text = "Artist: Jack Johnson",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            items(state) { album: Album ->
                AlbumCardView(album)
            }
        }
    }
}