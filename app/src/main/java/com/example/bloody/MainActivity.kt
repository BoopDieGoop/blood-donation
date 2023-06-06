@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.example.bloody

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scoff()
                }
            }
        }
    }
}

enum class Screens {
    Login,
    Info
}

@Composable
fun Scoff() {
    val navController = rememberNavController()
    Scaffold(topBar = { Top(navController) }) { padding ->
        NavHost(navController = navController, startDestination = Screens.Login.name) {
            composable(route = Screens.Login.name) {
                LogScreen(padding, LocalContext.current, navController)
            }
            composable(route = Screens.Info.name) {
                DataScreen(padding = padding, context = LocalContext.current)
            }
        }
    }
}

@Composable
fun Top(navController: NavHostController) {
    TopAppBar(title = { Text("Donations") }, actions = {
        IconButton(onClick = {
            navController.popBackStack(Screens.Login.name, inclusive = false)
        }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = ""
            )
        }
    })
}