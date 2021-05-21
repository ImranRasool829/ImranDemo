package com.networks;


import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebInterface {

    @GET(API.API_DATA)
    Call<JsonObject> API_DATA_GET_RESULT();
}
