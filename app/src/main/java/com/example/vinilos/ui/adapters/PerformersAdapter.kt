package com.example.vinilos.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.vinilos.R
import com.example.vinilos.databinding.PerformerItemBinding
import com.example.vinilos.models.Performer

class PerformersAdapter : RecyclerView.Adapter<PerformersAdapter.PerformerViewHolder>(){

    var performers :List<Performer> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerformerViewHolder {
        val withDataBinding: PerformerItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            PerformerViewHolder.LAYOUT,
            parent,
            false)
        return PerformerViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: PerformerViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.performer = performers[position]
        }
        loadUrl(performers[position].image,holder.viewDataBinding.cover)
        holder.viewDataBinding.root.setOnClickListener {
            val bundle = bundleOf("performerId" to performers[position].performerId.toString().trim())
            Navigation.findNavController(holder.itemView).navigate(R.id.nav_detail_performer, bundle);
        }
    }

    override fun getItemCount(): Int {
        return performers.size
    }

    class PerformerViewHolder(val viewDataBinding: PerformerItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.performer_item
        }
    }

    fun loadUrl(url: String, imgView : ImageView) {
        try {
            Glide.with(imgView).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.artist_cover).into(imgView)
        }catch (e : Exception){

        }

    }

}