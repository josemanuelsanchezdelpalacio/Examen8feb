package com.dam2jms.examen8feb.models

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.dam2jms.examen8feb.states.MainUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ViewModelFirstScreen : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    fun onChange(numeroUsuarioUi: String) {
        _uiState.update { currentState ->
            currentState.copy(numeroUsuario = numeroUsuarioUi)
        }
    }

    fun onChangeContraseña(contraseñaUi: String) {
        _uiState.update { currentState ->
            currentState.copy(contraseña = contraseñaUi)
        }
    }

    fun aumentarContador(contadorUi: Int) {
        _uiState.update { currentState ->
            currentState.copy(contador = contadorUi.inc())
        }
    }

    fun comprobarNumeros(context: Context) {
        val numeroUsuario = _uiState.value.numeroUsuario

        // Verificar si numeroUsuario es un número
        if (numeroUsuario.toDoubleOrNull() != null) {
            Toast.makeText(context, "El campo tiene un número", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "El campo no tiene un número", Toast.LENGTH_SHORT).show()
        }
    }


    fun comprobarContraseña(context: Context) {
        if (_uiState.value.contraseña == "admin") {
            _uiState.value.numIntentos = 0
            _uiState.value.resultado = "La contraseña ${_uiState.value.contraseña} es correcta\n El numero de intentos ha sido: ${_uiState.value.numIntentos}"
        } else if(_uiState.value.contraseña != "admin"){
            _uiState.update { currentState ->
                currentState.copy(numIntentos = currentState.numIntentos.inc())
            }
        }
    }
}

