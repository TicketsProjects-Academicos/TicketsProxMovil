package edu.ucne.ticketsproxmovil.ui.presentation.screens.Asientos



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.ticketsproxmovil.data.remote.Repository.RepositoryAsientos
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import edu.ucne.ticketsproxmovil.data.remote.dto.AsientosDTO



data class AsientoViewModelUIState(
    val asientos: List<AsientosDTO> = emptyList()
)
@HiltViewModel
class AsientosViewModel @Inject constructor(
    private val repAsiento: RepositoryAsientos
): ViewModel() {


    private val _uiStateAsiento = MutableStateFlow(AsientoViewModelUIState())
    val uiStateAsiento: StateFlow<AsientoViewModelUIState> = _uiStateAsiento.asStateFlow()

    init {


            viewModelScope.launch {
                _uiStateAsiento.update {
                    it.copy(asientos = repAsiento.GetAsientos())
                }
            }

    }
}


