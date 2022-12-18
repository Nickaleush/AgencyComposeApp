package com.example.agencycomposeapp.screens

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.agencycomposeapp.bottomBarItems.BottomBarClientScreen
import com.example.agencycomposeapp.bottomBarItems.BottomBarDirectorScreen
import com.example.agencycomposeapp.MainActivity.Companion.DIRECTOR_MODE
import com.example.agencycomposeapp.navigation.graphs.MainNavGraph
import com.example.agencycomposeapp.R

@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) { paddingValues ->
        MainNavGraph(
            navController = navController,
            modifier = Modifier.padding(paddingValues = paddingValues)
        )
    }
}

@Composable
fun BottomBar(navController: NavHostController) {

    val clientScreens = listOf(
        BottomBarClientScreen.AllFlats,
        BottomBarClientScreen.Contracts,
        BottomBarClientScreen.ProfileClient,
    )

    val directorScreens = listOf(
        BottomBarDirectorScreen.AgencyFlats,
        BottomBarDirectorScreen.AgencyClients,
        BottomBarDirectorScreen.ProfileDirector,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    if (DIRECTOR_MODE) {
        val bottomBarDirectorDestination =
            directorScreens.any { it.route == currentDestination?.route }
        if (bottomBarDirectorDestination) {
            BottomNavigation(backgroundColor = Color.White) {
                directorScreens.forEach { directorScreen ->
                    AddDirectorItem(
                        directorScreen = directorScreen,
                        currentDestination = currentDestination,
                        navController = navController
                    )
                }
            }
        }

    } else {
        val bottomBarClientDestination = clientScreens.any { it.route == currentDestination?.route }
        if (bottomBarClientDestination) {
            BottomNavigation(backgroundColor = Color.White) {
                clientScreens.forEach { screen ->
                    AddClientItem(
                        clientScreen = screen,
                        currentDestination = currentDestination,
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun RowScope.AddClientItem(
    clientScreen: BottomBarClientScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(
                text =
                when (clientScreen.title) {
                    "ALL FLATS" -> stringResource(id = R.string.allFlats)
                    "CONTRACTS" -> stringResource(id = R.string.contracts)
                    else -> stringResource(id = R.string.profile)
                },
                fontFamily = FontFamily(
                    Font(R.font.gteestiprotext_regular)
                )
            )
        },
        icon = {
            when (clientScreen.title) {
                "ALL FLATS" -> Icon(
                    modifier = Modifier.size(34.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.flats_foreground),
                    contentDescription = "Some icon",
                    tint = colorResource(R.color.blue)
                )
                "CONTRACTS" -> Icon(
                    modifier = Modifier.size(34.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.contracts_foreground),
                    contentDescription = "Some icon",
                    tint = colorResource(R.color.blue)
                )
                else -> Icon(
                    modifier = Modifier.size(34.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.person_foreground),
                    contentDescription = "Some icon",
                    tint = colorResource(R.color.blue)
                )
            }
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == clientScreen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(clientScreen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        alwaysShowLabel = false
    )
}

@Composable
fun RowScope.AddDirectorItem(
    directorScreen: BottomBarDirectorScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(
                text =
                when (directorScreen.title) {
                    "AGENCY_FLATS" -> stringResource(id = R.string.allFlats)
                    "AGENCY_CLIENTS" -> stringResource(id = R.string.yourClients)
                    else -> stringResource(id = R.string.profile)
                },
                fontFamily = FontFamily(
                    Font(R.font.gteestiprotext_regular)
                )
            )
        },
        icon = {
            when (directorScreen.title) {
                "AGENCY_FLATS" -> Icon(
                    modifier = Modifier.size(34.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.flats_foreground),
                    contentDescription = "Some icon",
                    tint = colorResource(R.color.blue)
                )
                "AGENCY_CLIENTS" -> Icon(
                    modifier = Modifier.size(34.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.client_list_icon_foreground),
                    contentDescription = "Some icon",
                    tint = colorResource(R.color.blue)
                )
                else -> Icon(
                    modifier = Modifier.size(34.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.contracts_foreground),
                    contentDescription = "Some icon",
                    tint = colorResource(R.color.blue)
                )
            }
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == directorScreen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(directorScreen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        alwaysShowLabel = false
    )
}