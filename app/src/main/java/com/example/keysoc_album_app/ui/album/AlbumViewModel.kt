package com.example.keysoc_album_app.ui.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keysoc_album_app.data.api.model.Album
import com.example.keysoc_album_app.data.api.model.Bookmark
import com.example.keysoc_album_app.data.api.repository.BaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    albumRepository: BaseRepository
) : ViewModel() {

    private val repository = albumRepository

    val bookmark = repository.getAllBookmarkItem()


    fun handleFavButtonOnClick(album: Album, isFav: Boolean) {
        viewModelScope.launch {
            if (isFav) {
                repository.addBookmark(Bookmark(album))
            } else {
                repository.deleteBookmark(Bookmark(album))
            }
        }
    }
}