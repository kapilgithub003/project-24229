package com.assignment.androidapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "manga_table")
data class Manga(
    @PrimaryKey val id: String,
    val title: String,
    val thumb: String,
    val summary: String,
    val status: String? = null,
    val sub_title: String? = null,
    val nsfw: Boolean = false,
    val type: String? = null,
    val total_chapter: Int = 0,
    val create_at: Long? = null,
    val update_at: Long? = null
)
