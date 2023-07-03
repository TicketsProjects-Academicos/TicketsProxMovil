package edu.ucne.ticketsproxmovil.ui.presentation.screens.Login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.ticketsproxmovil.data.remote.Repository.RepositoryClientes
import edu.ucne.ticketsproxmovil.data.remote.dto.LoginDto
import edu.ucne.ticketsproxmovil.data.remote.dto.LoginRep
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repositoryClientes: RepositoryClientes
): ViewModel() {

    private val _loginResult = MutableStateFlow<LoginRep?>(null)
    val loginResult: StateFlow<LoginRep?> = _loginResult


   fun Login(loginDto: LoginDto){
       viewModelScope.launch {
           try {
               val (success, message, nombre, correo,  result) = repositoryClientes.LoginCliente(loginDto)
               _loginResult.value = LoginRep(success, message, nombre, correo, result)
               if (success) {
                  println("Sesión iniciada correctamente: $success")
                   println("Message: $message")
                   println("Nombre: $nombre")
               } else {
                  println("Sesion fallida, credenciales incorrectas")

               }
           } catch (e: Exception) {
             println("Exception: $e")
           }
       }

  }
}