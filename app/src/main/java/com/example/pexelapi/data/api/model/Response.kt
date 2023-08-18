package com.example.pexelapi.data.api.model

data class Response<T>(
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val photos: T,
    val total_results: Int
)