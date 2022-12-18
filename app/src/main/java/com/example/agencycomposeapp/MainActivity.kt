package com.example.agencycomposeapp

import android.app.Application
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.agencycomposeapp.MainViewModel
import com.example.agencycomposeapp.MainViewModelFactory
import com.example.agencycomposeapp.navigation.graphs.RootNavigationGraph
import com.example.agencycomposeapp.ui.theme.AgencyComposeAppTheme
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AgencyComposeAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val context = LocalContext.current
                    val viewModel: MainViewModel = viewModel(
                        factory = MainViewModelFactory(context.applicationContext as Application)
                    )
                    viewModel.initDatabase {
                        Toast.makeText(viewModel.getApplication(), "Inited", Toast.LENGTH_SHORT).show()
                    }
                    RootNavigationGraph(navController = rememberNavController(), viewModel)
                }
            }
        }
    }

    companion object {
        var DIRECTOR_MODE = false
        var DIRECTOR_ID: UUID = UUID.randomUUID()
        var DIRECTOR_NAME = ""
        var DIRECTOR_PHONE = ""
        var CLIENT_ID: UUID = UUID.randomUUID()
        var AGENCY_ID: UUID = UUID.randomUUID()
        var CLIENT_NAME = ""
        var CLIENT_PHONE = ""
        var FLAT_INDEX = 0
        var CLIENT_TO_DELETE_ID: UUID = UUID.randomUUID()
        var FLAT_ID: UUID = UUID.randomUUID()
    }

}