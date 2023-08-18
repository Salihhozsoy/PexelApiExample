package com.example.pexelapi.data.repository

import com.example.pexelapi.data.api.model.Photo


interface PhotoRepository {

    suspend fun getAllPhotos(query:String):List<Photo>
}