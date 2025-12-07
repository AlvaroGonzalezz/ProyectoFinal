package com.example.proyectofinal

import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProducts(): List<Producto>

    @GET("api/")
    suspend fun getRandomUser(): RandomUserResponse
}