package edu.ucne.ticketsproxmovil.ui.presentation.screens.Asientos

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Checkbox
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import edu.ucne.ticketsproxmovil.data.remote.dto.AsientosDTO
import edu.ucne.ticketsproxmovil.ui.presentation.screens.Compra.CompraViewModel




@Composable
fun AsientoScreen(
    idSeccion: String?,
    viewModelAsiento: AsientosViewModel = hiltViewModel(),
    viewModelSecciones: CompraViewModel = hiltViewModel()
) {
    val id = idSeccion?.toInt()
    val uiStateAsiento by viewModelAsiento.uiStateAsiento.collectAsState()
    val uiStateSecciones by viewModelSecciones.uiStateSecciones.collectAsState()
    val seccion = uiStateSecciones.seccion.firstOrNull {it.idSecciones == id }
    val AsientoSecciones = uiStateAsiento.asientos.filter { it.idSecciones == idSeccion?.toInt()}

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {

                if (seccion != null) {
//                    Text(text = "Seccion: ${seccion.nombreSeccion}", fontSize = 16.sp)
                    Text(
                        "Seccion: ${seccion.nombreSeccion}",
                        modifier = Modifier.padding(20.dp),
                        fontSize = 30.sp)

                }

            Spacer(modifier = Modifier.height(20.dp))
            AsientosList(asientos = AsientoSecciones)
            Spacer(modifier = Modifier.height(40.dp))
            Text(text = "Asiento selecionado")

        }
    }
}

@Composable
fun AsientosList(asientos: List<AsientosDTO>) {
    val selectedAsientos = remember { mutableStateListOf<AsientosDTO>() }
    val asiento = "Asiento selecionaod"
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(6),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.padding(it) ){

            items(asientos) {asiento ->
                val isSelected = selectedAsientos.contains(asiento)
                val checkedState = remember {
                    mutableStateOf(asiento.reservado)
                }
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
                                selectedAsientos.add(asiento)
                            } else {
                                selectedAsientos.remove(asiento)
                            } },
                        modifier = Modifier.padding(5.dp)
                        ,


                    )





                }

            }


        }




    }
}

private fun GetAsientoColor(reservado: Boolean): Color {
    return if (reservado) {
        Color.Red
    } else {
        Color.Green
    }
}

//
//items(selectedAsientos) { asientoselect ->
//    Box(
//        modifier = Modifier
//            .aspectRatio(1f)
//            .padding(10.dp)
//            .background(color = Color.Blue)
//    ){
//        Box(modifier = Modifier.align(Alignment.Center)){
//            Text(text = asientoselect.numero)
//        }
//    }


//                Box(modifier = Modifier
//                    .aspectRatio(1f)
//                    .padding(4.dp)
//                    .background(
//                        color = if (asiento.reservado == true) Color.Red else Color.Green
//                    )
//                    .clickable {
//
//                        if (isSelected) {
//                            asiento.reservado = false
//                            selectedAsientos.remove(asiento)
//                        } else {
//                            asiento.reservado = true
//                            selectedAsientos.add(asiento)
//                        }
//
//
//                    }
//
//
//                ){
//                    Box(modifier = Modifier.align(Alignment.Center)){
//                        Text(text = asiento.numero)
//                    }
//
//                }


