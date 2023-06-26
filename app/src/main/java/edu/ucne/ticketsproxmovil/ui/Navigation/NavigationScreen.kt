package edu.ucne.ticketsproxmovil.ui.Navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import  edu.ucne.ticketsproxmovil.ui.presentation.screens.home.HomeScreen

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Filled.Home)
    object Settings : Screen("settings", "Settings", Icons.Filled.Settings)
}

@Composable
fun NavigationScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val currentRoute = navController.currentDestination?.route

                BottomNavigationItem(
                    selected = currentRoute == Screen.Home.route,
                    onClick = {
                        navController.navigate(Screen.Home.route) {
                            launchSingleTop = true
                        }
                    },
                    icon = { Icon(Screen.Home.icon, contentDescription = Screen.Home.label) },
                    label = { Text(Screen.Home.label) }
                )

                BottomNavigationItem(
                    selected = currentRoute == Screen.Settings.route,
                    onClick = {
                        navController.navigate(Screen.Settings.route) {
                            launchSingleTop = true
                        }
                    },
                    icon = { Icon(Screen.Settings.icon, contentDescription = Screen.Settings.label) },
                    label = { Text(Screen.Settings.label) }
                )
            }
        }
    ) {
        NavHost(navController, startDestination = Screen.Home.route) {
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.Settings.route) {
                SettingsScreen()
            }
        }
    }
}

//@Composable
//fun HomeScreen() {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = androidx.compose.ui.Alignment.Center
//    ) {
//        Text(text = "Home Screen", style = MaterialTheme.typography.h4)
//    }
//}

@Composable
fun SettingsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Text(text = "Settings Screen", style = MaterialTheme.typography.h4)
    }
}

@Preview
@Composable
fun PreviewNavigationScreen() {
    NavigationScreen()
}
