package com.example.keysoc_album_app.ui.components

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keysoc_album_app.data.api.model.Album
import com.example.keysoc_album_app.data.api.model.Bookmark
import com.example.keysoc_album_app.data.api.repository.BaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkButtonViewModel @Inject constructor(
    albumRepository: BaseRepository
) : ViewModel() {

    private val repository = albumRepository

    private val _isBookmarked = MutableLiveData<Boolean>()
    val isBookmarked: LiveData<Boolean>
        get() = _isBookmarked

    fun bookmarkAlbum(isFavorite: Boolean, album: Album) {
        val bookmark = Bookmark(album)
        viewModelScope.launch {
            if (isFavorite) {
                repository.bookmark(bookmark)
            } else {
//                repository.removeBookmark(bookmark)
            }
        }
    }

    fun checkIsBookmarked(album: Album) {
        val bookmark = Bookmark(album)
        CoroutineScope(Dispatchers.IO).launch {
            val result = repository.checkIfBookmarked(bookmark)
            _isBookmarked.postValue(result)
        }
    }

}