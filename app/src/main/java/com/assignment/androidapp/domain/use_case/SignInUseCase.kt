package com.assignment.androidapp.domain.use_case

import com.assignment.androidapp.domain.model.User
import com.assignment.androidapp.domain.repository.UserRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val repository: UserRepository) {
    suspend fun signIn(email: String, password: String): Boolean {
        val user = repository.getUserByEmail(email)
        return if (user == null) {
            repository.insertUser(User(email=email, password = password))
            true
        } else {
            user.password == password
        }
    }
}
