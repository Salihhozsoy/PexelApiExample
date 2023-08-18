package com.example.pexelapi.data.api.service

import com.example.pexelapi.Constant
import com.example.pexelapi.data.api.model.Photo
import com.example.pexelapi.data.api.model.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PhotoService {

    @Headers(
        "Authorization: ${Constant.API_KEY}"
    )

    @GET("v1/search")
    suspend fun getAllPhotos(@Query("query")queryText:String) : Response<List<Photo>>

}