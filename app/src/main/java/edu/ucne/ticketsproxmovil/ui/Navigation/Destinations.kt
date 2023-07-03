package edu.ucne.ticketsproxmovil.ui.Navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Chair
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.AirlineSeatFlat
import androidx.compose.ui.graphics.vector.ImageVector
import edu.ucne.ticketsproxmovil.data.remote.dto.EventoDTO

sealed class Destinations(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object EventScreen: Destinations("PreviewEventList", "Evento", Icons.Filled.Album)
    object  HomeScreen: Destinations("HomeScreen", "Home", Icons.Filled.Home)
    object  TicketPurchaseScreen: Destinations("TicketPurchaseScreen/?idEventos={idEventos}", "Tickets", Icons.Filled.AddBox){
        fun createRouteCompra(idEventos: Int) = "TicketPurchaseScreen/?idEventos=$idEventos"
    }
    object AsientoScreen: Destinations("AsientoScreen/?idSeccion={idSeccion}", "Asientos", Icons.Filled.Chair){
        fun  createRouteAsiento(idSeccion: Int) = "AsientoScreen/?idSeccion=$idSeccion"
    }
    object ReservadoScreen: Destinations("ReservadoScreen", "Reservado", Icons.Filled.AirlineSeatFlat)

    object LoginScreen: Destinations("LoginScreen", "Login", Icons.Filled.Person)





}
