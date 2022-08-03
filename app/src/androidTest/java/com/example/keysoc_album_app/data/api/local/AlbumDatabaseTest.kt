package com.example.keysoc_album_app.data.api.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.keysoc_album_app.data.api.local.dao.AlbumDao
import com.example.keysoc_album_app.data.api.local.dao.BookmarkDao
import com.example.keysoc_album_app.data.api.local.dao.RemoteKeysDao
import com.example.keysoc_album_app.data.api.model.Album
import com.example.keysoc_album_app.data.api.model.Bookmark
import com.example.keysoc_album_app.data.api.model.RemoteKeys
import com.example.keysoc_album_app.data.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlbumDatabaseTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: AlbumDatabase
    private lateinit var albumDao: AlbumDao
    private lateinit var bookmarkDao: BookmarkDao
    private lateinit var remoteKeysDao: RemoteKeysDao

    private val key = RemoteKeys(
        id = "id",
        prevPage = 1,
        nextPage = 3
    )
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

    private val bookmark = listOf(Bookmark(album = album))

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AlbumDatabase::class.java).build()
        albumDao = db.albumDao()
        bookmarkDao = db.bookmarkDao()
        remoteKeysDao = db.remoteKeysDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    private fun addAlbum() = runBlocking {
        albumDao.addAlbum(listOf(album))
    }

    @Test
    fun writeAndReadAlbum() = runBlocking {
        addAlbum()
        val albumResult = albumDao.getAllAlbums()
        // TODO: check if albumResult = gag
        assertThat(albumResult).isNotNull()
    }

    @Test
    fun deleteAlbum() = runBlocking {
        addAlbum()
        albumDao.deleteAllAlbum()
        val albumResult = albumDao.getAllAlbums()
        // TODO: check if gagResult is empty
    }

    private fun addBookmark() = runBlocking {
        bookmarkDao.addBookmark(Bookmark(album))
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun writeAndReadBookmark() = runBlocking {
        addBookmark()
        val bookmarkResult = bookmarkDao.getAllBookmarkItem().getOrAwaitValue()
        assertThat(bookmark).isEqualTo(bookmarkResult)
    }

    @Test
    fun deleteBookmark() = runBlocking {
        addBookmark()
        bookmarkDao.deleteBookmark(Bookmark(album))
        val bookmarkResult = bookmarkDao.getAllBookmarkItem().getOrAwaitValue()
        assertThat(bookmarkResult).doesNotContain(Bookmark(album))
    }

    private fun addRemoteKey() = runBlocking {
        remoteKeysDao.addAllRemoteKeys(listOf(key))
    }

    @Test
    fun writeAndReadRemoteKeys() = runBlocking {
        addRemoteKey()
        val keyResult = remoteKeysDao.getRemoteKeys("id")
        assertThat(key).isEqualTo(keyResult)
    }

    @Test
    fun deleteRemoteKeys() = runBlocking {
        addRemoteKey()
        remoteKeysDao.deleteAllRemoteKeys()
        val keyResult = remoteKeysDao.getRemoteKeys("id")
        assertThat(keyResult).isNull()
    }
}