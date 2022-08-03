package com.example.keysoc_album_app.data.api.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.keysoc_album_app.data.api.model.Bookmark

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM BOOKMARK_TABLE")
    fun getAllBookmarkItem(): LiveData<List<Bookmark>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmark(bookmark: Bookmark)

    @Delete
    suspend fun deleteBookmark(bookmark: Bookmark)
}