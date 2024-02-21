package com.server.bancamovil.data.response

data class AccountResponseItem(
      val balance: Int = 0,
      val currency: String = "",
      val id: Int = 0,
      val currencySymbol: String = ""
)