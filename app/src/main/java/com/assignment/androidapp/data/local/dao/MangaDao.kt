package com.assignment.androidapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assignment.androidapp.domain.model.Manga

@Dao
interface MangaDao {

    @Query("SELECT * FROM manga_table LIMIT :limit OFFSET :offset")
    suspend fun getCachedManga(limit: Int, offset: Int): List<Manga>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(manga: List<Manga>)

    @Query("SELECT * FROM manga_table WHERE id = :id LIMIT 1")
    suspend fun getMangaById(id: String?): Manga?

    @Query("DELETE FROM manga_table")
    suspend fun clearAll()
}
