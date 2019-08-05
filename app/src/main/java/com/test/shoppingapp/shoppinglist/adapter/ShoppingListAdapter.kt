package com.test.shoppingapp.shoppinglist.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.test.networkmodule.response.ShopListingResponse
import com.test.shoppingapp.OnListFragmentInteractionListener
import com.test.shoppingapp.R
import com.test.shoppingapp.shoppinglist.ProductStatus
import java.util.*


class ShoppingListAdapter(private val listener: OnListFragmentInteractionListener?) :
    androidx.recyclerview.widget.RecyclerView.Adapter<ShoppingListViewHolder>() {

    private val shopListingResponses: MutableList<ShopListingResponse>

    init {
        shopListingResponses = ArrayList()
    }

    fun setItems(items: List<ShopListingResponse>) {
        shopListingResponses.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return ShoppingListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        val shopListingResponse = shopListingResponses[position]
        holder.shopListingResponse = shopListingResponse
        holder.name.text = shopListingResponse.name
        holder.numLikes.text = shopListingResponse.numOfLikes.toString()
        holder.numComments.text = shopListingResponse.numOfComments.toString()
        holder.price.text = String.format("$%s", shopListingResponse.price.toString())

        if (ProductStatus.SOLD_OUT == shopListingResponse.status) {
            holder.status.visibility = View.VISIBLE
        } else {
            holder.status.visibility = View.GONE
        }

        Glide.with(holder.imageProduct)
            .load(shopListingResponse.photo)
            .into(holder.imageProduct)

        holder.mView.setOnClickListener {
            listener?.onListFragmentInteraction(holder.shopListingResponse)
        }
    }

    override fun getItemCount(): Int {
        return shopListingResponses.size
    }


}
