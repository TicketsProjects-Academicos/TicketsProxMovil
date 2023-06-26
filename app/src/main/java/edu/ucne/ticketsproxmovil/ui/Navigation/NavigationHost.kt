package edu.ucne.ticketsproxmovil.ui.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

import  edu.ucne.ticketsproxmovil.ui.Navigation.Destinations.*
import  edu.ucne.ticketsproxmovil.ui.presentation.Pantalla1
import  edu.ucne.ticketsproxmovil.ui.presentation.Pantalla2
import  edu.ucne.ticketsproxmovil.ui.presentation.Pantalla3
import  edu.ucne.ticketsproxmovil.ui.presentation.screens.Eventos.PreviewEventList
import  edu.ucne.ticketsproxmovil.ui.presentation.screens.home.HomeScreen


@Composable
fun NavigationHost(
    navController: NavHostController,
    darkMode: MutableState<Boolean>
) {
    NavHost(navController = navController, startDestination = HomeScreen.route) {

        composable(Pantalla1.route) {
            Pantalla1(
                navegarPantalla2 = { newText ->
                    navController.navigate(Pantalla2.createRoute(newText))
                }
            )
        }

        composable(HomeScreen.route) {
          HomeScreen()
        }
        composable(EventScreen.route) {
            PreviewEventList()
        }



        composable(
            Pantalla2.route,
            arguments = listOf(navArgument("newText"){ defaultValue = "Pantalla 2" })
        ) { navBackStackEntry ->
            var newText = navBackStackEntry.arguments?.getString("newText")
            requireNotNull(newText)
            Pantalla2(newText, darkMode)
        }

        composable(Pantalla3.route) {
            Pantalla3()
        }
    }
}