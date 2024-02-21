/**
 * Descripción del archivo:
 * Autor: Augusto Rojas
 * Fecha: 20/02/24
 */

package com.server.bancamovil.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MovementViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovementViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovementViewModel(movementRepository = MovementRepository()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
