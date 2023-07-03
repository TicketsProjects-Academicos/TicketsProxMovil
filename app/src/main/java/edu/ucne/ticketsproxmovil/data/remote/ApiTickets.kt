package edu.ucne.ticketsproxmovil.data.remote

import edu.ucne.ticketsproxmovil.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.*

interface ApiTickets {

    @GET("api/Eventos")
    suspend fun GetEvento(): List<EventoDTO>

    @GET("api/Secciones")
    suspend fun GetSecciones(): List<SeccionesDTO>

    @GET("api/Asientos")
    suspend fun GetAsientos(): List<AsientosDTO>

    @PUT("api/Asientos/{Id}")
    suspend fun PutAsientos(@Path("Id") Id: Int, @Body asientosDTO: AsientosDTO): Response<List<AsientosDTO>>

    @GET("api/Reservas")
    suspend fun GetReservas(): List<ReservadosDTO>

   @POST("api/Reservas")
   suspend fun PostReservacion(@Body reservadosDTO: ReservadosDTO): Response<ReservadosDTO>

   @POST("api/ClientesControllers")
   suspend fun LoginPost(@Body loginDto: LoginDto) : LoginRep
}