package com.test.networkmodule.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShopListingResponse(
    val id: String,
    val name: String,
    @Json(name = "num_likes") val numOfLikes: Int,
    @Json(name = "num_comments") val numOfComments: Int,
    val price: Int,
    val photo: String,
    val status: String
)
