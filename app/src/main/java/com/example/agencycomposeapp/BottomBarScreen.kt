package com.example.agencycomposeapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object AllFlats : BottomBarScreen(
        route = "allFlats",
        title = "ALL FLATS",
        icon = Icons.Default.Home
    )

    object Contracts : BottomBarScreen(
        route = "contracts",
        title = "CONTRACTS",
        icon = Icons.Default.Info
    )

    object Profile : BottomBarScreen(
        route = "profile",
        title = "PROFILE",
        icon = Icons.Default.Person
    )
}
