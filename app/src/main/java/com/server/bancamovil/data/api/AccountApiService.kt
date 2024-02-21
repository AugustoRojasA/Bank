/**
 * Descripción del archivo:
 * Autor: Augusto Rojas
 * Fecha: 19/02/24
 */

package com.server.bancamovil.data.api

import com.server.bancamovil.data.response.AccountResponse
import com.server.bancamovil.data.response.AccountResponseItem
import com.server.bancamovil.data.response.MovementResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface AccountApiService {

    @GET("/cuentas")
    suspend fun obtenerCuentas(): Response<List<AccountResponseItem>>

    @GET("/movimientos")
    suspend fun obtenerMovimientos(): Response<List<MovementResponseItem>>

}