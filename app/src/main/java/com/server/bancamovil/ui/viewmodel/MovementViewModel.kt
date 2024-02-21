/**
 * Descripción del archivo:
 * Autor: Augusto Rojas
 * Fecha: 20/02/24
 */

package com.server.bancamovil.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.server.bancamovil.data.response.AccountResponseItem
import com.server.bancamovil.data.response.MovementResponseItem
import com.server.bancamovil.utils.Resource
import kotlinx.coroutines.launch

class MovementViewModel(private val movementRepository: MovementRepository) : ViewModel() {

    private val _movementLiveData = MutableLiveData<Resource<List<MovementResponseItem>>>()
    val movementLiveData: LiveData<Resource<List<MovementResponseItem>>> get() = _movementLiveData


    fun obtenerMovimientos() {
        viewModelScope.launch {
            try {
                val movements = movementRepository.retrieveMovement()
                _movementLiveData.postValue(movements)
            } catch (e: Exception) {
                // Manejar errores
            }
        }
    }
}
