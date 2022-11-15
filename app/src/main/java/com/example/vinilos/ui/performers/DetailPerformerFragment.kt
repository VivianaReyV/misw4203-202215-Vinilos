package com.example.vinilos.ui.performers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos.databinding.FragmentDetailPerformerBinding
import com.example.vinilos.ui.adapters.PerformersAdapter
import com.example.vinilos.viewmodels.PerformerViewModel

class DetailPerformerFragment: Fragment() {
    private var _binding: FragmentDetailPerformerBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: PerformerViewModel
    private var viewModelAdapter: PerformersAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        viewModel = ViewModelProvider(this, PerformerViewModel.Factory(activity.application)).get(
            PerformerViewModel::class.java)

        _binding = FragmentDetailPerformerBinding.inflate(inflater, container, false)
        viewModelAdapter = PerformersAdapter()
        val root: View = binding.root

        val selectedPerformerId = arguments?.getString("performerId")
        viewModel.performers.observe(activity) { performersRv ->
            for (performer in performersRv) {
                if (performer.performerId.toString() == selectedPerformerId) {
                    viewModelAdapter?.loadUrl(performer.image, binding.cover)
                    binding.txtPerformerName.text = performer.name
                    binding.txtDescription.text = performer.description
                    binding.mainContainer.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

        return root
    }
}