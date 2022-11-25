package com.example.vinilos.ui.collectors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos.databinding.FragmentCreateCollectorBinding
import com.example.vinilos.models.Collector
import com.example.vinilos.viewmodels.CollectorViewModel


class  CreateCollectorFragment: Fragment() {

    private var _binding: FragmentCreateCollectorBinding ? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: CollectorViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this)[CollectorViewModel::class.java]

        _binding = FragmentCreateCollectorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.createCollectorButton.setOnClickListener{createCollector(view)}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.eventCreateCollectorSuccess.observe(viewLifecycleOwner) { successCollectorCreated ->
            if (successCollectorCreated) onSuccessCollectorCreated()
        }
        viewModel.eventNetworkError.observe(viewLifecycleOwner) { isNetworkError ->
            if (isNetworkError) onNetworkError()
        }
    }

    private fun createCollector(view: View){

        if (binding.nombreColeccionista.text.toString() == null || binding.nombreColeccionista.text.toString() == "")
        {
            formError("El campo nombre no puede estar vacío")
            return
        }
        if (binding.telefono.text.toString() == null || binding.telefono.text.toString() == "")
        {
            formError("El campo teléfono no puede estar vacío")
            return
        }
        if (binding.email.text.toString() == null || binding.email.text.toString() == "")
        {
            formError("El campo Correo electrónico no puede estar vacío")
            return
        }
        val collectorToCreate = Collector(null,
            binding.nombreColeccionista.text.toString(),
            binding.email.text.toString(),
            binding.telefono.text.toString()
        )
        viewModel.createCollector(collectorToCreate)

    }

    private fun onSuccessCollectorCreated(){
        if(!viewModel.isCreateCollectorSuccessShown.value!!) {
            Toast.makeText(activity, "Coleccionista creado exitosamente", Toast.LENGTH_LONG).show()
            viewModel.onSuccessCollectorCreatedShown()
            activity?.onBackPressed()
        }
    }

    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Error al crear coleccionista", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

    private fun formError(error: String){
        Toast.makeText(activity, error, Toast.LENGTH_LONG).show()
    }
}