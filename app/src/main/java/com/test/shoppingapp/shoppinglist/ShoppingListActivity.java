package com.test.shoppingapp.shoppinglist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.test.networkmodule.response.ShopListingResponse;
import com.test.shoppingapp.BaseActivity;
import com.test.shoppingapp.OnListFragmentInteractionListener;
import com.test.shoppingapp.R;
import com.test.shoppingapp.shoppinglist.adapter.ShoppingListPagerAdapter;

public class ShoppingListActivity extends BaseActivity implements OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        ViewPager pager = findViewById(R.id.pager);

        pager.setAdapter(new ShoppingListPagerAdapter(getSupportFragmentManager()));

    }


    @Override
    public void onListFragmentInteraction(ShopListingResponse item) {

    }
}
