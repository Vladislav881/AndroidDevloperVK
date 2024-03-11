package com.example.androiddevlopervk.view.recycler_view.view_holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddevlopervk.R
import com.example.androiddevlopervk.view.recycler_view.CatalogAdapter
import com.example.androiddevlopervk.view.recycler_view.OnClickListener
import com.example.androiddevlopervk.view_model.MainViewModel

class CatalogViewHolder(
    private val mainViewModel: MainViewModel,
    itemView: View,
    private val listener: OnClickListener
):
    RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private val imageList: RecyclerView = itemView.findViewById(R.id.catalog_element_image)
    private val title: TextView = itemView.findViewById(R.id.catalog_element_title)
    private val price: TextView = itemView.findViewById(R.id.catalog_element_price)

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val position = adapterPosition
        if (position != RecyclerView.NO_POSITION) {
            listener.onClickItem(position)
        }
    }

    fun bind(title_catalog: String, prise_catalog: String, imageUrls: List<String>, listener: OnClickListener) {

        title.text = title_catalog
        price.text = prise_catalog

        val layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        imageList.layoutManager = layoutManager

        val adapter = CatalogAdapter(mainViewModel, imageUrls, listener)
        imageList.adapter = adapter

    }

}