package edu.ucne.ticketsproxmovil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import  edu.ucne.ticketsproxmovil.ui.Navigation.Destinations
import  edu.ucne.ticketsproxmovil.ui.presentation.components.Drawer
import com.dev.leonardom.introuduccionajetpackcompose.presentation.components.TopBar

import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.ticketsproxmovil.ui.Navigation.NavigationHost
import edu.ucne.ticketsproxmovil.ui.theme.TicketsProxMovilTheme

import  edu.ucne.ticketsproxmovil.ui.theme.BLUE800

import edu.ucne.ticketsproxmovil.ui.presentation.screens.Login.LoginScreen
import edu.ucne.ticketsproxmovil.ui.presentation.screens.Login.LoginViewModel
import edu.ucne.ticketsproxmovil.ui.presentation.screens.home.HomeScreen
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            val darkMode = remember { mutableStateOf(false) }

            SideEffect {
                systemUiController.setStatusBarColor(
                    color = BLUE800
                )
            }

            TicketsProxMovilTheme(
                darkTheme = darkMode.value
            ){
                MainScreen(darkMode)
            }
        }
    }
}

@Composable
fun TicketsProxNav(darkMode: MutableState<Boolean>) {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    )
    val scope = rememberCoroutineScope()
    val openDialog = remember { mutableStateOf(false) }

    val navigationItems = listOf(
        Destinations.HomeScreen,
        Destinations.EventScreen,
    )

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                scope,
                scaffoldState,
                openDialog = { openDialog.value = true  },
                displaySnackBar = {
                    scope.launch {

                    }
                }
            )
        },
        drawerContent = { Drawer(scope, scaffoldState, navController, items = navigationItems) },
        drawerGesturesEnabled = true
    ){
        NavigationHost(navController, darkMode)
    }
}

@Composable
fun MainScreen(
    darkMode: MutableState<Boolean>,
    viwModel: LoginViewModel = hiltViewModel()

) {
    val loginResult by viwModel.loginResult.collectAsState()

    LaunchedEffect(loginResult) {
        loginResult?.let { result ->
            handleLoginResult(result.succes, result.message, result.nombre)
        }
    }

    Scaffold {
        if (loginResult?.succes == true) {
            TicketsProxNav(darkMode)
        } else {
            LoginScreen(onLoginResult = ::handleLoginResult)
        }
    }



}

fun handleLoginResult(success: Boolean, message: String, nombre: String) {
    if (success) {
        println("MainActivity")
        println("Sesión iniciada correctamente: $success")
        println("Message: $message")
        println("Nombre: $nombre")


    } else {
        println("Sesión fallida, credenciales incorrectas")
    }
}


