package edu.ucne.ticketsproxmovil.data.remote.Repository

import edu.ucne.ticketsproxmovil.data.remote.ApiTickets
import edu.ucne.ticketsproxmovil.data.remote.dto.EventoDTO
import edu.ucne.ticketsproxmovil.data.remote.dto.LoginDto
import edu.ucne.ticketsproxmovil.data.remote.dto.LoginRep
import javax.inject.Inject

class RepositoryClientes @Inject constructor(
    private val apievento: ApiTickets
){
    suspend fun LoginCliente(loginDto: LoginDto): LoginRep {

        try {
            val Login = apievento.LoginPost(loginDto)
            return Login
        }catch (e: Exception) {
            throw e
        }
    }
}