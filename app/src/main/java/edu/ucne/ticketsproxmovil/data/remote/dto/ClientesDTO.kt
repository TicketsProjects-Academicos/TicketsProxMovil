package edu.ucne.ticketsproxmovil.data.remote.dto

data class ClientesDTO(
    val idCliente: Int,
    val nombre: String,
    val apellido: String,
    val identificacion: String,
    val correo: String,
    val password: String
)

