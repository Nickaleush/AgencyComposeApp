package com.example.agencycomposeapp.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.agencycomposeapp.MainViewModel
import com.example.agencycomposeapp.screens.auth.SignInScreen
import com.example.agencycomposeapp.screens.auth.DirectorSignUpScreen
import com.example.agencycomposeapp.screens.auth.ClientSignUpScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController, viewModel: MainViewModel) {

    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {

        composable(route = AuthScreen.Login.route) {
            SignInScreen(
                onSignUpClick = {
                    navController.navigate(AuthScreen.SignUp.route)
                },
                onOpenAgencyClick = {
                    navController.navigate(AuthScreen.OpenAgency.route)
                },
                viewModel = viewModel,
                navController = navController
            )
        }

        composable(route = AuthScreen.SignUp.route) {
            ClientSignUpScreen(viewModel = viewModel,
                onClick = {
                    navController.navigate(Graph.MAIN)
                }
            )
        }

        composable(route = AuthScreen.OpenAgency.route) {
            DirectorSignUpScreen(viewModel = viewModel,
                onClick = {
                    navController.navigate(Graph.MAIN)
                }
            )
        }
    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
    object SignUp : AuthScreen(route = "SIGN_UP")
    object OpenAgency : AuthScreen(route = "OPEN_AGENCY")
}