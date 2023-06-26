package edu.ucne.ticketsproxmovil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import  edu.ucne.ticketsproxmovil.ui.presentation.MainScreen
import  edu.ucne.ticketsproxmovil.ui.Navigation.Destinations
import  edu.ucne.ticketsproxmovil.ui.presentation.components.Drawer
import com.dev.leonardom.introuduccionajetpackcompose.presentation.components.TopBar

import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.ticketsproxmovil.ui.Navigation.NavigationHost
import edu.ucne.ticketsproxmovil.ui.theme.TicketsProxMovilTheme

import  edu.ucne.ticketsproxmovil.ui.theme.BLUE800

import  edu.ucne.ticketsproxmovil.ui.Navigation.PreviewNavigationScreen
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
fun MainScreen(
    darkMode: MutableState<Boolean>
) {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    )
    val scope = rememberCoroutineScope()
    val openDialog = remember { mutableStateOf(false) }

    val navigationItems = listOf(
        Destinations.Pantalla1,
        Destinations.Pantalla2,
        Destinations.Pantalla3,
        Destinations.HomeScreen,
        Destinations.EventScreen
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

//    Dialog(showDialog = openDialog.value, dismissDialog = { openDialog.value = false })
}