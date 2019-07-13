package com.test.shoppingapp.shoppinglist.fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.networkmodule.response.ShopListingResponse;
import com.test.shoppingapp.OnListFragmentInteractionListener;
import com.test.shoppingapp.R;
import com.test.shoppingapp.di.Injectable;
import com.test.shoppingapp.shoppinglist.ItemExtras;
import com.test.shoppingapp.shoppinglist.ShoppingListViewModel;
import com.test.shoppingapp.shoppinglist.adapter.ShoppingListAdapter;

import java.util.List;

import javax.inject.Inject;

public class ItemFragment extends Fragment implements Injectable {

    private static final String ITEM_EXTRAS = "ITEM_EXTRAS";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private ShoppingListViewModel shoppingListViewModel;
    private OnListFragmentInteractionListener listener;
    private ShoppingListAdapter shoppingListAdapter;

    public ItemFragment() {

    }

    public static ItemFragment newInstance(ItemExtras itemExtras) {
        Bundle args = new Bundle();
        args.putParcelable(ITEM_EXTRAS, itemExtras);
        ItemFragment fragment = new ItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));

        shoppingListAdapter = new ShoppingListAdapter(listener);
        recyclerView.setAdapter(shoppingListAdapter);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            listener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        shoppingListViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ShoppingListViewModel.class);

        if (null != getArguments()) {
            ItemExtras itemExtras = getArguments().getParcelable(ITEM_EXTRAS);
            int position = itemExtras.getPosition();

            shoppingListViewModel.findListFromIndex(position);
            shoppingListViewModel.getLiveDataForShoppingList(position).observe(this, this::setShoppingListData);
        }

    }

    private void setShoppingListData(List<ShopListingResponse> shoppingContentList) {
        shoppingListAdapter.setItems(shoppingContentList);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

}
