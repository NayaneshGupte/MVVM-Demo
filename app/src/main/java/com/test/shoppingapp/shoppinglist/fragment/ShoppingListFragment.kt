package com.test.shoppingapp.shoppinglist.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.networkmodule.response.ShopListingResponse
import com.test.shoppingapp.OnListFragmentInteractionListener
import com.test.shoppingapp.R
import com.test.shoppingapp.di.Injectable
import com.test.shoppingapp.shoppinglist.ItemExtras
import com.test.shoppingapp.shoppinglist.ShoppingListViewModel
import com.test.shoppingapp.shoppinglist.adapter.ShoppingListAdapter
import javax.inject.Inject

class ShoppingListFragment : Fragment(), Injectable {

    @set:Inject
    internal var viewModelFactory: ViewModelProvider.Factory? = null

    private var shoppingListViewModel: ShoppingListViewModel? = null
    private var listener: OnListFragmentInteractionListener? = null
    private var shoppingListAdapter: ShoppingListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        val context = view.context
        val recyclerView = view as RecyclerView
        recyclerView.layoutManager = GridLayoutManager(context, COLUMN_COUNT)

        shoppingListAdapter = ShoppingListAdapter(listener)
        recyclerView.adapter = shoppingListAdapter

        return view
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnListFragmentInteractionListener")
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        shoppingListViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ShoppingListViewModel::class.java)

        if (null != arguments) {
            val itemExtras = arguments!!.getParcelable<ItemExtras>(ITEM_EXTRAS)
            val position = itemExtras!!.position

            shoppingListViewModel?.findListFromIndex(position)
            shoppingListViewModel?.getLiveDataForShoppingList(position)!!.observe(
                this,
                Observer<List<ShopListingResponse>> {
                    if (it != null) {
                        this.setShoppingListData(it)
                    }
                })
        }

    }

    private fun setShoppingListData(shoppingContentList: List<ShopListingResponse>) {
        shoppingListAdapter!!.setItems(shoppingContentList)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {

        private const val ITEM_EXTRAS = "ITEM_EXTRAS"

        private const val COLUMN_COUNT = 2

        fun newInstance(itemExtras: ItemExtras): ShoppingListFragment {
            val args = Bundle()
            args.putParcelable(ITEM_EXTRAS, itemExtras)
            val fragment = ShoppingListFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
