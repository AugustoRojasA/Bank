/**
 * Descripción del archivo:
 * Autor: Augusto Rojas
 * Fecha: 20/02/24
 */

package com.server.bancamovil.ui.viewmodel

import com.server.bancamovil.data.api.ApiClient
import com.server.bancamovil.data.response.AccountResponseItem
import com.server.bancamovil.utils.Resource

class AccountRepository {

    suspend fun retrieveAccount(): Resource<List<AccountResponseItem>> {
        return try {
            val apiService = ApiClient.getAPiService()
            val response = apiService.obtenerCuentas()
            if (response.isSuccessful) {
                val responseBody = response.body()
                return Resource.Success(responseBody)
            } else {
                return Resource.Error("Error en la OrderDetailRepository: ${response.code()} ${response.message()}")            }

        } catch (e: Exception) {
            return Resource.Error(e.message ?: "Error desconocido")
        }
    }
}