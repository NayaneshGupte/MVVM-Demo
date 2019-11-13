package com.test.shoppingapp.shoppinglist.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.networkmodule.api.ShoppingAPI
import com.test.networkmodule.response.Page
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PageRepositiory @Inject
constructor(private val shoppingAPI: ShoppingAPI) {


    private val pageMLD: MutableLiveData<Page> = MutableLiveData()

    val pageLD: LiveData<Page> = pageMLD


    private val compositeDisposable: CompositeDisposable = CompositeDisposable()


    fun getPageData() {

        val disposable = shoppingAPI.getPage
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ this.onPageDataRecevied(it) },
                        { this.onPageError(it) })
        compositeDisposable.addAll(disposable)
    }


    fun onPageDataRecevied(page: Page) {

        Log.d("PageRepositiory", "Success")
    }

    fun onPageError(throwable: Throwable) {
        Log.e("PageRepositiory", "Error", throwable)
    }

}