package com.example.keysoc_album_app.data.api.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.keysoc_album_app.data.api.local.dao.AlbumDao
import com.example.keysoc_album_app.data.api.local.dao.RemoteKeysDao
import com.example.keysoc_album_app.data.api.model.Album
import com.example.keysoc_album_app.data.api.model.RemoteKeys

@Database(entities = [Album::class, RemoteKeys::class], version = 1)
abstract class AlbumDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}