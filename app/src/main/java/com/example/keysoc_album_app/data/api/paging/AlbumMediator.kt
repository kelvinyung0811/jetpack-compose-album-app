package com.example.keysoc_album_app.data.api.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.keysoc_album_app.data.api.AlbumApi
import com.example.keysoc_album_app.data.api.local.AlbumDatabase
import com.example.keysoc_album_app.data.api.model.Album
import com.example.keysoc_album_app.data.api.model.RemoteKeys

@OptIn(ExperimentalPagingApi::class)
class AlbumMediator(
    private val albumApi: AlbumApi,
    private val albumDatabase: AlbumDatabase,
    private val term: String,
    private val entity: String
) : RemoteMediator<Int, Album>() {

    private val albumDao = albumDatabase.albumDao()
    private val remoteKeysDao = albumDatabase.remoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Album>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = albumApi.getApiResponse(term = term, entity = entity)
            val endOfPaginationReached = currentPage * 10 > response.resultCount

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            albumDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    albumDao.deleteAllAlbum()
                    remoteKeysDao.deleteAllRemoteKeys()
                }
                val keys = response.albums.map { album ->
                    RemoteKeys(
                        id = album.collectionId.toString(),
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                remoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                albumDao.addAlbum(gag = response.albums)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Album>
    ): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.collectionId?.let { id ->
                remoteKeysDao.getRemoteKeys(id = id.toString())
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Album>
    ): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { album ->
                remoteKeysDao.getRemoteKeys(id = album.collectionId.toString())
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Album>
    ): RemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { album ->
                remoteKeysDao.getRemoteKeys(id = album.collectionId.toString())
            }
    }

}