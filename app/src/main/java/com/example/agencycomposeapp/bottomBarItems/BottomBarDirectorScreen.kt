package com.example.agencycomposeapp.bottomBarItems

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarDirectorScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object AgencyFlats : BottomBarDirectorScreen(
        route = "agencyFlats",
        title = "AGENCY_FLATS",
        icon = Icons.Default.Email
    )

    object AgencyClients : BottomBarDirectorScreen(
        route = "agencyClients",
        title = "AGENCY_CLIENTS",
        icon = Icons.Default.Person
    )

    object ProfileDirector : BottomBarDirectorScreen(
        route = "profileDirector",
        title = "PROFILE_DIRECTOR",
        icon = Icons.Default.Person
    )
}