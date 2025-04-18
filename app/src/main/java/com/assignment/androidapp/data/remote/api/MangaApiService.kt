package com.assignment.androidapp.data.remote.api

import com.assignment.androidapp.data.remote.dto.MangaApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MangaApiService {

    @GET("manga/fetch")
    suspend fun fetchMangaList(
        @Query("page") page: Int,
        @Query("genres") genres: String = "",
        @Query("nsfw") nsfw: Boolean = true,
        @Query("type") type: String = "all"
    ): MangaApiResponse
}