package com.example.vinilos.ui.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos.databinding.FragmentDetailAlbumBinding
import com.example.vinilos.viewmodels.AlbumViewModel
import com.example.vinilos.R

class DetailAlbumFragment: Fragment() {
    private var _binding: FragmentDetailAlbumBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AlbumViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this).get(AlbumViewModel::class.java)

        _binding = FragmentDetailAlbumBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.textView6.text = arguments?.getString("albumId");

        return root
    }

}