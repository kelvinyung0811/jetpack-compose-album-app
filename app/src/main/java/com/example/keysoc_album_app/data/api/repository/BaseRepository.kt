package com.example.keysoc_album_app.data.api.repository

import com.example.keysoc_album_app.data.api.model.Album

interface BaseRepository {
    suspend fun getAllAlbums(): List<Album>
}