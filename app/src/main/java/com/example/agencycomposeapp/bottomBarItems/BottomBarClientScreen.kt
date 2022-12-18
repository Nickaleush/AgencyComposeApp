package com.example.agencycomposeapp.bottomBarItems

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarClientScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object AllFlats : BottomBarClientScreen(
        route = "allFlats",
        title = "ALL FLATS",
        icon = Icons.Default.Home
    )

    object Contracts : BottomBarClientScreen(
        route = "contracts",
        title = "CONTRACTS",
        icon = Icons.Default.Info
    )

    object ProfileClient : BottomBarClientScreen(
        route = "profile",
        title = "PROFILE",
        icon = Icons.Default.Person
    )
}
