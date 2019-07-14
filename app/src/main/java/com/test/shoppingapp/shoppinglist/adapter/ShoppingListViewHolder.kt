package com.test.shoppingapp.shoppinglist.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.test.networkmodule.response.ShopListingResponse
import com.test.shoppingapp.R

class ShoppingListViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
    val imageProduct: ImageView = mView.findViewById(R.id.img_product_pic)
    val numLikes: TextView = mView.findViewById(R.id.num_likes)
    val numComments: TextView = mView.findViewById(R.id.num_comments)
    val price: TextView = mView.findViewById(R.id.price)
    val status: ImageView = mView.findViewById(R.id.img_status)
    val name: TextView = mView.findViewById(R.id.name)
    lateinit var shopListingResponse: ShopListingResponse
}