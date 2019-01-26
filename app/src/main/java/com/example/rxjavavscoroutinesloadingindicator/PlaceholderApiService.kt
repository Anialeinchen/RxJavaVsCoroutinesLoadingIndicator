package com.example.rxjavavscoroutinesloadingindicator

import retrofit2.Call
import retrofit2.http.GET

interface PlaceholderApiService {
    @GET("/users")
    fun fetchPlaceholderUsers(): Call<List<User>>
}