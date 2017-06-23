package com.mediclink.hassan.bakingapp.network;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by hassan on 6/16/2017.
 */

public interface ApiService {
    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<JsonArray> getRecipe();
}
