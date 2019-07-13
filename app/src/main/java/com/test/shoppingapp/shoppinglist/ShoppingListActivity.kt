package com.test.shoppingapp.shoppinglist

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.widget.Toast

import com.test.networkmodule.response.ShopListingResponse
import com.test.shoppingapp.BaseActivity
import com.test.shoppingapp.OnListFragmentInteractionListener
import com.test.shoppingapp.R
import com.test.shoppingapp.shoppinglist.adapter.ShoppingListPagerAdapter

class ShoppingListActivity : BaseActivity(), OnListFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Add Product", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val pager = findViewById<ViewPager>(R.id.pager)

        pager.offscreenPageLimit = 2
        pager.adapter = ShoppingListPagerAdapter(supportFragmentManager)
    }


    override fun onListFragmentInteraction(item: ShopListingResponse) {
        Toast.makeText(this, "Clicked " + item.name(), Toast.LENGTH_SHORT).show()
    }
}
