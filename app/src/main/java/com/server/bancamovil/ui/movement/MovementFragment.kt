package com.server.bancamovil.ui.movement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.server.bancamovil.R
import com.server.bancamovil.databinding.FragmentMovementBinding
import com.server.bancamovil.ui.viewmodel.AccountsAdapter
import com.server.bancamovil.ui.viewmodel.MovementViewModel
import com.server.bancamovil.ui.viewmodel.MovementViewModelFactory
import com.server.bancamovil.utils.LoadingDialog
import com.server.bancamovil.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MovementFragment : Fragment(), CoroutineScope {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var movementAdapter: MovementAdapter
    private var _binding: FragmentMovementBinding? = null
    private val viewModel: MovementViewModel by viewModels { MovementViewModelFactory() }
    private val loadingDialog: LoadingDialog = LoadingDialog()
    private val binding get() = _binding!!
    private var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onResume() {
        super.onResume()
        loadingDialog.show(activity?.supportFragmentManager!!, "")
        loadingDialog.isCancelable = false
        launch {
            delay(3000)
            viewModel.obtenerMovimientos()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar RecyclerView
        movementAdapter = MovementAdapter()
        binding.recyclerviewMovements.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movementAdapter
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMovementBinding.inflate(inflater, container, false)
        setupObservers()
        return binding.root
    }

    private fun setupObservers() {

        viewModel.movementLiveData.observe(viewLifecycleOwner, Observer { resource ->
            when (resource) {
                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    val movementsList = resource.data
                    if (movementsList != null) {
                        movementAdapter.actualizarMovimientos(movementsList)
                    }
                    loadingDialog.dismiss()
                }

                is Resource.Error -> {
                    loadingDialog.dismiss()
                }
            }
        })


    }
}