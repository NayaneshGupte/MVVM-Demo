package com.test.shoppingapp.shoppinglist.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.networkmodule.response.ShopListingResponse
import com.test.shoppingapp.R
import com.test.shoppingapp.shoppinglist.ProductStatus

class ShoppingListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val imageProduct: ImageView = view.findViewById(R.id.img_product_pic)
    private val numLikes: TextView = view.findViewById(R.id.num_likes)
    private val numComments: TextView = view.findViewById(R.id.num_comments)
    private val price: TextView = view.findViewById(R.id.price)
    private val status: ImageView = view.findViewById(R.id.img_status)
    private val name: TextView = view.findViewById(R.id.name)

    fun bind(shopListingResponse: ShopListingResponse) {
        name.text = shopListingResponse.name
        numLikes.text = shopListingResponse.numOfLikes.toString()
        numComments.text = shopListingResponse.numOfComments.toString()
        price.text = String.format("$%s", shopListingResponse.price.toString())

        if (ProductStatus.SOLD_OUT == shopListingResponse.status) {
            status.visibility = View.VISIBLE
        } else {
            status.visibility = View.GONE
        }

        Glide.with(imageProduct)
            .load(shopListingResponse.photo)
            .into(imageProduct)
    }
}