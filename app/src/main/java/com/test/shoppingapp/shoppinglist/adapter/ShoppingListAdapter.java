package com.test.shoppingapp.shoppinglist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.networkmodule.response.ShopListingResponse;
import com.test.shoppingapp.OnListFragmentInteractionListener;
import com.test.shoppingapp.R;

import java.util.ArrayList;
import java.util.List;


public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListViewHolder> {

    private List<ShopListingResponse> shopListingResponses;
    private final OnListFragmentInteractionListener listener;

    public ShoppingListAdapter(OnListFragmentInteractionListener listener) {
        shopListingResponses = new ArrayList<>();
        this.listener = listener;
    }

    public void setItems(List<ShopListingResponse> items) {
        shopListingResponses.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public ShoppingListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ShoppingListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ShoppingListViewHolder holder, int position) {
        holder.mItem = shopListingResponses.get(position);
        holder.mIdView.setText(shopListingResponses.get(position).name());
        holder.mContentView.setText("" + shopListingResponses.get(position).numOfLikes());

        holder.mView.setOnClickListener(v -> {
            if (null != listener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                listener.onListFragmentInteraction(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return shopListingResponses.size();
    }


}
