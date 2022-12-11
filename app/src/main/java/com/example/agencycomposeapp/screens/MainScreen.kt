package com.example.agencycomposeapp.screens

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.agencycomposeapp.BottomBarScreen
import com.example.agencycomposeapp.navigation.graphs.MainNavGraph
import com.example.agencycomposeapp.R
import com.example.agencycomposeapp.ui.theme.GTEestiProText
import com.example.agencycomposeapp.ui.theme.Typography

@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        MainNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.AllFlats,
        BottomBarScreen.Contracts,
        BottomBarScreen.Profile,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        BottomNavigation(backgroundColor = Color.White) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(
                text =
                when (screen.title) {
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
            when (screen.title) {
                "ALLFLATS" -> Icon(
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
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        alwaysShowLabel = false
    )
}