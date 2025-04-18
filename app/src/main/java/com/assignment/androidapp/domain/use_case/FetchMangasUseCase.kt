package com.assignment.androidapp.domain.use_case

import com.assignment.androidapp.data.remote.api.MangaApiService
import javax.inject.Inject

class FetchMangasUseCase @Inject constructor(
    private val mangaApiService: MangaApiService
) {
//    fun execute(genres: String, nsfw: Boolean, type: String): Flow<PagingData<Manga>> {
//        return Pager(
//            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
//            pagingSourceFactory = { MangaPagingSource(mangaApiService, genres, nsfw, type) }
//        ).flow
//    }
}