package com.example.keysoc_album_app.data.api.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.keysoc_album_app.util.Constants

@Entity(tableName = Constants.REMOTE_KEY_TABLE)
data class RemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
) {
}