package com.example.agencycomposeapp.navigation.graphs

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.agencycomposeapp.MainViewModel
import com.example.agencycomposeapp.screens.MainScreen

@ExperimentalMaterialApi
@Composable
fun RootNavigationGraph(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {
        authNavGraph(navController = navController, viewModel)

        composable(route = Graph.MAIN) {
            MainScreen(rememberNavController())
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val MAIN = "main_graph"
}