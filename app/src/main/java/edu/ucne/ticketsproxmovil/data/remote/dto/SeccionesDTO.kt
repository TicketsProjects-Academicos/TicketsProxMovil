package edu.ucne.ticketsproxmovil.data.remote.dto

data class SeccionesDTO (
    val idSecciones: Int,
    val idEventos: Int,
    val nombreSeccion: String,
    val capacidad: Int,
    val precio: Double,
)
