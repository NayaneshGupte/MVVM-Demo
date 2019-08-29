package com.test.shoppingapp.shoppinglist.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
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
        val recyclerView = view as androidx.recyclerview.widget.RecyclerView
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
            shoppingListViewModel?.getLiveDataForShoppingList(position)?.observe(
                this, Observer { it -> it?.let { this.setShoppingListData(it) } })

            shoppingListViewModel?.getErrorData()
                ?.observe(this, Observer { t -> t?.let { showErrorToast(it) } })
        }

    }

    private fun setShoppingListData(shoppingContentList: List<ShopListingResponse>) {
        shoppingListAdapter?.setItems(shoppingContentList)
    }

    private fun showErrorToast(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
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
