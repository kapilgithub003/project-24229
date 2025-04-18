package com.assignment.androidapp.data.repository

import com.assignment.androidapp.domain.model.User
import com.assignment.androidapp.data.local.dao.UserDao
import com.assignment.androidapp.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun getUserByEmail(email: String) = userDao.getUserByEmail(email)

    override suspend fun insertUser(user: User) = userDao.insertUser(user)
}
