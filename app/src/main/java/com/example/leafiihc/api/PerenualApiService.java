package com.example.leafiihc.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PerenualApiService {
    
    @GET("v2/species-list")
    Call<PerenualSearchResponse> searchPlants(
            @Query("key") String apiKey,
            @Query("q") String query,
            @Query("page") int page,
            @Query("per_page") int perPage,
            @Query("order") String order,
            @Query("edible") Boolean edible,
            @Query("poisonous") Boolean poisonous,
            @Query("cycle") String cycle,
            @Query("watering") String watering,
            @Query("sunlight") String sunlight,
            @Query("indoor") Boolean indoor,
            @Query("hardiness") String hardiness
    );
    
    @GET("v2/species/details/{id}")
    Call<PerenualPlant> getPlantDetails(
            @Path("id") int plantId,
            @Query("key") String apiKey
    );
    
    @GET("v2/species-list")
    Call<PerenualSearchResponse> getPlantsByPage(
            @Query("key") String apiKey,
            @Query("page") int page,
            @Query("per_page") int perPage
    );
    
    @GET("v2/species-list")
    Call<PerenualSearchResponse> getPlantsByEdiblePart(
            @Query("key") String apiKey,
            @Query("edible") Boolean edible,
            @Query("page") int page,
            @Query("per_page") int perPage
    );
    
    @GET("v2/species-list")
    Call<PerenualSearchResponse> getPlantsByLight(
            @Query("key") String apiKey,
            @Query("sunlight") String sunlight,
            @Query("page") int page,
            @Query("per_page") int perPage
    );
    
    @GET("v2/species-list")
    Call<PerenualSearchResponse> getPlantsByWatering(
            @Query("key") String apiKey,
            @Query("watering") String watering,
            @Query("page") int page,
            @Query("per_page") int perPage
    );
    
    @GET("v2/species-list")
    Call<PerenualSearchResponse> getPlantsByCycle(
            @Query("key") String apiKey,
            @Query("cycle") String cycle,
            @Query("page") int page,
            @Query("per_page") int perPage
    );
    
    @GET("v2/species-list")
    Call<PerenualSearchResponse> getPlantsByHardiness(
            @Query("key") String apiKey,
            @Query("hardiness") String hardiness,
            @Query("page") int page,
            @Query("per_page") int perPage
    );
    
    @GET("v2/species-list")
    Call<PerenualSearchResponse> getIndoorPlants(
            @Query("key") String apiKey,
            @Query("indoor") Boolean indoor,
            @Query("page") int page,
            @Query("per_page") int perPage
    );

    @GET("api/pest-disease-list")
    Call<PerenualDiseaseResponse> getDiseases(
        @Query("key") String apiKey
    );
} 