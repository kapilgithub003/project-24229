//package com.assignment.androidapp.domain.use_case
//
//import javax.inject.Inject
//
//class GetMangaDetailUseCase @Inject constructor(
//    private val mangaRepository: MangaRepository
//) {
//
//    suspend fun execute(mangaId: String): Manga? {
//        return mangaRepository.getMangasFromDatabase()
//            .load(0).firstOrNull { it.id == mangaId }
//    }
//}