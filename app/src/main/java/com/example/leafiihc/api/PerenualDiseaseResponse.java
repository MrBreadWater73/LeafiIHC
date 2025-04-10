package com.example.leafiihc.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PerenualDiseaseResponse {
    @SerializedName("data")
    private List<PerenualDisease> data;
    
    @SerializedName("to")
    private int to;
    
    @SerializedName("per_page")
    private int perPage;
    
    @SerializedName("current_page")
    private int currentPage;
    
    @SerializedName("from")
    private int from;
    
    @SerializedName("last_page")
    private int lastPage;
    
    @SerializedName("total")
    private int total;
    
    public List<PerenualDisease> getData() {
        return data;
    }
    
    public int getTo() {
        return to;
    }
    
    public int getPerPage() {
        return perPage;
    }
    
    public int getCurrentPage() {
        return currentPage;
    }
    
    public int getFrom() {
        return from;
    }
    
    public int getLastPage() {
        return lastPage;
    }
    
    public int getTotal() {
        return total;
    }
} 