package edu.ucne.ticketsproxmovil.ui.presentation.screens.Eventos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import edu.ucne.ticketsproxmovil.data.remote.dto.EventoDTO
import  edu.ucne.ticketsproxmovil.data.remote.Repository.RepositoryEvento

data class EventoViewModelUiState(
    val evento: List<EventoDTO> = emptyList()
)
@HiltViewModel
class EventoViewModel @Inject constructor(
    private val repEvento: RepositoryEvento
): ViewModel() {

    private val _uiState = MutableStateFlow(EventoViewModelUiState())
    val uiState: StateFlow<EventoViewModelUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update {
                it.copy(evento = repEvento.Getevento())
            }
        }
    }
}