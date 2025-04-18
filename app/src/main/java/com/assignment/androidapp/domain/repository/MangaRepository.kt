package com.assignment.androidapp.domain.repository

import android.content.Context
import androidx.paging.Pager
import com.assignment.androidapp.domain.model.Manga

interface MangaRepository {
    fun getMangaPager(context: Context): Pager<Int, Manga>
    suspend fun getMangaById(mangaId: String?): Manga?
}