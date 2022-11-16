package com.example.vinilos.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vinilos.R
import com.example.vinilos.databinding.AlbumItemBinding
import com.example.vinilos.models.Album
//import com.example.vinilos.ui.album.AlbumFragmentDirections

// import com.example.vinilos.ui.AlbumFragmentDirections

class AlbumsAdapter : RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>(){

    var albums :List<Album> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val withDataBinding: AlbumItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumViewHolder.LAYOUT,
            parent,
            false)
        return AlbumViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.album = albums[position]
        }
        loadUrl(albums[position].cover,holder.viewDataBinding.cover)
        holder.viewDataBinding.root.setOnClickListener {
            val bundle = bundleOf("albumId" to albums[position].albumId.toString().trim())
            Navigation.findNavController(holder.itemView).navigate(R.id.nav_detail_album, bundle);
        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }


    class AlbumViewHolder(val viewDataBinding: AlbumItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.album_item
        }
    }

    fun loadUrl(url: String, imgView : ImageView) {
        try {
            Glide.with(imgView).load(url).error(R.drawable.coverdefault).into(imgView)
        }catch (e : Exception){
            //sendError(e,"loadUrl", url)
        }

    }

}