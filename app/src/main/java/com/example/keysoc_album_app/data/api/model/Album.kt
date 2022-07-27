package com.example.keysoc_album_app.data.api.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.keysoc_album_app.util.Constants
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = Constants.ALBUM_TABLE)
data class Album(
    val amgArtistId: Int? = 0,
    val artistId: Int,
    val artistName: String,
    val artistViewUrl: String,
    val artworkUrl100: String,
    val artworkUrl60: String,
    val collectionCensoredName: String,
    val collectionExplicitness: String,
    @PrimaryKey(autoGenerate = false)
    val collectionId: Int,
    val collectionName: String,
    val collectionPrice: Double,
    val collectionType: String,
    val collectionViewUrl: String,
    val contentAdvisoryRating: String? = "",
    val copyright: String,
    val country: String,
    val currency: String,
    val primaryGenreName: String,
    val releaseDate: String,
    val trackCount: Int,
    val wrapperType: String
)