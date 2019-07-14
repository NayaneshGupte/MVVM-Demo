package com.test.networkmodule.backend

import android.text.TextUtils
import com.test.networkmodule.exceptions.ApiException
import com.test.networkmodule.response.MasterListResponse
import com.test.networkmodule.response.ShopListingResponse
import com.test.networkmodule.retrofitapi.ShoppingRetrofitAPI
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShoppingReceiver @Inject
constructor(private val shoppingRetrofitAPI: ShoppingRetrofitAPI) {

    fun getShoppingList(name: String): Single<List<ShopListingResponse>> {

        return shoppingRetrofitAPI.getMasterListResponse(SHOPPING_MASTER_UL)
            .map { t -> getMasterResponse(t) }
            .map { t -> getUrlForName(t, name) }
            .map { t -> checkValidUrl(t) }
            .flatMap { t -> shoppingRetrofitAPI.getShoppingList(t) }
            .map { t -> getShoppingListResponse(t) }

    }

    private fun getShoppingListResponse(response: Response<List<ShopListingResponse>>): List<ShopListingResponse> {
        return when {
            response.isSuccessful -> response.body()
            else -> throw ApiException("Content not available")
        }
    }

    private fun getMasterResponse(response: Response<List<MasterListResponse>>): List<MasterListResponse> {
        return when {
            response.isSuccessful -> response.body()
            else -> throw ApiException("Master API Failure")
        }
    }

    private fun checkValidUrl(url: String): String {
        return when {
            TextUtils.isEmpty(url) -> throw ApiException("Content not available")
            else -> url
        }
    }

    private fun getUrlForName(
        masterListResponseList: List<MasterListResponse>,
        name: String
    ): String? {
        for ((name1, data) in masterListResponseList) {
            if (name1.equals(name, ignoreCase = true)) {
                return data
            }
        }
        return null
    }

    companion object {
        private const val SHOPPING_MASTER_UL =
            "https://s3-ap-northeast-1.amazonaws.com/m-et/Android/json/master.json"
    }
}
