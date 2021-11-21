package com.example.laba2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("City.json")
    Call<List<City>> getCities();
}