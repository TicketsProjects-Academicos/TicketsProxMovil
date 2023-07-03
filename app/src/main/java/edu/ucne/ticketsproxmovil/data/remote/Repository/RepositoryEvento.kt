package edu.ucne.ticketsproxmovil.data.remote.repositoryEvento

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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