package com.test.shoppingapp.shoppinglist.adapter


import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import com.test.shoppingapp.shoppinglist.ItemExtras
import com.test.shoppingapp.shoppinglist.fragment.ItemFragment

class ShoppingListPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val tabTitles = arrayOf("All", "Men", "Women")

    override fun getItem(position: Int): Fragment {
        return ItemFragment.newInstance(ItemExtras(position))
    }

    override fun getCount(): Int {
        return tabTitles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }
}
