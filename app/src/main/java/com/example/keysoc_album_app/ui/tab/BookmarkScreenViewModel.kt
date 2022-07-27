package com.example.keysoc_album_app.ui.tab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class BookmarkScreenViewModel @Inject constructor(
    albumRepository: BaseRepository
) : ViewModel() {

    private val _bookmark = MutableStateFlow(emptyList<Bookmark>())
    val bookmarks: StateFlow<List<Bookmark>>
        get() = _bookmark

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val bookmarks = albumRepository.getBookmark()
            _bookmark.value = bookmarks
        }
    }
}