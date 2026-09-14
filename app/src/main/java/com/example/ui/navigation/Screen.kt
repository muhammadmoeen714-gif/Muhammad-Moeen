package com.example.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.School
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Default.Home)
    object Alerts : Screen("alerts", "Test Dates", Icons.Default.NotificationsActive)
    object Lectures : Screen("lectures", "Lectures", Icons.Default.PlayCircle)
    object Calculator : Screen("calculator", "Merit Calc", Icons.Default.Calculate)
    object Saved : Screen("saved", "My Alerts", Icons.Default.School)

    // Player screen (internal route)
    object LecturePlayer : Screen("player", "Lecture Player", Icons.Default.PlayCircle)
}

val BOTTOM_NAV_ITEMS = listOf(
    Screen.Home,
    Screen.Alerts,
    Screen.Lectures,
    Screen.Calculator,
    Screen.Saved
)
