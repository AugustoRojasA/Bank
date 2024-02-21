/**
 * Descripción del archivo:
 * Autor: Augusto Rojas
 * Fecha: 19/02/24
 */

package com.server.bancamovil.domain

data class Account(
    val id: Int,
    val currency: String,
    val balance: Double
)