package com.assignment.androidapp.data.remote.dto

import com.assignment.androidapp.domain.model.Manga

data class MangaApiResponse(
    val code: Int,
    val data: List<Manga>
)