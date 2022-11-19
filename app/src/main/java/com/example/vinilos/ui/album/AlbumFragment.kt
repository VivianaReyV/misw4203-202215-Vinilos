package com.example.vinilos.ui.album

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
import com.example.vinilos.databinding.FragmentAlbumBinding
import com.example.vinilos.ui.adapters.AlbumsAdapter
import com.example.vinilos.viewmodels.AlbumViewModel
import com.example.vinilos.R

class AlbumFragment : Fragment() {

   // This property is only valid between onCreateView and
    // onDestroyView.
    private var _binding: FragmentAlbumBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: AlbumViewModel
    private var viewModelAdapter: AlbumsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val albumViewModel =
            ViewModelProvider(this)[AlbumViewModel::class.java]

        _binding = FragmentAlbumBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = AlbumsAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.albumsRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
        binding.rollButton.setOnClickListener{clickButton(view)}
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_albums)
        viewModel = ViewModelProvider(this, AlbumViewModel.Factory(activity.application))[AlbumViewModel::class.java]
        viewModel.albums.observe(viewLifecycleOwner) { it ->
            it.apply {
                viewModelAdapter!!.albums = this.sortedByDescending { it.releaseDate }
                binding.progressBar.visibility = View.INVISIBLE
            }
        }
        viewModel.eventNetworkError.observe(viewLifecycleOwner) { isNetworkError ->
            if (isNetworkError) {
                onNetworkError()
                binding.progressBar.visibility = View.INVISIBLE
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(this.context, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

    private fun clickButton(view: View){
        try{
            Navigation.findNavController(view).navigate(R.id.nav_create_album)
        }catch(err: Exception){

        }
    }
}