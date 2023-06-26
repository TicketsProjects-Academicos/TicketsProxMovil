package edu.ucne.ticketsproxmovil.data.remote

import edu.ucne.ticketsproxmovil.data.remote.dto.EventoDTO
import retrofit2.Response
import retrofit2.http.GET

interface ApiTickets {

    @GET("api/Eventos")
    suspend fun GetEvento(): List<EventoDTO>
}