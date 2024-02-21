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
import com.server.bancamovil.utils.Resource
import kotlinx.coroutines.launch

class AccountViewModel(private val accountRepository: AccountRepository) : ViewModel() {

    private val _accountsLiveData = MutableLiveData<Resource<List<AccountResponseItem>>>()
    val accountsLiveData: LiveData<Resource<List<AccountResponseItem>>> get() = _accountsLiveData


    fun obtenerCuentas() {
        viewModelScope.launch {
            try {
                val accounts = accountRepository.retrieveAccount()
                _accountsLiveData.postValue(accounts)
            } catch (e: Exception) {
                // Manejar errores
            }
        }
    }
}

