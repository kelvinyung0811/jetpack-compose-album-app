package com.example.keysoc_album_app.ui.tab

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.example.keysoc_album_app.ui.album.AlbumCardView

@Composable
fun AlbumScreen() {
    val albumScreenViewModel = viewModel(modelClass = AlbumScreenViewModel::class.java)
    val albums = albumScreenViewModel.albums.collectAsLazyPagingItems()
    Surface(
        contentColor = Color.Black
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "Artist: Jack Johnson",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            itemsIndexed(albums) { index, album ->
                Log.v("Index", index.toString())
                if (album != null) {
                    AlbumCardView(album = album)
                }
            }
        }
    }
}