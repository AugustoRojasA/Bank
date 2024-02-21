/**
 * Descripción del archivo:
 * Autor: Augusto Rojas
 * Fecha: 19/02/24
 */

package com.server.bancamovil.domain

interface AccountRepository {
    suspend fun obtenerCuentas(): List<Account>
}
