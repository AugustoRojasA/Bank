/**
 * Descripción del archivo:
 * Autor: Augusto Rojas
 * Fecha: 19/02/24
 */

package com.server.bancamovil.ui.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.server.bancamovil.data.response.AccountResponseItem
import com.server.bancamovil.databinding.ItemAccountBinding
import com.server.bancamovil.domain.Account

class AccountsAdapter( private val onClickListener: OnClickListener) : RecyclerView.Adapter<AccountsAdapter.AccountViewHolder>() {

    private var accounts: List<AccountResponseItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAccountBinding.inflate(inflater, parent, false)
        return AccountViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val currentAccount = accounts[position]
        holder.bind(currentAccount)
    }

    override fun getItemCount() = accounts.size

    fun actualizarCuentas(accounts: List<AccountResponseItem>) {
        this.accounts = accounts
        notifyDataSetChanged()
    }

    inner class AccountViewHolder(private val binding: ItemAccountBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(account: AccountResponseItem) {
            binding.id.text = account.id.toString()
            binding.currency.text = account.currency
            binding.amount.text = account.balance.toString()
        }

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val accountItem = accounts[position]
                    onClickListener.onClick(position,accountItem)
                    println("click aqui funciona bien: $accountItem")
                    // Realiza la acción deseada al hacer clic en el elemento
                    // Por ejemplo, puedes enviar el objeto accountItem a través de una interfaz para manejar el clic en la actividad o fragmento principal
                }
            }
        }
    }
}

interface OnClickListener {
    fun onClick(position: Int, account: AccountResponseItem)
}