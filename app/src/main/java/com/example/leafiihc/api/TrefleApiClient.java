package com.example.leafiihc.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.concurrent.TimeUnit;

public class TrefleApiClient {
    private static final String BASE_URL = "https://trefle.io/";
    private static TrefleApiClient instance;
    private final TrefleApiService apiService;
    
    private TrefleApiClient() {
        // Create logging interceptor
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        
        // Create OkHttp client with interceptors and timeouts
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(chain -> {
                    okhttp3.Request original = chain.request();
                    okhttp3.HttpUrl url = original.url();
                    
                    // Log the URL being called
                    System.out.println("Calling URL: " + url.toString());
                    
                    return chain.proceed(original);
                })
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        // Create a lenient Gson instance that ignores malformed JSON
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
        apiService = retrofit.create(TrefleApiService.class);
    }
    
    public static synchronized TrefleApiClient getInstance() {
        if (instance == null) {
            instance = new TrefleApiClient();
        }
        return instance;
    }
    
    public TrefleApiService getApiService() {
        return apiService;
    }
} 