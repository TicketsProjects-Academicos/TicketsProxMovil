package edu.ucne.ticketsproxmovil.data.remote.dto

import java.time.LocalDate
import java.time.LocalDateTime


data class EventoDTO(
    val idEventos: Int,
    val nombreEvento: String,
    val descripcion: String,
    val image: String,
    val lugarEvento: String,
    val tipoEvento: String,
    val capacidadTotal: String,
    val hora: String,
)

