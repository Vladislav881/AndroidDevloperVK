package com.example.androiddevlopervk.view.recycler_view.view_holder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androiddevlopervk.R
import com.example.androiddevlopervk.view.recycler_view.OnClickListener


class ImageProductHolder(itemView: View, private val listener: OnClickListener? = null):
    RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private val image: ImageView = itemView.findViewById(R.id.image_product_catalog)


    fun bind(url: String) {
        Glide.with(itemView)
            .load(url)
            .into(image)
    }

    override fun onClick(view: View) {
        val position = adapterPosition
        if (position != RecyclerView.NO_POSITION) {
            listener!!.onClickItem(position)
        }
    }
}