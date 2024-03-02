package com.dam2jms.examen8feb.states

data class MainUiState(
    val numeroUsuario: String = "",
    var contraseña: String = "",
    var resultado: String = "",
    var numIntentos: Int = 0,
    var contador: Int = 1,
    val listaNumeros: List<Int> = listOf(1,2,3,4,5,6,7,8,9,0)
)