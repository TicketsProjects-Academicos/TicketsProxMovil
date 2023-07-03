package edu.ucne.ticketsproxmovil.ui.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.twotone.AccountBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import  edu.ucne.ticketsproxmovil.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(

) {
    var expanded by remember {
        mutableStateOf(false)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "¡Bienvenido a AppticketsProx!",
            style = TextStyle(fontSize = 24.sp),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = { expanded = !expanded },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(text = if (expanded) "Ocultar detalles" else "Mostrar detalles")
        }

        if (expanded) {
            Text(
                text = "Aquí encontrarás una amplia selección de tickets para los mejores eventos. ¡Disfruta de una experiencia única comprando tus entradas con nosotros!",
                style = TextStyle(fontSize = 16.sp),
                textAlign = TextAlign.Center
            )
        }
    }

}