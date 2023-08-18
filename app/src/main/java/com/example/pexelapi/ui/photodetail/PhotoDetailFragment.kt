package com.example.pexelapi.ui.photodetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.example.pexelapi.R
import com.example.pexelapi.data.api.model.Photo
import com.example.pexelapi.databinding.FragmentPhotoDetailBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class PhotoDetailFragment( val photo: Photo) : BottomSheetDialogFragment() {

    lateinit var binding: FragmentPhotoDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotoDetailBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivPhoto.load(photo.src.portrait)
        binding.tvPhotographer.text = photo.photographer
    }

}