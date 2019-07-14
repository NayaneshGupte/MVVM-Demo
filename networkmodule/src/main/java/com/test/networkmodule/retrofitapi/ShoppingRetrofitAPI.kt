package com.test.networkmodule.retrofitapi


import com.test.networkmodule.response.MasterListResponse
import com.test.networkmodule.response.ShopListingResponse

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ShoppingRetrofitAPI {

    @GET
    fun getShoppingList(@Url url: String): Single<Response<List<ShopListingResponse>>>

    @GET
    fun getMasterListResponse(@Url url: String): Single<Response<List<MasterListResponse>>>
}
