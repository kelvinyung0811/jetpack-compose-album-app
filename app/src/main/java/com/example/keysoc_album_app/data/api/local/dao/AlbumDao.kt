package com.example.keysoc_album_app.data.api.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.keysoc_album_app.data.api.model.Album

@Dao
interface AlbumDao {
    @Query("SELECT * FROM ALBUM_TABLE")
    fun getAllAlbums(): PagingSource<Int, Album>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAlbum(gag: List<Album>)

    @Query("DELETE FROM ALBUM_TABLE")
    suspend fun deleteAllAlbum()
}