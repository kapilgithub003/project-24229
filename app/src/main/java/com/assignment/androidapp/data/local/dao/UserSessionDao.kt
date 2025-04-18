package com.assignment.androidapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assignment.androidapp.domain.model.UserSession

@Dao
interface UserSessionDao {
    @Query("SELECT * FROM user_session LIMIT 1")
    suspend fun getSession(): UserSession?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: UserSession)

    @Query("DELETE FROM user_session")
    suspend fun clearSession()
}