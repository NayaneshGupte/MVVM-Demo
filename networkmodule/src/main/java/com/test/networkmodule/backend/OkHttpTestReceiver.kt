package com.test.networkmodule.backend

import com.test.networkmodule.exceptions.ApiException
import com.test.networkmodule.response.Page
import com.test.networkmodule.retrofitapi.ShoppingRetrofitAPI
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class OkHttpTestReceiver @Inject
constructor(private val shoppingRetrofitAPI: ShoppingRetrofitAPI) {

    fun getPage(): Single<Page> {
        return shoppingRetrofitAPI.getPageResponse(TEST_URL)
                .map { t -> getPageResponse(t) }

    }

    private fun getPageResponse(response: Response<Page>): Page {
        return when {
            response.isSuccessful -> response.body()!!
            else -> throw ApiException("Content not available")
        }
    }


    companion object {
        private const val TEST_URL =
                "https://www.ajio.com/rilfnlwebservices/v2/rilfnl/newcategoryselection?pageId=firstpagemobileapp&uiel=Mobile"
    }
}