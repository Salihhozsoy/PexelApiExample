package com.example.pexelapi.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pexelapi.data.repository.PhotoRepository
import com.example.pexelapi.data.state.PhotoListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val photoRepository: PhotoRepository
):ViewModel() {

    private val _photoListState:MutableStateFlow<PhotoListState> = MutableStateFlow(PhotoListState.Idle)
    val photoListState:StateFlow<PhotoListState> =_photoListState

    fun getAllPhotos(query:String){
        viewModelScope.launch {
            kotlin.runCatching {
                _photoListState.value =PhotoListState.Loading
                val photos = photoRepository.getAllPhotos(query)
                if(photos.isEmpty()) _photoListState.value =PhotoListState.Empty
                else _photoListState.value =PhotoListState.Result(photos)
            }.onFailure {
                _photoListState.value =PhotoListState.Error
                it.printStackTrace()
            }
        }
    }
}