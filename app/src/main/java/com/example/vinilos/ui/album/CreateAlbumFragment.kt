package com.example.vinilos.ui.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos.databinding.FragmentCreateAlbumBinding
import com.example.vinilos.models.Album
import com.example.vinilos.viewmodels.AlbumViewModel
import com.example.vinilos.R

class CreateAlbumFragment: Fragment() {

    private var _binding: FragmentCreateAlbumBinding ? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: AlbumViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this)[AlbumViewModel::class.java]

        _binding = FragmentCreateAlbumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.createButton.setOnClickListener{createAlbum(view)}

        // Set genre items
        val genreItems: MutableList<String> = ArrayList()
        genreItems.add("Classical")
        genreItems.add("Folk")
        genreItems.add("Rock")
        genreItems.add("Salsa")

        context?.let { context ->
            ArrayAdapter(
                context,
                android.R.layout.simple_spinner_item,
                genreItems
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinnerGenre.adapter = adapter
            }
        }

        // Set recordlabel items
        val recordlabelItems: MutableList<String> = ArrayList()
        recordlabelItems.add("Discos Fuentes")
        recordlabelItems.add("Elektra")
        recordlabelItems.add("EMI")
        recordlabelItems.add("Fania Records")
        recordlabelItems.add("Sony Music")

        context?.let { context ->
            ArrayAdapter(
                context,
                android.R.layout.simple_spinner_item,
                recordlabelItems
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinnerRecordLabel.adapter = adapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.eventCreateAlbumSuccess.observe(viewLifecycleOwner) { successAlbumCreated ->
            if (successAlbumCreated) onSuccessAlbumCreated()
        }
        viewModel.eventNetworkError.observe(viewLifecycleOwner) { isNetworkError ->
            if (isNetworkError) onNetworkError()
        }
    }

    private fun createAlbum(view: View){
        if (binding.nombreAlbum.text.toString() == "")
        {
            formError(getString(R.string.alert_name_empty))
            return
        }
        val regex = Regex("^\\d{4}-\\d{2}-\\d{2}$")
        if (!regex.matches(binding.fechaLanzamiento.text.toString()))
        {
            formError(getString(R.string.alert_date_format_error))
            return
        }
        if (binding.descripcion.text.toString() == "")
        {
            formError(getString(R.string.alert_description_empty))
            return
        }
        if (binding.cover.text.toString() == "")
        {
            formError(getString(R.string.alert_cover_empty))
            return
        }
        val albumToCreate = Album(null,
            binding.nombreAlbum.text.toString(),
            binding.cover.text.toString(),
            binding.fechaLanzamiento.text.toString(),
            binding.descripcion.text.toString(),
            binding.spinnerGenre.selectedItem.toString(),
            binding.spinnerRecordLabel.selectedItem.toString()
        )
        viewModel.createAlbum(albumToCreate)

    }

    private fun onSuccessAlbumCreated(){
        if(!viewModel.isCreateAlbumSuccessShown.value!!) {
            Toast.makeText(activity, getString(R.string.alert_create_album_success), Toast.LENGTH_LONG).show()
            viewModel.onSuccessAlbumCreatedShown()
            activity?.onBackPressed()
        }
    }

    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, getString(R.string.alert_create_album_fail), Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

    private fun formError(error: String){
        Toast.makeText(activity, error, Toast.LENGTH_LONG).show()
    }
}