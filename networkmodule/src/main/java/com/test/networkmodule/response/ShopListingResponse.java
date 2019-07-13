package com.test.networkmodule.response;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class ShopListingResponse {

    public static TypeAdapter<ShopListingResponse> typeAdapter(Gson gson) {
        return new AutoValue_ShopListingResponse.GsonTypeAdapter(gson);
    }

    public abstract String id();

    public abstract String name();

    public abstract String status();

    @SerializedName("num_likes")
    public abstract Integer numOfLikes();

    @SerializedName("num_comments")
    public abstract Integer numOfComments();

    public abstract Integer price();

    public abstract String photo();

}
