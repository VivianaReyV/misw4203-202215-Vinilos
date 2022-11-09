package com.example.vinilos.ui.performers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos.R
import com.example.vinilos.databinding.FragmentPerformersBinding
import com.example.vinilos.models.Performer
import com.example.vinilos.ui.adapters.PerformersAdapter
import com.example.vinilos.viewmodels.PerformerViewModel

class PerformerFragment : Fragment() {

    private var _binding: FragmentPerformersBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.

    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: PerformerViewModel
    private var viewModelAdapter: PerformersAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val performerViewModel =
            ViewModelProvider(this).get(PerformerViewModel::class.java)

        _binding = FragmentPerformersBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = PerformersAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.performersRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
        //binding.rollButton.setOnClickListener{clickButton(view)}
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_performers)
        viewModel = ViewModelProvider(this, PerformerViewModel.Factory(activity.application)).get(
            PerformerViewModel::class.java)
        viewModel.performers.observe(viewLifecycleOwner, Observer<List<Performer>> {
            it.apply {
                viewModelAdapter!!.performers = this.sortedByDescending { it.name }
                binding.progressBar.visibility = View.INVISIBLE
            }
        })
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
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
}