package com.peartedio.lab5

import retrofit2.http.GET

interface APIInterface {

    @GET("posts")
    suspend fun getItems(): List<Item>

}