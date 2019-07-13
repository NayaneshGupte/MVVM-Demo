package com.test.networkmodule.retrofitapi;


import com.test.networkmodule.response.MasterListResponse;
import com.test.networkmodule.response.ShopListingResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ShoppingRetrofitAPI {

    @GET
    Single<Response<List<ShopListingResponse>>> getShoppingList(@Url String url);

    @GET
    Single<Response<List<MasterListResponse>>> getMasterListResponse(@Url String url);
}
