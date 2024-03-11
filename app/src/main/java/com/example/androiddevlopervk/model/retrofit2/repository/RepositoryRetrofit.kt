package com.example.androiddevlopervk.model.retrofit2.repository

import android.util.Log
import com.example.androiddevlopervk.model.retrofit2.model_retrofit.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ProductRepository {

    val retrofit = ClientRetrofit.apiService
    private var skip = 0
    private val limit = 20

    suspend fun getProducts(): Pair<List<Product>, List<List<String>>> {
        try {
            val response = withContext(Dispatchers.IO) {
                retrofit.getAllProducts(skip, limit).execute()
            }

            skip += limit

            if (response.code() != 200) {
                Log.e("ERROR CONNECTION WITH SERVICE", response.code().toString())
                return Pair(emptyList(), emptyList())
            }

            val newImageList = mutableListOf<List<String>>()
            response.body()!!.products.forEach { product ->
                val massUrl = mutableListOf<String>()
                massUrl.add(product.thumbnail)
                product.images.forEach { url ->
                    massUrl.add(url)
                }
                newImageList.add(massUrl)
            }

            return Pair(response.body()!!.products, newImageList)
        } catch (e: Exception) {
            Log.e("ERROR DOWNLOAD", e.message.toString())
            return Pair(emptyList(), emptyList())
        }
    }
}