package com.example.pexelapi.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pexelapi.R
import com.example.pexelapi.data.api.model.Photo
import com.example.pexelapi.databinding.PhotoListItemBinding

class PhotoAdapter(
    private val context: Context,
    private var photos: List<Photo>,
    val onClick: (photo: Photo) -> Unit
) : RecyclerView.Adapter<PhotoAdapter.CustomViewHolder>() {

    class CustomViewHolder(binding: PhotoListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val ivPhoto = binding.ivPhoto

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        val binding = PhotoListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val photo = photos[position]
        holder.ivPhoto.load(photo.src.portrait)

//        holder.itemView.setOnLongClickListener {
//        val animation = AnimationUtils.loadAnimation(context, R.anim.animation)
//        holder.ivPhoto.animation = animation
//        animation.start()
//            return@setOnLongClickListener true
//        }

        holder.itemView.setOnClickListener {
            onClick(photo)
        }
    }

    override fun getItemCount(): Int {
        return photos.size
    }
}