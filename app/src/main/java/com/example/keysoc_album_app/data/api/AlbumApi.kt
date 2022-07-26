package com.example.keysoc_album_app.data.api

import com.example.keysoc_album_app.data.api.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumApi {

    @GET("/search?term={term}&entity={entity}")
    suspend fun getApiResponse(
        @Path("term") term: String, @Path("entity") entity: String
    ): ApiResponse
}