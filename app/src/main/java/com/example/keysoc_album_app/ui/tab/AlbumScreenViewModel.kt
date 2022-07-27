package com.example.keysoc_album_app.ui.tab

import androidx.lifecycle.ViewModel
import com.example.keysoc_album_app.data.api.repository.BaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlbumScreenViewModel @Inject constructor(
    albumRepository: BaseRepository
) : ViewModel() {
    val albums = albumRepository.getAllAlbums()
}