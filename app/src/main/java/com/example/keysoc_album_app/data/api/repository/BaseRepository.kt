package com.example.keysoc_album_app.data.api.repository

import com.example.keysoc_album_app.data.api.model.Album
import kotlinx.coroutines.flow.Flow

interface BaseRepository {
    fun getAllAlbums(): List<Album>
}