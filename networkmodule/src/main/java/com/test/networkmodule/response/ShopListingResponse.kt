package com.test.networkmodule.response

import com.google.gson.annotations.SerializedName

data class ShopListingResponse(
    val id: String,
    val name: String,
    @SerializedName("num_likes") val numOfLikes: Int,
    @SerializedName("num_comments") val numOfComments: Int,
    val price: Int,
    val photo: String,
    val status: String
)
