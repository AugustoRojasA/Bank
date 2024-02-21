/**
 * Descripción del archivo:
 * Autor: Augusto Rojas
 * Fecha: 19/02/24
 */

package com.server.bancamovil.domain

class GetAccountsUseCase(private val accountRepository: AccountRepository) {
    suspend operator fun invoke(): List<Account> {
        return accountRepository.obtenerCuentas()
    }
}