package com.test.networkmodule.response;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class MasterListResponse {

    public static TypeAdapter<MasterListResponse> typeAdapter(Gson gson) {
        return new AutoValue_MasterListResponse.GsonTypeAdapter(gson);
    }

    public abstract String name();

    public abstract String data();

}
