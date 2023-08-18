package com.example.pexelapi.data.repository

import com.example.pexelapi.data.api.model.Photo
import com.example.pexelapi.data.api.service.PhotoService
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(private val photoService: PhotoService) : PhotoRepository {

    override suspend fun getAllPhotos(query:String):List<Photo> {
        return photoService.getAllPhotos(query).photos
    }
}