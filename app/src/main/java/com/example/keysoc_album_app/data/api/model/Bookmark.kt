package com.example.keysoc_album_app.data.api.model

import androidx.room.Embedded
import androidx.room.Entity
import com.example.keysoc_album_app.util.Constants

@Entity(tableName = Constants.BOOKMARK_TABLE, primaryKeys = ["collectionId"])
data class Bookmark(
    @Embedded
    val album: Album
)