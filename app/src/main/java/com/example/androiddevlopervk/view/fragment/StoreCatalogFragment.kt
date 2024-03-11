package com.example.androiddevlopervk.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddevlopervk.R
import com.example.androiddevlopervk.view.recycler_view.CatalogAdapter
import com.example.androiddevlopervk.view.recycler_view.OnClickListener
import com.example.androiddevlopervk.view_model.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StoreCatalogFragment: Fragment(R.layout.store_catalog_fragment), OnClickListener {

    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: CatalogAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.catalog_recycler_view)
        val layoutManager = GridLayoutManager(view.context, 2)
        recyclerView.layoutManager = layoutManager

        adapter = CatalogAdapter(mainViewModel, emptyList(), this@StoreCatalogFragment)
        recyclerView.adapter = adapter

        if (mainViewModel.productsLiveData.value == null) {
            mainViewModel.viewModelScope.launch(Dispatchers.IO) {
                mainViewModel.fetchProducts()
            }
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount

                if (!mainViewModel.isLoading.value!! && lastVisibleItemPosition >= totalItemCount - 3) {
                    mainViewModel.fetchProducts()
                }
            }
        })

        mainViewModel.productsLiveData.observe(viewLifecycleOwner) { products ->
            products?.let {
                adapter.updateData(products)
            }
        }
    }

    override fun onClickItem(position: Int) {
        view?.let {
            val bundle = Bundle()
            bundle.putInt("position", position)
            Navigation.findNavController(it)
                .navigate(R.id.action_to_product_description, bundle)
        }
    }
}
