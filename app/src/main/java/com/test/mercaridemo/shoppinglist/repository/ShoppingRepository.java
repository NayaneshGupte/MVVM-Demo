package com.test.shoppingapp.shoppinglist.repository;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.test.networkmodule.api.ShoppingAPI;
import com.test.networkmodule.response.ShopListingResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShoppingRepository {

    private static final String TAG = "ShoppingRepository";

    private final ShoppingAPI shoppingAPI;

    private final MutableLiveData<List<ShopListingResponse>> mensShoppingList;
    private final MutableLiveData<List<ShopListingResponse>> womensShoppingList;
    private final MutableLiveData<List<ShopListingResponse>> allShoppingList;

    private final CompositeDisposable compositeDisposable;

    @Inject
    public ShoppingRepository(ShoppingAPI shoppingAPI) {
        this.shoppingAPI = shoppingAPI;
        this.compositeDisposable = new CompositeDisposable();
        this.mensShoppingList = new MutableLiveData<>();
        this.womensShoppingList = new MutableLiveData<>();
        this.allShoppingList = new MutableLiveData<>();
    }

    public void getShoppingListForMen() {
        Disposable disposable = shoppingAPI.getShoppingListForMen()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onMensListReceived, this::onApiError);
        compositeDisposable.addAll(disposable);
    }

    public void getShoppingListForWomen() {
        Disposable disposable = shoppingAPI.getShoppingListForWomen()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onWoensListReceived, this::onApiError);
        compositeDisposable.addAll(disposable);
    }

    public void getShoppingListForAll() {
        Disposable disposable = shoppingAPI.getShoppingListForAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onAllListReceived, this::onApiError);
        compositeDisposable.addAll(disposable);
    }

    private void onMensListReceived(List<ShopListingResponse> shopListingResponse) {
        mensShoppingList.setValue(shopListingResponse);
    }

    private void onWoensListReceived(List<ShopListingResponse> shopListingResponse) {
        womensShoppingList.setValue(shopListingResponse);
    }

    private void onAllListReceived(List<ShopListingResponse> shopListingResponse) {
        allShoppingList.setValue(shopListingResponse);
    }

    private void onApiError(Throwable throwable) {
        Log.e(TAG, "Error " + throwable.getMessage(), throwable);
    }

    public LiveData<List<ShopListingResponse>> getMensShoppingList() {
        return mensShoppingList;
    }

    public LiveData<List<ShopListingResponse>> getWomensShoppingList() {
        return womensShoppingList;
    }

    public LiveData<List<ShopListingResponse>> getAllShoppingList() {
        return allShoppingList;
    }

    public void onCleared() {
        compositeDisposable.clear();
    }

}
