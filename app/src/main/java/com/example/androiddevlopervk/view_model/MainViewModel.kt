package com.example.androiddevlopervk.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevlopervk.model.retrofit2.model_retrofit.Product
import com.example.androiddevlopervk.model.retrofit2.repository.ProductRepository
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val repository = ProductRepository

    val productsLiveData: MutableLiveData<List<Product>> = MutableLiveData()
    val imageList: MutableLiveData<List<List<String>>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value = false }


    fun fetchProducts() {
        viewModelScope.launch {
            try {
                isLoading.value = true
                val (products, images) = repository.getProducts()

                val currentProducts = productsLiveData.value ?: emptyList()
                val updatedProducts = currentProducts + products
                productsLiveData.postValue(updatedProducts)

                val currentImages = imageList.value ?: emptyList()
                val updatedImages = currentImages + images
                imageList.postValue(updatedImages)
            } catch (e: Exception) {
                Log.e("ERROR", e.message.toString())
            } finally {
                isLoading.value = false
            }
        }
    }
}