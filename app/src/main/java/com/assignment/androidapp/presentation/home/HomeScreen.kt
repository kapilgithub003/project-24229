package com.assignment.androidapp.presentation.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.assignment.androidapp.presentation.face_recognition.FaceScreen
import com.assignment.androidapp.presentation.home.manga.MangaViewModel
import com.assignment.androidapp.presentation.manga.MangaDetailScreen
import com.assignment.androidapp.presentation.manga.MangaScreen
import com.assignment.androidapp.presentation.main.Routes

@Composable
fun HomeScreen(viewModel: MangaViewModel) {
    val navController = rememberNavController()
    var currentRoute by remember { mutableStateOf(Routes.MANGA) }

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            backStackEntry.destination.route?.let {
                currentRoute = it
            }
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavBar(navController = navController, currentRoute = currentRoute)
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Routes.MANGA,
            modifier = Modifier.padding(padding)
        ) {
            composable(Routes.MANGA) {
                MangaScreen(viewModel = viewModel, navController = navController)
            }
            composable(
                route = "mangaDetail/{mangaId}",
                arguments = listOf(navArgument("mangaId") { type = NavType.StringType })
            ) { backStackEntry ->
                val mangaId = backStackEntry.arguments?.getString("mangaId") ?: return@composable
                val manga = viewModel.getMangaById(mangaId)
                manga?.let {
                    MangaDetailScreen(
                        manga = it,
                        onBackClick = { navController.popBackStack() }
                    )
                }
            }
            composable(Routes.FACE) {
                FaceScreen()
            }
        }
    }
}



