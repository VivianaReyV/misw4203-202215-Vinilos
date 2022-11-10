package com.example.vinilos.ui.album

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos.databinding.FragmentDetailAlbumBinding
import com.example.vinilos.models.Album
import com.example.vinilos.ui.adapters.AlbumsAdapter
import com.example.vinilos.viewmodels.AlbumViewModel
import kotlinx.android.synthetic.main.fragment_album.*

class DetailAlbumFragment: Fragment() {
    private var _binding: FragmentDetailAlbumBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AlbumViewModel
    private var viewModelAdapter: AlbumsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        viewModel = ViewModelProvider(this, AlbumViewModel.Factory(activity.application)).get(AlbumViewModel::class.java)

        _binding = FragmentDetailAlbumBinding.inflate(inflater, container, false)
        viewModelAdapter = AlbumsAdapter()
        val root: View = binding.root

        val selectedAlbumId = arguments?.getString("albumId")
        viewModel.albums.observe(activity) { albumsRv ->
            for (album in albumsRv) {
                if (album.albumId.toString() == selectedAlbumId) {
                    viewModelAdapter?.loadUrl(album.cover, binding.cover)
                    binding.txtAlbumName.text = album.name
                    binding.txtReleaseDate.text = "Fecha de lanzamiento (${(album.releaseDate).slice(0 until 10)})"
                    binding.txtDescription.text = album.description
                    binding.txtRecordLabel.text = album.recordLabel
                    binding.mainContainer.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

        return root
    }

}