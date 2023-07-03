package edu.ucne.ticketsproxmovil.data.remote.Repository

import javax.inject.Inject
import  edu.ucne.ticketsproxmovil.data.remote.ApiTickets
import  edu.ucne.ticketsproxmovil.data.remote.dto.EventoDTO

class RepositoryEvento @Inject constructor(
    private val apievento: ApiTickets
){
    suspend fun Getevento(): List<EventoDTO> {

        try {
            val evento = apievento.GetEvento()
            return evento
        }catch (e: Exception) {
            throw e
        }
    }
}