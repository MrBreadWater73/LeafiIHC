package com.example.leafiihc.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PerenualSearchResponse {
    @SerializedName("data")
    private List<PerenualPlant> data;
    
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
    
    public List<PerenualPlant> getData() {
        return data;
    }
    
    public void setData(List<PerenualPlant> data) {
        this.data = data;
    }
    
    public int getTo() {
        return to;
    }
    
    public void setTo(int to) {
        this.to = to;
    }
    
    public int getPerPage() {
        return perPage;
    }
    
    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }
    
    public int getCurrentPage() {
        return currentPage;
    }
    
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    
    public int getFrom() {
        return from;
    }
    
    public void setFrom(int from) {
        this.from = from;
    }
    
    public int getLastPage() {
        return lastPage;
    }
    
    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }
    
    public int getTotal() {
        return total;
    }
    
    public void setTotal(int total) {
        this.total = total;
    }
} 