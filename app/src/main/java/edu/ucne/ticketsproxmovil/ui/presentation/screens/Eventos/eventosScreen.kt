package edu.ucne.ticketsproxmovil.ui.presentation.screens.Eventos


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import edu.ucne.ticketsproxmovil.data.remote.dto.EventoDTO
import coil.size.Scale
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp


@Composable
fun EventList(event: List<EventoDTO>) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
//        color = MaterialTheme.colors.background
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(it)) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
//                horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
            ) {
                items(event) { event ->
                    Box(modifier = Modifier
                        .fillMaxWidth(0.90f)
                        .padding(16.dp)){
                        EventItem(event, Modifier = Modifier)

                    }

                }
            }
        }

    }
}

@Composable
fun EventItem(event: EventoDTO, Modifier: Modifier) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(top = 2.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = rememberImagePainter(data = event.image),
                contentDescription = event.descripcion,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth().padding(top = 5.dp)
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = event.nombreEvento,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = event.descripcion,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Button(
                    onClick = { /* Acción al hacer clic en el botón */ },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(text = "Comprar tickets")
                }
            }
        }
    }
}


//@Composable
//fun EventItem(event: EventoDTO, Modifier: Modifier) {
//
//    Card(
//        modifier = Modifier.fillMaxWidth(),
//        shape = RoundedCornerShape(15.dp),
//        elevation = 5.dp
//    ) {
//
//        Box(
//            modifier = Modifier
//                .height(200.dp)
//                .fillMaxSize()
//        ) {
//
//            val painter = rememberImagePainter(
//                data = event.image,
//                builder = {
//                    scale(Scale.FILL)
//                }
//            )
//
//            Image(
//                painter = painter,
//                contentDescription = event.descripcion
//            )
//
//            Spacer(modifier = Modifier.width(16.dp))
//
//            Column {
//                Text(text = event.nombreEvento, style = MaterialTheme.typography.h6)
//                Text(text = event.descripcion, style = MaterialTheme.typography.body2)
//                Button(onClick = { }) {
//                    Text(text = "Comprar tickets")
//                }
//            }
//
//
//        }
//
//    }
//}
    //            Column {
//                            Text(text = event.nombreEvento, style = TextStyle(color = Color.White, fontSize = 16.sp))
//            Text(text = event.descripcion, style = MaterialTheme.typography.body2)
//            Button(onClick = {  }) {
//                Text(text = "Comprar tickets")
//            }
//            }

//    Column {
//        Text(text = event.nombreEvento, style = MaterialTheme.typography.h6)
//        Text(text = event.descripcion, style = MaterialTheme.typography.body2)
//        Button(onClick = {  }) {
//            Text(text = "Comprar tickets")}
//    }
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(2.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//    ) {
//        val painter = rememberImagePainter(
//            data = event.image,
//            builder = {
//                scale(Scale.FILL)
//            }
//        )
//
//        Image(
//            painter = painter,
//            contentDescription = event.nombreEvento,
//            modifier = Modifier
//                .size(150.dp)
//                .fillMaxWidth(),
//            contentScale = ContentScale.Crop
//        )
//
//        Spacer(modifier = Modifier.width(16.dp))
//        Column {
//            Text(text = event.nombreEvento, style = MaterialTheme.typography.h6)
//            Text(text = event.descripcion, style = MaterialTheme.typography.body2)
//            Button(onClick = {  }) {
//                Text(text = "Comprar tickets")
//            }
//        }
//    }


@Composable
fun PreviewEventList(
    viewModel: EventoViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    EventList(event = uiState.evento)
}

