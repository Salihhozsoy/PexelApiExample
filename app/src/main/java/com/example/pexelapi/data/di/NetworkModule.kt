package com.example.pexelapi.data.di

import com.example.pexelapi.Constant
import com.example.pexelapi.data.api.service.PhotoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofit() :Retrofit =Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constant.BASE_API_URL).build()

    @Provides
    fun provideService(retrofit: Retrofit) : PhotoService =retrofit.create(PhotoService::class.java)
}