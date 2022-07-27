package com.example.keysoc_album_app.data.api.repository

import androidx.paging.PagingData
import com.example.keysoc_album_app.data.api.model.Album
import com.example.keysoc_album_app.data.api.model.Bookmark
import kotlinx.coroutines.flow.Flow

interface BaseRepository {
    fun getAllAlbums(term: String, entity: String): Flow<PagingData<Album>>
    suspend fun bookmark(bookmark: Bookmark)
    suspend fun checkIfBookmarked(bookmark: Bookmark) : Boolean
    suspend fun getBookmark() : List<Bookmark>
}