package com.example.lavanderiairaque

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // ⚠️ Troque pelo seu IP (resultado do ifconfig, en0 inet)
    private const val BASE_URL = "http://10.0.2.2/lavanderia_api/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
