package com.test.shoppingapp.shoppinglist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.networkmodule.response.ShopListingResponse;
import com.test.shoppingapp.R;

class ShoppingListViewHolder extends RecyclerView.ViewHolder {
    public final View mView;

    public final ImageView imageProduct;
    public final TextView numLikes;
    public final TextView numComments;
    public final TextView price;
    public final ImageView status;
    public final TextView name;

    public ShopListingResponse mItem;

    public ShoppingListViewHolder(View view) {
        super(view);
        mView = view;
        imageProduct = view.findViewById(R.id.img_product_pic);
        status = view.findViewById(R.id.img_status);
        numLikes = view.findViewById(R.id.num_likes);
        numComments = view.findViewById(R.id.num_comments);
        price = view.findViewById(R.id.price);
        name = view.findViewById(R.id.name);
    }
}