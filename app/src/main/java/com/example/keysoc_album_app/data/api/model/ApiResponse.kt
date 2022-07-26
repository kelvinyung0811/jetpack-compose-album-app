package com.example.keysoc_album_app.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponse(
    @Json(name = "resultCount")
    val resultCount: Int,
    @Json(name = "results")
    val albums: List<Album>
)