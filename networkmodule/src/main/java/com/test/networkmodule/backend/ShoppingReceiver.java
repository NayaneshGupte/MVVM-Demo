package com.test.networkmodule.backend;

import android.text.TextUtils;

import com.test.networkmodule.exceptions.ApiException;
import com.test.networkmodule.response.MasterListResponse;
import com.test.networkmodule.response.ShopListingResponse;
import com.test.networkmodule.retrofitapi.ShoppingRetrofitAPI;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class ShoppingReceiver {

    private final ShoppingRetrofitAPI shoppingRetrofitAPI;

    private final static String SHOPPING_MASTER_UL = "https://s3-ap-northeast-1.amazonaws.com/m-et/Android/json/master.json";

    @Inject
    public ShoppingReceiver(ShoppingRetrofitAPI shoppingRetrofitAPI) {
        this.shoppingRetrofitAPI = shoppingRetrofitAPI;
    }

    public Single<List<ShopListingResponse>> getShoppingList(String name) {
        return shoppingRetrofitAPI.getMasterListResponse(SHOPPING_MASTER_UL)
                .map(response -> {
                    if (response.isSuccessful()) {
                        return response.body();
                    } else {
                        throw new ApiException("Master API Failure");
                    }
                })
                .map(masterListResponse -> getUrlForName(masterListResponse, name))
                .map(url -> {
                    if (TextUtils.isEmpty(url)) {
                        throw new ApiException("Content not available");
                    } else {
                        return url;
                    }
                })
                .flatMap(shoppingRetrofitAPI::getShoppingList)
                .map(shopListingResponseResponse -> {
                    if (shopListingResponseResponse.isSuccessful()) {
                        return shopListingResponseResponse.body();
                    } else {
                        throw new ApiException(name + " API Failure");
                    }
                });
    }


    private String getUrlForName(List<MasterListResponse> masterListResponseList, String name) {
        for (MasterListResponse masterResponse : masterListResponseList) {
            if (masterResponse.name().equalsIgnoreCase(name)) {
                return masterResponse.data();
            }
        }
        return null;
    }
}
