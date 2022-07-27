package com.example.keysoc_album_app.ui.tab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keysoc_album_app.data.api.model.Album
import com.example.keysoc_album_app.data.api.repository.BaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumScreenViewModel @Inject constructor(
    albumRepository: BaseRepository
) : ViewModel() {

    private val _albums = MutableStateFlow(emptyList<Album>())
    val albums: StateFlow<List<Album>>
        get() = _albums

    init {
        viewModelScope.launch {
            val albums = albumRepository.getAllAlbums()
            _albums.value = albums
        }

    }
}