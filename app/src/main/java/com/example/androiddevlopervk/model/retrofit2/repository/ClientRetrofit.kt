package com.example.androiddevlopervk.model.retrofit2.repository

import com.example.androiddevlopervk.model.retrofit2.api.ApiRetrofit
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClientRetrofit {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiRetrofit = retrofit.create(ApiRetrofit::class.java)
}