package com.test.networkmodule.response

import android.annotation.SuppressLint
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
@JsonClass(generateAdapter = true)
data class Page(
        @Json(name = "pageDetails")
        val pageDetails: List<PageDetail>?,
        @Json(name = "status")
        val status: Boolean?
) : Parcelable


@SuppressLint("ParcelCreator")
@Parcelize
@JsonClass(generateAdapter = true)
data class PageDetail(
        @Json(name = "banner")
        val banner: Banner?,
        @Json(name = "content")
        val content: String?,
        @Json(name = "imageHeight")
        val imageHeight: String?,
        @Json(name = "imageWidth")
        val imageWidth: String?,
        @Json(name = "nativeCategoryNavigationListDetails")
        val nativeCategoryNavigationListDetails: List<NativeCategoryNavigationDetails>?,
        @Json(name = "pageLayoutOption")
        val pageLayoutOption: String?,
        @Json(name = "pageTitle")
        val pageTitle: String?,
        @Json(name = "position")
        val position: String?,
        @Json(name = "seoDescription")
        val seoDescription: String?,
        @Json(name = "seoKeyword")
        val seoKeyword: String?,
        @Json(name = "seoTitle")
        val seoTitle: String?,
        @Json(name = "slotName")
        val slotName: String?,
        @Json(name = "timeOut")
        val timeOut: Int?,
        @Json(name = "typeCode")
        val typeCode: String?
) : Parcelable


@SuppressLint("ParcelCreator")
@Parcelize
@JsonClass(generateAdapter = true)
data class ImageDetail(
        @Json(name = "bannerPosition")
        val bannerPosition: Int?,
        @Json(name = "componentPosition")
        val componentPosition: Int?,
        @Json(name = "imagePosition")
        val imagePosition: String?,
        @Json(name = "imageUrl")
        val imageUrl: String?,
        @Json(name = "uId")
        val uId: String?
) : Parcelable


@SuppressLint("ParcelCreator")
@Parcelize
@JsonClass(generateAdapter = true)
data class Banner(
        @Json(name = "bannerPosition")
        val bannerPosition: Int?,
        @Json(name = "componentPosition")
        val componentPosition: Int?,
        @Json(name = "imageUrl")
        val imageUrl: String?,
        @Json(name = "uId")
        val uId: String?
) : Parcelable


@SuppressLint("ParcelCreator")
@Parcelize
@JsonClass(generateAdapter = true)
data class NativeCategoryNavigationDetails(
        @Json(name = "imageDetails")
        val imageDetails: List<ImageDetail>?,
        @Json(name = "nativeCategoryBrowserUrlLink")
        val nativeCategoryBrowserUrlLink: String?,
        @Json(name = "nativeCategoryName")
        val nativeCategoryName: String?,
        @Json(name = "nativeCategoryPageId")
        val nativeCategoryPageId: String?,
        @Json(name = "nativeCategoryType")
        val nativeCategoryType: String?
) : Parcelable