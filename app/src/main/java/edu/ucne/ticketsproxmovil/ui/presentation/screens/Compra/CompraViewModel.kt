package edu.ucne.ticketsproxmovil.ui.presentation.screens.Compra

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.ticketsproxmovil.data.remote.Repository.RepositoryAsientos
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import edu.ucne.ticketsproxmovil.data.remote.dto.EventoDTO
import  edu.ucne.ticketsproxmovil.data.remote.Repository.RepositoryEvento
import edu.ucne.ticketsproxmovil.data.remote.Repository.RepositoryReservas
import edu.ucne.ticketsproxmovil.data.remote.Repository.RepositorySecciones
import edu.ucne.ticketsproxmovil.data.remote.dto.AsientosDTO
import edu.ucne.ticketsproxmovil.data.remote.dto.ReservadosDTO
import edu.ucne.ticketsproxmovil.data.remote.dto.SeccionesDTO


data class SeccionesViewModelUIState (
    val seccion: List<SeccionesDTO> = emptyList()
)
@HiltViewModel
class CompraViewModel @Inject constructor(
    private val repSeccion: RepositorySecciones,
    private val repositoryReservas: RepositoryReservas,
    private val repositoryAsientos: RepositoryAsientos
): ViewModel() {






    //Secciones
    private val _uiStateSecciones = MutableStateFlow(SeccionesViewModelUIState())
    val uiStateSecciones: StateFlow<SeccionesViewModelUIState> = _uiStateSecciones.asStateFlow()

    init {

        //Secciones
        viewModelScope.launch {
            _uiStateSecciones.update {
                it.copy(seccion = repSeccion.GetSecciones())
            }
        }
    }



    fun SaveReservas(cliente: String, evento: String, seccion: String, asiento: String, idasiento: Int, asientosDTO: AsientosDTO ) {
        viewModelScope.launch {
            try {
                repositoryReservas.PostReservas(
                    ReservadosDTO(
                        id = 0,
                        cliente = cliente,
                        evento = evento,
                        seccion = seccion,
                        asiento = asiento
                    )
                )
                UpdateAsientos(idasiento, asientosDTO)
            }catch (e: Exception){
                throw e
            }

        }
    }

    fun UpdateAsientos(id: Int, asientosDTO: AsientosDTO) {
        viewModelScope.launch {
            repositoryAsientos.PutAsientos(id, asientosDTO)
        }
    }
}