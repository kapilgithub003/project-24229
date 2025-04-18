package com.assignment.androidapp.data.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.assignment.androidapp.data.local.dao.MangaDao
import com.assignment.androidapp.data.remote.api.MangaApiService
import com.assignment.androidapp.data.remote.paging.RemoteMangaPagingSource
import com.assignment.androidapp.domain.model.Manga
import com.assignment.androidapp.domain.repository.MangaRepository
import javax.inject.Inject

class MangaRepositoryImpl @Inject constructor(
    private val mangaApiService: MangaApiService,
    private val mangaDao: MangaDao
) : MangaRepository {

    override fun getMangaPager(context: Context): Pager<Int, Manga> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { RemoteMangaPagingSource(mangaApiService, mangaDao, context) }
        )
    }

    override suspend fun getMangaById(mangaId: String?): Manga? {
        return mangaDao.getMangaById(mangaId)
    }
}


