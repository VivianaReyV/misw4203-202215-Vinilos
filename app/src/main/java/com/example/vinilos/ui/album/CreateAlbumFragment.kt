package com.example.vinilos.ui.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos.databinding.FragmentCreateAlbumBinding
import com.example.vinilos.models.Album
import com.example.vinilos.viewmodels.AlbumViewModel
import com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker


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
            ViewModelProvider(this).get(AlbumViewModel::class.java)

        _binding = FragmentCreateAlbumBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding.createButton.setOnClickListener{createAlbum(view)}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.eventCreateAlbumSuccess.observe(viewLifecycleOwner, Observer<Boolean> { successAlbumCreated ->
            if (successAlbumCreated) onSuccessAlbumCreated()
        })
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }

    fun createAlbum(view: View){

        if (binding.nombreAlbum.text.toString() == null || binding.nombreAlbum.text.toString() == "")
        {
            formError("El campo nombre no puede estar vacío")
            return
        }
        if (binding.cover.text.toString() == null || binding.cover.text.toString() == "")
        {
            formError("El campo Portada no puede estar vacío")
            return
        }
        if (binding.descripcion.text.toString() == null || binding.descripcion.text.toString() == "")
        {
            formError("El campo Descripción no puede estar vacío")
            return
        }
        if (binding.genero.text.toString() != " Clasical" &&
            binding.genero.text.toString() != "Salsa" &&
            binding.genero.text.toString() != "Rock" &&
            binding.genero.text.toString() != "Folk")
        {
            formError("El campo Género debe ser: Classical, Salsa, Rock o Folk")
            return
        }
        if (binding.discografia.text.toString() != " Sony Music" &&
            binding.discografia.text.toString() != "EMI" &&
            binding.discografia.text.toString() != "Discos Fuentes" &&
            binding.discografia.text.toString() != "Elektra" &&
            binding.discografia.text.toString() != "Fania Records")
        {
            formError("El campo Discografia debe ser: Sony Music, EMI, Discos Fuentes, Elektra o Fania Records")
            return
        }
        val regex = Regex("^\\d{4}-\\d{2}-\\d{2}$")
        if (!regex.matches(binding.fechaLanzamiento.text.toString()))
        {
            formError("El campo Fecha de lanzamiento no es válido")
            return
        }
        val albumToCreate = Album(null,
            binding.nombreAlbum.text.toString(),
            binding.cover.text.toString(),
            binding.fechaLanzamiento.text.toString(),
            binding.descripcion.text.toString(),
            binding.genero.text.toString(),
            binding.discografia.text.toString()
        )
        viewModel.createAlbumFromNetwork(albumToCreate)

    }

    private fun onSuccessAlbumCreated(){
        if(!viewModel.isCreateAlbumSuccessShown.value!!) {
            Toast.makeText(activity, "Albúm creado exitosamente", Toast.LENGTH_LONG).show()
            viewModel.onSuccessAlbumCreatedShown()
            activity?.onBackPressed()
        }
    }

    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Error al crear albúm", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

    private fun formError(error: String){
        Toast.makeText(activity, error, Toast.LENGTH_LONG).show()
    }
}