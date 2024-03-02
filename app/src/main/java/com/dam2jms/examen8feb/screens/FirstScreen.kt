package com.dam2jms.examen8feb.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dam2jms.examen8feb.models.ViewModelFirstScreen
import com.dam2jms.examen8feb.navigation.AppScreens
import com.dam2jms.examen8feb.states.MainUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstScreen(navController: NavHostController, mvvm: ViewModelFirstScreen) {

    val uiState by mvvm.uiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "CONTADOR: ${uiState.contador}") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        mvvm.aumentarContador(uiState.contador)
                    }) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = "Contador")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {navController.navigate(route = AppScreens.SecondScreen.route + "/${mvvm.uiState.value.numeroUsuario}") },
                containerColor = Color(0xFFAC53F7),
                elevation = FloatingActionButtonDefaults.elevation()
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        }
    ) { paddingValues ->
        firstScreenContent(modifier = Modifier.padding(paddingValues), mvvm, navController, uiState)
    }
}


@Composable
fun firstScreenContent(modifier: Modifier = Modifier,mvvm: ViewModelFirstScreen, navController: NavHostController, uiState: MainUiState) {

    val context = LocalContext.current
    var mostrarAlertDialog by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(
                value = uiState.numeroUsuario,
                onValueChange = { mvvm.onChange(it) },
                label = { Text(text = "Escribe un numero") },
                modifier = Modifier
                    .padding(8.dp)
            )
            Button(onClick = {
                mvvm.comprobarNumeros(context)
            }) {
                Text(text = "Comprobar numeros")
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(value = uiState.contraseña,
                onValueChange = { mvvm.onChangeContraseña(it) },
                label = { Text(text = "Introduce contraseña") },
                modifier = Modifier
                    .padding(8.dp)
            )

            if (mostrarAlertDialog) {
                AlertDialog(text = {
                    uiState.resultado.let { Text(text = it) }
                }, onDismissRequest = { mostrarAlertDialog = false }, confirmButton = {
                    TextButton(onClick = { mostrarAlertDialog = false }) { Text(text = "OK") }
                })
            }

            Button(onClick = {
                mvvm.comprobarContraseña(context)
                mostrarAlertDialog = true
            }) {
                Text(text = "Comprobar contraseña")
            }
        }
    }

}
