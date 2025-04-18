package com.assignment.androidapp.presentation.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.assignment.androidapp.presentation.home.HomeScreen
import com.assignment.androidapp.presentation.home.manga.MangaViewModel
import com.assignment.androidapp.presentation.signin.SignInScreen

object Routes {
    const val SIGN_IN = "sign_in"
    const val HOME = "home"
    const val MANGA = "manga"
    const val FACE = "face"
}
@Composable
fun MainNavigationGraph(
    navController: NavHostController,
    isUserLoggedIn: Boolean,
    onSignInSuccess: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = if (isUserLoggedIn) Routes.HOME else Routes.SIGN_IN
    ) {
        composable(Routes.SIGN_IN) {
            SignInScreen(onSignInSuccess = onSignInSuccess)
        }

        composable(Routes.HOME) {
            val viewModel: MangaViewModel = hiltViewModel()
            HomeScreen(viewModel)
        }
    }
}

