package edu.ucne.ticketsproxmovil.data.remote.dto

data class ReservadosDTO(
    val id: Int,
    val cliente: String,
    val evento: String,
    val seccion: String,
    val asiento: String
)

