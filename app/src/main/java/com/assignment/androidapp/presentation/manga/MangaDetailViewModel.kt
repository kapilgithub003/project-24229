//package com.assignment.androidapp.presentation.home.manga
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.assignment.androidapp.domain.use_case.GetMangaDetailUseCase
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//class MangaDetailViewModel @Inject constructor(
//    private val getMangaDetailUseCase: GetMangaDetailUseCase
//) : ViewModel() {
//
//    fun getMangaDetail(mangaId: String): Manga? {
//        var manga: Manga? = null
//        viewModelScope.launch {
//            manga = getMangaDetailUseCase.execute(mangaId)
//        }
//        return manga
//    }
//}