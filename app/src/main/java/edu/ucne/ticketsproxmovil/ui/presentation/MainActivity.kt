package edu.ucne.ticketsproxmovil.ui.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import  edu.ucne.ticketsproxmovil.ui.Navigation.Destinations.*
import  edu.ucne.ticketsproxmovil.ui.Navigation.NavigationHost
import com.dev.leonardom.introuduccionajetpackcompose.presentation.components.BottomNavigationBar
import com.dev.leonardom.introuduccionajetpackcompose.presentation.components.Dialog
import  edu.ucne.ticketsproxmovil.ui.presentation.components.Drawer
import com.dev.leonardom.introuduccionajetpackcompose.presentation.components.TopBar
import  edu.ucne.ticketsproxmovil.ui.theme.TicketsProxMovilTheme
import  edu.ucne.ticketsproxmovil.ui.theme.BLUE800
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

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
        HomeScreen,
        EventScreen,
//        Pantalla1,
//        Pantalla2,
//        Pantalla3,



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









