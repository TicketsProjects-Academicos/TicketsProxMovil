package edu.ucne.ticketsproxmovil.ui.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

import  edu.ucne.ticketsproxmovil.ui.Navigation.Destinations.*
import  edu.ucne.ticketsproxmovil.ui.presentation.screens.Eventos.PreviewEventList
import  edu.ucne.ticketsproxmovil.ui.presentation.screens.home.HomeScreen
import  edu.ucne.ticketsproxmovil.ui.presentation.screens.Compra.TicketPurchaseScreen
import  edu.ucne.ticketsproxmovil.ui.presentation.screens.Asientos.AsientoScreen
import edu.ucne.ticketsproxmovil.ui.presentation.screens.Login.LoginScreen


@Composable
fun NavigationHost(
    navController: NavHostController,
    darkMode: MutableState<Boolean>
) {
    NavHost(navController = navController, startDestination = HomeScreen.route) {

        composable(HomeScreen.route) {
          HomeScreen()
        }
        composable(EventScreen.route) {
            PreviewEventList(
                idEventos = {idEventos ->
                    navController.navigate(TicketPurchaseScreen.createRouteCompra(idEventos))

                }
            )
        }

        composable(
            TicketPurchaseScreen.route,
            arguments = listOf(navArgument("idEventos"){defaultValue = "Comprar Tickets"})
        ) {navBackStackEntry ->
            var idEventos = navBackStackEntry.arguments?.getString("idEventos")
            requireNotNull(idEventos)
            TicketPurchaseScreen(
                idEventos,
                idSeccion = {idSeccion ->
                    navController.navigate(AsientoScreen.createRouteAsiento(idSeccion))},
                        navController = navController
            )

        }

        composable(
            AsientoScreen.route,
            arguments = listOf(navArgument("idSeccion"){defaultValue= "asientos "})
        ){ NavBackStackEntry ->
            var idSeccion = NavBackStackEntry.arguments?.getString("idSeccion")
            requireNotNull(idSeccion)
            AsientoScreen(idSeccion)
        }



    }
}

