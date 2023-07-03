package edu.ucne.ticketsproxmovil.data.remote.dto

data class AsientosDTO(
    val id: Int,
    val idSecciones: Int,
    val numero: String,
    var reservado: Boolean
)

