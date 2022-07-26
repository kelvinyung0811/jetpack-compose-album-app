package com.example.keysoc_album_app.data.api.repository

import com.example.keysoc_album_app.data.api.AlbumApi
import com.example.keysoc_album_app.data.api.model.Album
import javax.inject.Inject

class AlbumRepository @Inject constructor(
    private val albumApi: AlbumApi
) : BaseRepository {

    override fun getAllAlbums(): List<Album> {
//        albumApi.getApiResponse("jack+johnson", "album")
//            .albums
        return listOf()
    }
}