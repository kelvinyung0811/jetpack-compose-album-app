package com.example.keysoc_album_app.data.api

import com.example.keysoc_album_app.data.api.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumApi {

    @GET("/search")
    suspend fun getApiResponse(
        @Query("term") term: String,
        @Query("entity") entity: String
    ): ApiResponse
}