package com.server.bancamovil.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.server.bancamovil.R
import com.server.bancamovil.data.response.AccountResponseItem
import com.server.bancamovil.databinding.FragmentAccountsBinding
import com.server.bancamovil.ui.viewmodel.AccountViewModel
import com.server.bancamovil.ui.viewmodel.AccountViewModelFactory
import com.server.bancamovil.ui.viewmodel.AccountsAdapter
import com.server.bancamovil.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import com.server.bancamovil.ui.viewmodel.OnClickListener
import com.server.bancamovil.utils.LoadingDialog
import kotlinx.coroutines.delay


class AccountsFragment : Fragment(), CoroutineScope,OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val viewModel: AccountViewModel by viewModels { AccountViewModelFactory() }
    private lateinit var accountsAdapter: AccountsAdapter
    private val binding get() = _binding!!
    private var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    private var _binding: FragmentAccountsBinding? = null
    private val loadingDialog: LoadingDialog = LoadingDialog()

    override fun onResume() {
        super.onResume()
        refreshData()
    }

    private fun refreshData(){
        loadingDialog.show(activity?.supportFragmentManager!!, "")
        loadingDialog.isCancelable = false
        _binding?.packingLoading?.isVisible = true
        launch {
            delay(3000)
            viewModel.obtenerCuentas()
        }

        binding.swipeResfresh.isRefreshing = false
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar RecyclerView
        accountsAdapter = AccountsAdapter(this)
        binding.recyclerViewAccounts.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = accountsAdapter
        }

        binding.swipeResfresh.setOnRefreshListener {
            refreshData()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentAccountsBinding.inflate(inflater, container, false)
        setupObservers()
        return binding.root
    }

    private fun setupObservers() {

        viewModel.accountsLiveData.observe(viewLifecycleOwner, Observer { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _binding?.packingLoading?.isVisible = true
                }

                is Resource.Success -> {
                    val accountsList = resource.data
                    if (accountsList != null) {
                        accountsAdapter.actualizarCuentas(accountsList)
                    }
                    loadingDialog.dismiss()
                    _binding?.packingLoading?.isVisible = false
                }

                is Resource.Error -> {
                    _binding?.packingLoading?.isVisible = false
                    loadingDialog.dismiss()
                }
            }
        })


    }

    override fun onClick(position: Int, account: AccountResponseItem) {
        val data = Gson().toJson(account)
        val bundle = bundleOf("data" to data)
        binding.root.findNavController().navigate(R.id.movementFragment, bundle)
    }
}
