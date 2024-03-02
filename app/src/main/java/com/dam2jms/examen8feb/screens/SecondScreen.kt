package com.dam2jms.examen8feb.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dam2jms.examen8feb.models.ViewModelFirstScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(navController: NavHostController, mvvm: ViewModelFirstScreen, text:String?) {
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
        }
    ) { paddingValues ->
        secondScreenContent(modifier = Modifier.padding(paddingValues), navController, text)
    }
}


@Composable
fun secondScreenContent(modifier: Modifier, navController: NavHostController, text: String?) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text?: "Texto no disponible")
    }
}