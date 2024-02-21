package com.server.bancamovil.data.response

data class MovementResponseItem(
      val descripcion: String = "",
      val fecha: String = "",
      val id: Int = 0,
      val monto: Double = 0.00
)