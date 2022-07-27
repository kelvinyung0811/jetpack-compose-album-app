package com.example.keysoc_album_app.data.api.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.keysoc_album_app.data.api.model.RemoteKeys

@Dao
interface RemoteKeysDao {
    @Query("SELECT * FROM REMOTE_KEY_TABLE WHERE id =:id")
    suspend fun getRemoteKeys(id: String): RemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<RemoteKeys>)

    @Query("DELETE FROM REMOTE_KEY_TABLE")
    suspend fun deleteAllRemoteKeys()
}