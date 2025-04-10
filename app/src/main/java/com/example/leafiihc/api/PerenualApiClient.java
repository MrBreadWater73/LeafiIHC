package com.example.leafiihc.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.concurrent.TimeUnit;

public class PerenualApiClient {
    private static final String BASE_URL = "https://perenual.com/";
    private static PerenualApiClient instance;
    private final PerenualApiService apiService;
    
    private PerenualApiClient() {
        // Create logging interceptor
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        
        // Create OkHttp client with interceptors and timeouts
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        // Create a lenient Gson instance
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        
        // Create Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        
        // Create API service
        apiService = retrofit.create(PerenualApiService.class);
    }
    
    public static synchronized PerenualApiClient getInstance() {
        if (instance == null) {
            instance = new PerenualApiClient();
        }
        return instance;
    }
    
    public PerenualApiService getApiService() {
        return apiService;
    }
} 