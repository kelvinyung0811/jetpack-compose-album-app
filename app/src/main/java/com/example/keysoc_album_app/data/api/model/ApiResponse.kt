package com.example.keysoc_album_app.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val resultCount: Int,
    @SerialName("results")
    val albums: List<Album>
)