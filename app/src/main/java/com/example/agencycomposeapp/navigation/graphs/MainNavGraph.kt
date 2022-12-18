package com.example.agencycomposeapp.navigation.graphs

import android.app.Activity
import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.agencycomposeapp.bottomBarItems.BottomBarClientScreen
import com.example.agencycomposeapp.bottomBarItems.BottomBarDirectorScreen
import com.example.agencycomposeapp.MainActivity.Companion.DIRECTOR_MODE
import com.example.agencycomposeapp.MainViewModel
import com.example.agencycomposeapp.screens.flats.ClientFlatsScreen
import com.example.agencycomposeapp.screens.flats.AgencyFlatsScreen
import com.example.agencycomposeapp.screens.flats.AllFlatsScreen
import com.example.agencycomposeapp.screens.clients.AgencyClientsScreen
import com.example.agencycomposeapp.screens.profile.DirectorProfileScreen
import com.example.agencycomposeapp.screens.profile.ClientProfileScreen

@Composable
fun MainNavGraph(navController: NavHostController, modifier: Modifier) {
    val context = LocalContext.current
    val application = context.applicationContext as Application
    val activity = (LocalContext.current as? Activity)
    if (DIRECTOR_MODE) {
        NavHost(
            navController = navController,
            route = Graph.MAIN,
            startDestination = BottomBarDirectorScreen.AgencyFlats.route
        ) {
            composable(route = BottomBarDirectorScreen.AgencyFlats.route) {
                AgencyFlatsScreen(
                    viewModel = MainViewModel(application)
                )
            }

            composable(route = BottomBarDirectorScreen.AgencyClients.route) {
                AgencyClientsScreen(
                    viewModel = MainViewModel(application)
                )
            }

            composable(route = BottomBarDirectorScreen.ProfileDirector.route) {
                DirectorProfileScreen(
                    onClick = {
                        activity?.finish()
                    },
                    viewModel = MainViewModel(application)
                )
            }
        }

    } else {
        NavHost(
            navController = navController,
            route = Graph.MAIN,
            startDestination = BottomBarClientScreen.AllFlats.route
        ) {
            composable(route = BottomBarClientScreen.AllFlats.route) {
                AllFlatsScreen(
                    viewModel = MainViewModel(application)
                )
            }
            composable(route = BottomBarClientScreen.Contracts.route) {
                ClientFlatsScreen(
                    viewModel = MainViewModel(application)
                )
            }
            composable(route = BottomBarClientScreen.ProfileClient.route) {
                ClientProfileScreen(
                    onClick = {
                        activity?.finish()
                    }
                )
            }
        }
    }
}

