package edu.ucne.ticketsproxmovil.data.remote.Repository

import edu.ucne.ticketsproxmovil.data.remote.ApiTickets
import edu.ucne.ticketsproxmovil.data.remote.dto.ReservadosDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryReservas  @Inject constructor(
    private val apiTickets: ApiTickets
) {
    suspend fun GetReservas(): List<ReservadosDTO> {
        try {
            val reserva = apiTickets.GetReservas()
            return  reserva
        }catch (e: Exception) {
            throw e
        }
    }

    suspend fun PostReservas(reservadosDTO: ReservadosDTO): ReservadosDTO? {
        try {
            val response = apiTickets.PostReservacion(reservadosDTO)
            if (response.isSuccessful) {
                val reservados = response.body()
                return reservados
            } else {
                throw Exception("Error")
            }
        } catch (e: Exception) {
            throw e
        }
    }



}