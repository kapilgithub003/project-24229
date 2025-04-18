package com.assignment.androidapp.presentation.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.assignment.androidapp.presentation.main.Routes

@Composable
fun BottomNavBar(navController: NavController, currentRoute: String) {
    val items = listOf(
        BottomNavItem("Manga", Icons.Default.Menu, Routes.MANGA),
        BottomNavItem("Face", Icons.Default.Face, Routes.FACE),
    )

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(Routes.MANGA)
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    }
}

data class BottomNavItem(val title: String, val icon: ImageVector, val route: String)

