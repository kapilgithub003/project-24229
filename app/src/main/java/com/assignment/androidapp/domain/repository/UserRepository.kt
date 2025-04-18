package com.assignment.androidapp.domain.repository

import com.assignment.androidapp.domain.model.User


interface UserRepository {
    suspend fun getUserByEmail(email: String): User?
    suspend fun insertUser(user: User):Long
}
