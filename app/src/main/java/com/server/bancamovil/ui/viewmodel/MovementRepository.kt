/**
 * Descripción del archivo:
 * Autor: Augusto Rojas
 * Fecha: 20/02/24
 */

package com.server.bancamovil.ui.viewmodel

import com.server.bancamovil.data.api.ApiClient
import com.server.bancamovil.data.response.AccountResponseItem
import com.server.bancamovil.data.response.MovementResponseItem
import com.server.bancamovil.utils.Resource

class MovementRepository {

    suspend fun retrieveMovement(): Resource<List<MovementResponseItem>> {
        return try {
            val apiService = ApiClient.getAPiService()
            val response = apiService.obtenerMovimientos()
            if (response.isSuccessful) {
                val responseBody = response.body()
                return Resource.Success(responseBody)
            } else {
                return Resource.Error("Error en la retrieveMovement: ${response.code()} ${response.message()}")            }

        } catch (e: Exception) {
            return Resource.Error(e.message ?: "Error desconocido en retrieveMovement")
        }
    }
}