package com.example.vinilos.ui.collectors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.FragmentCollectorsBinding
import com.example.vinilos.ui.adapters.CollectorsAdapter
import com.example.vinilos.viewmodels.CollectorPerformerViewModel

class CollectorFragment : Fragment() {

    private var _binding: FragmentCollectorsBinding? = null

    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: CollectorPerformerViewModel
    private var viewModelAdapter: CollectorsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val collectorViewModel =
            ViewModelProvider(this)[CollectorPerformerViewModel::class.java]

        _binding = FragmentCollectorsBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = CollectorsAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.collectorsRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
        binding.rollButton.setOnClickListener{clickButton(view)}
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_performers)
        viewModel = ViewModelProvider(this, CollectorPerformerViewModel.Factory(activity.application))[CollectorPerformerViewModel::class.java]
        viewModel.collectors.observe(viewLifecycleOwner) { it ->
            it.apply {
                viewModelAdapter!!.collectors = this.sortedByDescending { it.name }
                binding.progressBar.visibility = View.INVISIBLE
            }
        }
        viewModel.eventNetworkError.observe(viewLifecycleOwner) { isNetworkError ->
            if (isNetworkError) onNetworkError()
        }
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun clickButton(view: View){
        try{
            Navigation.findNavController(view).navigate(R.id.nav_create_collector)
        }catch(_: Exception){ }
    }
}