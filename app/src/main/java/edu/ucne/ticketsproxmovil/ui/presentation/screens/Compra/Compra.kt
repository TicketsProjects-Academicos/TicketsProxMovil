package edu.ucne.ticketsproxmovil.ui.presentation.screens.Compra

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight.Companion.Black
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import edu.ucne.ticketsproxmovil.data.remote.dto.AsientosDTO
import edu.ucne.ticketsproxmovil.data.remote.dto.SeccionesDTO
import edu.ucne.ticketsproxmovil.ui.Navigation.Destinations
import edu.ucne.ticketsproxmovil.ui.presentation.screens.Asientos.AsientosViewModel
import edu.ucne.ticketsproxmovil.ui.presentation.screens.Eventos.EventoViewModel

@Composable
fun TicketPurchaseScreen(
    idEventos: String?,
    idSeccion: (Int) -> Unit,
    viewModel: EventoViewModel = hiltViewModel(),
    viewModelComprar: CompraViewModel = hiltViewModel(),
    viewModelAsiento: AsientosViewModel = hiltViewModel(),
    navController: NavHostController

) {
    var ticketQuantity by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()
    val uiStateSeccion by viewModelComprar.uiStateSecciones.collectAsState()

    val id = idEventos?.toInt()
    val evento = uiState.evento.firstOrNull { it.idEventos == id }
    val seccionEventos = uiStateSeccion.seccion.filter { it.idEventos == evento?.idEventos }

    val uiStateAsiento by viewModelAsiento.uiStateAsiento.collectAsState()
    var selectedSeccion by remember { mutableStateOf("") }
    var selectedAsientos = remember { mutableStateListOf<AsientosDTO>() }
    val AsientosSaveSELECT: MutableList<AsientosDTO> = mutableListOf()
    var showDialog by remember { mutableStateOf(false) }

    fun AddAsiento(asientosDTO: AsientosDTO) {
        println("Asiento recibido: $asientosDTO")
        AsientosSaveSELECT.add(asientosDTO)
        AsientosSaveSELECT.forEach { asientores ->

        }
        println("Asientos ADD : ${AsientosSaveSELECT}")
    }

    fun ReservarAsientos() {


        AsientosSaveSELECT.forEach { asientosDTO ->



            if (evento != null) {
                asientosDTO.reservado = true
                viewModelComprar.SaveReservas(
                    cliente = "Juan Lopez",
                    evento = evento.nombreEvento,
                    seccion = selectedSeccion,
                    asiento = asientosDTO.numero,
                    idasiento = asientosDTO.id,
                    asientosDTO = asientosDTO
                    )
            }

            println("Save: ${asientosDTO.numero}")
        }

        showDialog = true

//        println("Asirnto a comprar: $AsientosSaveSELECT")
    }


    Column(modifier = Modifier.padding(start = 16.dp, top = 10.dp, end = 16.dp)) {
        if (evento != null) {
            Text(
                text = "Tickets    ${evento.nombreEvento}",
                style = MaterialTheme.typography.h6
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.padding(4.dp)) {
                Image(
                    painter = rememberImagePainter(data = evento.image),
                    contentDescription = evento.descripcion,
                    modifier = Modifier
                        .height(200.dp)
                        .width(200.dp)
                        .padding(top = 5.dp)
                )

                Column(modifier = Modifier.padding(start = 16.dp)) {
                    Text(
                        text = evento.hora,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = evento.lugarEvento,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = evento.descripcion,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    ReservarAsientos()

                },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(4.dp)
            ) {
                Text(text = "Comprar")
            }


            Spacer(modifier = Modifier.height(16.dp))
            DropdownMenuExample(seccionEventos) { selectedItem ->
                selectedSeccion = selectedItem
            }
            Spacer(modifier = Modifier.height(16.dp))
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
                    .padding(10.dp)
            ) {


                val seccionFil = seccionEventos.firstOrNull { it.nombreSeccion == selectedSeccion }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    contentPadding = PaddingValues(16.dp),
                    modifier = Modifier.padding(4.dp)
                ) {

                    items(uiStateAsiento.asientos) { asiento ->
                        if (seccionFil != null) {
                            if (seccionFil.idSecciones == asiento.idSecciones) {
                                Column {
                                    Text(
                                        text = asiento.numero,
                                        color = if (asiento.reservado == true) Color.Red else Color.Black,
                                        modifier = Modifier
                                            .padding(5.dp),
                                        fontSize = 18.sp,
                                    )
                                    Checkbox(
                                        checked = selectedAsientos.contains(asiento),
                                        enabled = !asiento.reservado,
                                        onCheckedChange = { isChecked ->
                                            if (isChecked) {
                                                AddAsiento(asiento)
//                                                viewModelReservas.comprarTickets(asiento)
                                                selectedAsientos.add(asiento)
                                            } else {
                                                selectedAsientos.remove(asiento)
                                            }
                                        },
                                        modifier = Modifier.padding(5.dp),


                                        )
                                }
                            }
                        }


                    }


                }
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text(text = "Reservado") },
                    text = {

                        Box(modifier = Modifier.fillMaxWidth().wrapContentHeight(Alignment.CenterVertically)) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Reserva exitosa"
                                )
                                Text(text = "¡La reserva se ha realizado con éxito!")
                            }
                        }

                    },
                    confirmButton = {
                        Button(onClick = { showDialog = false }) {
                            Text(text = "Aceptar")
                        }
                    },
                    dismissButton = {
                        Button(onClick = { showDialog = false }) {
                            Text(text = "Cancelar")
                        }
                    }
                )
            }

        }
    }

}

@Composable
fun DropdownMenuExample(
    seccionEventos: List<SeccionesDTO>,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("") }

    Column {
        Button(onClick = { expanded = true }) {
            Text(text = if (selectedItem.isNotEmpty()) selectedItem else "Select an Section")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            seccionEventos.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        selectedItem = item.nombreSeccion
                        onItemSelected(selectedItem)
                        expanded = false
                    }
                ) {
                    Text(text = item.nombreSeccion)
                }
            }
        }
    }
}




