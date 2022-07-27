package com.example.keysoc_album_app.data.api.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.keysoc_album_app.data.api.AlbumApi
import com.example.keysoc_album_app.data.api.local.AlbumDatabase
import com.example.keysoc_album_app.data.api.model.Album
import com.example.keysoc_album_app.data.api.model.Bookmark
import com.example.keysoc_album_app.data.api.paging.AlbumMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AlbumRepository @Inject constructor(
    private val albumApi: AlbumApi,
    private val albumDatabase: AlbumDatabase
) : BaseRepository {

    private val bookmarkDao = albumDatabase.bookmarkDao()

    @OptIn(ExperimentalPagingApi::class)
    override fun getAllAlbums(term: String, entity: String): Flow<PagingData<Album>> {
        val pagingSourceFactory = { albumDatabase.albumDao().getAllGags() }
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = AlbumMediator(
                albumApi = albumApi,
                albumDatabase = albumDatabase,
                term = term,
                entity = entity
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override suspend fun bookmark(bookmark: Bookmark) {
        bookmarkDao.addBookmark(bookmark)
    }

    override suspend fun checkIfBookmarked(bookmark: Bookmark): Boolean {
        return bookmarkDao.loadBookmark(bookmark.album.collectionId.toString())
    }

    override suspend fun getBookmark(): List<Bookmark> {
        return bookmarkDao.getAllBookmark()
    }
}