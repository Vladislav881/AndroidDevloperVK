package com.example.androiddevlopervk.view.recycler_view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.androiddevlopervk.R
import com.example.androiddevlopervk.model.retrofit2.model_retrofit.Product
import com.example.androiddevlopervk.view.recycler_view.view_holder.CatalogViewHolder
import com.example.androiddevlopervk.view.recycler_view.view_holder.ImageProductHolder
import com.example.androiddevlopervk.view_model.MainViewModel

class CatalogAdapter(
    private val mainViewModel: MainViewModel,
    private var items: List<Any>,
    private val listener: OnClickListener? = null
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ItemType {
        CATALOG_PRODUCT,
        CATALOG_ITEM_IMAGE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            ItemType.CATALOG_PRODUCT.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.element_catalog_recyclerview, parent, false)
                CatalogViewHolder(mainViewModel, view, listener!!)
            }
            ItemType.CATALOG_ITEM_IMAGE.ordinal -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.element_image_catalog_recyclerview, parent, false)
                ImageProductHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when(holder) {
            is CatalogViewHolder -> {
                item as Product

                mainViewModel.imageList.observe(holder.itemView.context as LifecycleOwner) { image ->
                    image?.let {
                        holder.bind(item.title, item.price.toString(), mainViewModel.imageList.value!![position], listener!!)
                    }
                }

            }
            is ImageProductHolder -> {
                item as String
                holder.bind(item)
            }
            else -> throw IllegalArgumentException("Invalid ViewHolder type")
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position] is Product) {
            ItemType.CATALOG_PRODUCT.ordinal
        } else {
            ItemType.CATALOG_ITEM_IMAGE.ordinal
        }
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: List<Product>) {
        items = newData
        notifyDataSetChanged()
    }
}

