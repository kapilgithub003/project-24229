package com.assignment.androidapp.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.androidapp.domain.model.User
import com.assignment.androidapp.data.local.dao.UserDao
import com.assignment.androidapp.domain.model.UserSession
import com.assignment.androidapp.data.local.dao.UserSessionDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userDao: UserDao,
    private val userSessionDao: UserSessionDao
) : ViewModel() {

    private val _signInSuccess = MutableStateFlow(false)
    val signInSuccess: StateFlow<Boolean> = _signInSuccess

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            val user = userDao.getUserByEmail(email)
            if (user == null) {
                val userId = userDao.insertUser(User(email = email, password = password)).toInt()
                userSessionDao.insertSession(UserSession(userId = userId))
                _signInSuccess.value = true
            } else if (user.password == password) {
                userSessionDao.insertSession(UserSession(userId = user.id))
                _signInSuccess.value = true
            }
        }
    }
}


