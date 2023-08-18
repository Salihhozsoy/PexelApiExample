package com.example.pexelapi.data.state

import com.example.pexelapi.data.api.model.Photo

sealed class PhotoListState{
    object Idle:PhotoListState()
    object Loading:PhotoListState()
    object Empty:PhotoListState()
    class Result(val photos:List<Photo>):PhotoListState()
    object Error:PhotoListState()
}
