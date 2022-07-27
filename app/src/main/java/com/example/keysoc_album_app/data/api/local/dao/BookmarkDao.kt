package com.example.keysoc_album_app.data.api.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.keysoc_album_app.data.api.model.Bookmark

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM BOOKMARK_TABLE")
    fun getAllBookmark(): List<Bookmark>

    @Query("SELECT EXISTS (SELECT * FROM BOOKMARK_TABLE WHERE collectionId = :bookmarkId)")
    fun loadBookmark(bookmarkId: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmark(bookmark: Bookmark)

    @Query("DELETE FROM BOOKMARK_TABLE")
    suspend fun deleteBookmark()
}