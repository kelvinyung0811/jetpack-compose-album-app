package com.example.keysoc_album_app.data.api.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.example.keysoc_album_app.data.api.model.Album
import com.example.keysoc_album_app.data.api.model.Bookmark
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MockAlbumRepository : BaseRepository {

    private val bookmarkItems = mutableListOf<Bookmark>()
    private val observableBookmarkItems = MutableLiveData<List<Bookmark>>(bookmarkItems)

    private val album = Album(
        wrapperType = "collection",
        collectionType = "Album",
        artistId = 909253,
        collectionId = 1469577723,
        amgArtistId = 468749,
        artistName = "Jack Johnson",
        collectionName = "",
        collectionCensoredName = "",
        artistViewUrl = "",
        collectionViewUrl = "",
        artworkUrl60 = "",
        artworkUrl100 = "",
        collectionPrice = 9.99,
        collectionExplicitness = "NotExplicit",
        trackCount = 15,
        copyright = "",
        country = "USA",
        currency = "USD",
        releaseDate = "2006-02-07T08:00:00Z",
        primaryGenreName = "Rock"
    )

    override fun getAllAlbums(term: String, entity: String): Flow<PagingData<Album>> {
        return flowOf(PagingData.from(listOf(album)))
    }

    override fun getAllBookmarkItem(): LiveData<List<Bookmark>> {
        return observableBookmarkItems
    }

    override suspend fun addBookmark(bookmark: Bookmark) {
        bookmarkItems.add(bookmark)
    }

    override suspend fun deleteBookmark(bookmark: Bookmark) {
        bookmarkItems.remove(bookmark)
    }

}