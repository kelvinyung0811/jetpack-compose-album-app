package com.example.keysoc_album_app.ui.tab

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AlbumScreen() {
    val albumScreenViewModel = viewModel(modelClass = AlbumScreenViewModel::class.java)
    val getAllAlbums = albumScreenViewModel.getAllAlbums
    Text(text = "Hello Album", color = Color.Black)
}