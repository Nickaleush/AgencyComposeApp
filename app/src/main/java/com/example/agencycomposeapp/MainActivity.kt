package com.example.agencycomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.agencycomposeapp.navigation.graphs.RootNavigationGraph
import com.example.agencycomposeapp.ui.theme.AgencyComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AgencyComposeAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    RootNavigationGraph(navController = rememberNavController())
                }
            }
        }
    }
}