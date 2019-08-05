package com.test.shoppingapp.shoppinglist.adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import com.test.shoppingapp.shoppinglist.ItemExtras
import com.test.shoppingapp.shoppinglist.fragment.ShoppingListFragment

class ShoppingListPagerAdapter(fm: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentPagerAdapter(fm) {

    private val tabTitles = arrayOf("All", "Men", "Women")

    override fun getItem(position: Int): androidx.fragment.app.Fragment {
        return ShoppingListFragment.newInstance(ItemExtras(position))
    }

    override fun getCount(): Int {
        return tabTitles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }
}
