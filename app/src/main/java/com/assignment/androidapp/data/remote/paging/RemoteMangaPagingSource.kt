package com.assignment.androidapp.data.remote.paging

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.assignment.androidapp.data.local.dao.MangaDao
import com.assignment.androidapp.domain.model.Manga
import com.assignment.androidapp.core.util.NetworkUtils
import com.assignment.androidapp.data.remote.api.MangaApiService

class RemoteMangaPagingSource(
    private val api: MangaApiService,
    private val dao: MangaDao,
    private val context: Context
) : PagingSource<Int, Manga>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Manga> {
        val page = params.key ?: 1

        return try {
            if (NetworkUtils.isNetworkAvailable(context)) {
                val response = api.fetchMangaList(page)
                val mangaList = response.data
                if (mangaList.isNotEmpty()) {
                    dao.insertAll(mangaList)
                }

                LoadResult.Page(
                    data = mangaList,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (mangaList.isEmpty()) null else page + 1
                )
            } else {
                val cachedManga = getCachedMangaList(page, params.loadSize)
                LoadResult.Page(
                    data = cachedManga,
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Manga>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }

    private suspend fun getCachedMangaList(page: Int, loadSize: Int): List<Manga> {
        val limit = loadSize
        val offset = (page - 1) * limit
        return dao.getCachedManga(limit, offset)
    }
}








