package edu.ucne.ticketsproxmovil.ui.presentation.screens.Login

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import edu.ucne.ticketsproxmovil.data.remote.dto.LoginDto


@Composable
fun LoginScreen(
    viewModelLogin: LoginViewModel = hiltViewModel(),
    onLoginResult: (Boolean, String, String) -> Unit,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

     fun IniciarSesion() {
        println("Email: $email")
        println("Password: $password")
        viewModelLogin.Login(LoginDto(email = email, password = password))
    }

    LaunchedEffect(Unit) {
        // Observar los cambios en el resultado del inicio de sesión
        viewModelLogin.loginResult.collect { loginResult ->
            loginResult?.let { (success, message, nombre) ->
                // Llamar a la función de callback con los datos del inicio de sesión
                onLoginResult(success, message, nombre)
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Filled.Person,
            contentDescription = "Login",
            modifier = Modifier.width(20.dp).height(20.dp)
        )
        Text(text = "AppTicketsProx!", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Text(text = "Login!", fontSize = 12.sp)
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.padding(16.dp),
            singleLine = true,
            textStyle = MaterialTheme.typography.body1,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.primary,
                unfocusedBorderColor = MaterialTheme.colors.onSurface
            )
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.padding(16.dp),
            singleLine = true,
            textStyle = MaterialTheme.typography.body1,
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.primary,
                unfocusedBorderColor = MaterialTheme.colors.onSurface
            )
        )

        Button(
            onClick = { IniciarSesion() },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Log In", fontSize = 18.sp)
        }
    }
}


