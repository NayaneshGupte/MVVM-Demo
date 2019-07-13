package com.test.shoppingapp.shoppinglist.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.test.shoppingapp.shoppinglist.ItemExtras;
import com.test.shoppingapp.shoppinglist.fragment.ItemFragment;

public class ShoppingListPagerAdapter extends FragmentPagerAdapter {

    private String[] tabTitles = new String[]{"All", "Men", "Women"};


    public ShoppingListPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ItemFragment.newInstance(new ItemExtras(position));
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
