package com.example.leafiihc.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TrefleApiService {
    @GET("api/v1/plants/search")
    Call<TrefleSearchResponse> searchPlants(
        @Query("token") String token,
        @Query("q") String query,
        @Query("page") int page
    );

    @GET("api/v1/species")
    Call<TrefleSearchResponse> getPlants(
        @Query("token") String token,
        @Query("page") int page
    );

    @GET("api/v1/species/{id}")
    Call<TrefleSearchResponse> getPlantDetails(
        @Path("id") int plantId,
        @Query("token") String token
    );
} 