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
import com.example.vinilos.R

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

        if (binding.nombreColeccionista.text.toString() == "")
        {
            formError(getString(R.string.alert_name_empty))
            return
        }
        if (binding.telefono.text.toString() == "")
        {
            formError(getString(R.string.alert_phone_empty))
            return
        }
        if (binding.email.text.toString() == "")
        {
            formError(getString(R.string.alert_email_empty))
            return
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(binding.email.text.toString()).matches())
        {
            formError(getString(R.string.alert_email_format_error))
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
            Toast.makeText(activity, getString(R.string.alert_create_collector_success), Toast.LENGTH_LONG).show()
            viewModel.onSuccessCollectorCreatedShown()
            activity?.onBackPressed()
        }
    }

    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, getString(R.string.alert_create_collector_fail), Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

    private fun formError(error: String){
        Toast.makeText(activity, error, Toast.LENGTH_LONG).show()
    }
}