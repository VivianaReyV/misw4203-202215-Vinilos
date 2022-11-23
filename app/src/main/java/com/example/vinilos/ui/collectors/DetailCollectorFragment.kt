package com.example.vinilos.ui.collectors

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos.databinding.FragmentDetailCollectorBinding
import com.example.vinilos.ui.adapters.CollectorsAdapter
import com.example.vinilos.viewmodels.CollectorViewModel

class DetailCollectorFragment: Fragment() {
    private var _binding: FragmentDetailCollectorBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CollectorViewModel
    private var viewModelAdapter: CollectorsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        viewModel = ViewModelProvider(this, CollectorViewModel.Factory(activity.application))[CollectorViewModel::class.java]

        _binding = FragmentDetailCollectorBinding.inflate(inflater, container, false)
        viewModelAdapter = CollectorsAdapter()
        val root: View = binding.root

        val selectedCollectorId = arguments?.getString("collectorId")
        viewModel.collectors.observe(activity) { collectorsRv ->
            for (collector in collectorsRv) {
                if (collector.collectorId.toString() == selectedCollectorId) {
                    binding.txtCollectorName.text = collector.name
                    binding.txtPhone.text = collector.telephone
                    binding.txtEmail.text = collector.email
                    binding.mainContainer.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE

                    binding.performersContainer.removeAllViews()
                    if(collector.favoritePerformers.isNotEmpty()){
                        binding.lblFavoritePerformers.visibility = View.VISIBLE
                    }
                    for (favPerformer in collector.favoritePerformers){
                        createPerformerListView(favPerformer.name)
                    }
                }
            }
        }
        return root
    }

    fun createPerformerListView(name: String){
        val context = this.context
        // TextView Performer
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(0, 5, 0, 5)
        val tvPerformer = TextView(context)
        tvPerformer.layoutParams = layoutParams
        tvPerformer.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18F)
        tvPerformer.text = name

        binding.performersContainer.addView(tvPerformer)
    }
}