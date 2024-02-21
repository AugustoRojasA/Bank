/**
 * Descripción del archivo:
 * Autor: Augusto Rojas
 * Fecha: 19/02/24
 */

package com.server.bancamovil.ui.movement

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.server.bancamovil.R
import com.server.bancamovil.data.response.AccountResponseItem
import com.server.bancamovil.data.response.MovementResponseItem
import com.server.bancamovil.databinding.ItemAccountBinding
import com.server.bancamovil.databinding.ItemMovementBinding
import com.server.bancamovil.domain.Account

class MovementAdapter : RecyclerView.Adapter<MovementAdapter.AccountViewHolder>() {

    private var movements: List<MovementResponseItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovementBinding.inflate(inflater, parent, false)
        return AccountViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val currentAccount = movements[position]
        holder.bind(currentAccount)
    }

    override fun getItemCount() = movements.size

    fun actualizarMovimientos(movements: List<MovementResponseItem>) {
        this.movements = movements
        notifyDataSetChanged()
    }

    inner class AccountViewHolder(private val binding: ItemMovementBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movement: MovementResponseItem) {
            binding.tvIdMovement.text = movement.id.toString()
            if(movement.monto.toString().contains("Pago",true)){
                binding.tvAmount.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.red
                    )
                )
            }
            binding.tvAmount.text = movement.monto.toString()
            binding.tvDate.text = movement.fecha
            binding.tvDescription.text = movement.descripcion
        }

        init {
            binding.root.setOnClickListener {
                // Acción que deseas realizar al hacer clic en el elemento
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    // Obtener el elemento en la posición actual
                    val movementItem = movements[position]
                    println("click aqui funciona bien")
                    // Realizar acciones basadas en el elemento seleccionado
                    // Por ejemplo, puedes abrir un nuevo fragmento o actividad con más detalles sobre el elemento seleccionado
                }
            }
        }
    }
}

