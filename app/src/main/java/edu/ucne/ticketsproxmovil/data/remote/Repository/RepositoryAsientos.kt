package edu.ucne.ticketsproxmovil.data.remote.Repository

import javax.inject.Inject
import  edu.ucne.ticketsproxmovil.data.remote.ApiTickets
import  edu.ucne.ticketsproxmovil.data.remote.dto.AsientosDTO

class RepositoryAsientos @Inject constructor(
    private val apiTickets: ApiTickets
){
    suspend fun GetAsientos(): List<AsientosDTO> {

        try {
            val asientos = apiTickets.GetAsientos()
            return asientos
        }catch (e: Exception) {
            throw e
        }
    }

    suspend fun PutAsientos(id: Int, asientosDTO: AsientosDTO): List<AsientosDTO> {

        try {

            val asientoupdate = apiTickets.PutAsientos(id, asientosDTO)
            return asientoupdate.body()?: emptyList()

        }catch (e:Exception) {
            throw e
        }
    }
}