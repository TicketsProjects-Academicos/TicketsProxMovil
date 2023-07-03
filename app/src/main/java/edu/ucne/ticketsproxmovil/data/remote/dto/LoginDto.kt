package edu.ucne.ticketsproxmovil.data.remote.dto

data class LoginDto(
    val email: String,
    val password: String
)

data class LoginRep(
    val succes: Boolean,
    val message: String,
    val nombre: String,
    val correo: String,
    val result: String
)