package com.assignment.androidapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_session")
data class UserSession(
    @PrimaryKey val id: Int = 1,
    val userId: Int
)