package com.example.androiddevlopervk.model.retrofit2.api


import com.example.androiddevlopervk.model.retrofit2.model_retrofit.ProductsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRetrofit {
    @GET("products")
    fun getAllProducts(
        @Query("skip") skip: Int,
        @Query("limit") limit: Int
    ): Call<ProductsResponse>
}

