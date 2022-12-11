package com.example.agencycomposeapp.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.agencycomposeapp.screens.auth.AuthScreen
import com.example.agencycomposeapp.screens.contracts.ContractsScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {
        composable(route = AuthScreen.Login.route) {
            AuthScreen(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.MAIN)
                },
                onSignUpClick = {
                    navController.navigate(AuthScreen.SignUp.route)
                },
                onForgotClick = {
                    navController.navigate(AuthScreen.Forgot.route)
                }
            )
        }
        composable(route = AuthScreen.SignUp.route) {
            ContractsScreen(name = AuthScreen.SignUp.route) {

            }
        }
        composable(route = AuthScreen.Forgot.route) {
            ContractsScreen(name = AuthScreen.Forgot.route) {
            }
        }
    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
    object SignUp : AuthScreen(route = "SIGN_UP")
    object Forgot : AuthScreen(route = "FORGOT")
}