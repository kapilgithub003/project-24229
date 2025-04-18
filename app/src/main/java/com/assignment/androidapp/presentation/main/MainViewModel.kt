package com.assignment.androidapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.androidapp.data.local.dao.UserSessionDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userSessionDao: UserSessionDao
) : ViewModel() {

    private val _isUserLoggedIn = MutableStateFlow(false)
    val isUserLoggedIn: StateFlow<Boolean> = _isUserLoggedIn

    init {
        viewModelScope.launch {
            val session = userSessionDao.getSession()
            _isUserLoggedIn.value = session != null
        }
    }

    fun setLoggedIn(isLoggedIn: Boolean) {
        _isUserLoggedIn.value = isLoggedIn
    }
}
