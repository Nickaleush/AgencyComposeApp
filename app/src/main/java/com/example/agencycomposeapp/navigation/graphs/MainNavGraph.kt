package com.example.agencycomposeapp.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.agencycomposeapp.BottomBarScreen
import com.example.agencycomposeapp.screens.allFlats.AllFlatsScreen
import com.example.agencycomposeapp.screens.contracts.ContractsScreen
import com.example.agencycomposeapp.screens.contracts.ProfileScreen

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = BottomBarScreen.AllFlats.route
    ) {
        composable(route = BottomBarScreen.AllFlats.route) {
            AllFlatsScreen(
                name = BottomBarScreen.AllFlats.route,
                onClick = {
                    navController.navigate(Graph.DETAILS)
                }
            )
        }
        composable(route = BottomBarScreen.Contracts.route) {
            ContractsScreen(
                name = BottomBarScreen.Contracts.route,
                onClick = { }
            )
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen(
                name = BottomBarScreen.Profile.route,
                onClick = { }
            )
        }
        detailsNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.Information.route
    ) {
        composable(route = DetailsScreen.Information.route) {
            ContractsScreen(name = DetailsScreen.Information.route) {
                navController.navigate(DetailsScreen.Overview.route)
            }
        }
        composable(route = DetailsScreen.Overview.route) {
            ContractsScreen(name = DetailsScreen.Overview.route) {
                navController.popBackStack(
                    route = DetailsScreen.Information.route,
                    inclusive = false
                )
            }
        }
    }
}

sealed class DetailsScreen(val route: String) {
    object Information : DetailsScreen(route = "INFORMATION")
    object Overview : DetailsScreen(route = "OVERVIEW")
}
