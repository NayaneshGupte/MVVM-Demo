package com.test.shoppingapp.shoppinglist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.test.networkmodule.response.ShopListingResponse
import com.test.shoppingapp.OnListFragmentInteractionListener
import com.test.shoppingapp.R
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
        holder.bind(shopListingResponse)
        holder.view.setOnClickListener {
            listener?.onListFragmentInteraction(shopListingResponse)
        }
    }

    override fun getItemCount(): Int {
        return shopListingResponses.size
    }
}
