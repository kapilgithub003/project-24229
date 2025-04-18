package com.assignment.androidapp.presentation.home.manga

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.assignment.androidapp.domain.model.Manga
import com.assignment.androidapp.core.util.NetworkMonitor
import com.assignment.androidapp.data.repository.MangaRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MangaViewModel @Inject constructor(
    private val repository: MangaRepositoryImpl,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _refreshTrigger = MutableStateFlow(Unit)
    private val _isNetworkAvailable = MutableStateFlow(false)

    @OptIn(ExperimentalCoroutinesApi::class)
    val mangaFlow = _refreshTrigger
        .flatMapLatest {
            repository.getMangaPager(context).flow
        }
        .cachedIn(viewModelScope)

    init {
        NetworkMonitor(context) { isConnected ->
            _isNetworkAvailable.value = isConnected
            if (isConnected) {
                refreshData()
            }
        }.register()
    }

    private fun refreshData() {
        _refreshTrigger.value = Unit
    }

    fun getMangaById(id: String): Manga? {
        return runBlocking {
            repository.getMangaById(id)
        }
    }

}





