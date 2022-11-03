package com.example.vinilos.ui.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos.databinding.FragmentAlbumBinding
import com.example.vinilos.databinding.FragmentCreateAlbumBinding
import com.example.vinilos.databinding.FragmentGalleryBinding
import com.example.vinilos.ui.adapters.AlbumsAdapter
import com.example.vinilos.viewmodels.AlbumViewModel
import com.example.vinilos.viewmodels.GalleryViewModel

class CreateAlbumFragment: Fragment() {

    private var _binding: FragmentCreateAlbumBinding ? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /*val createAlbumViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)*/

        _binding = FragmentCreateAlbumBinding.inflate(inflater, container, false)
        val root: View = binding.root

 //       val textView: TextView = binding.textView
        /*galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}