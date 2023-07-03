package edu.ucne.ticketsproxmovil.data.remote.Repository

import javax.inject.Inject
import  edu.ucne.ticketsproxmovil.data.remote.ApiTickets
import  edu.ucne.ticketsproxmovil.data.remote.dto.SeccionesDTO

class RepositorySecciones @Inject constructor(
    private val apiTickets: ApiTickets
){
    suspend fun GetSecciones(): List<SeccionesDTO> {

        try {
            val secciones = apiTickets.GetSecciones()
            return secciones
        }catch (e: Exception) {
            throw e
        }
    }
}