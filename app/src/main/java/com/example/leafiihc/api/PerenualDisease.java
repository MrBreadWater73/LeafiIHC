package com.example.leafiihc.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PerenualDisease {
    @SerializedName("id")
    private int id;
    
    @SerializedName("common_name")
    private String commonName;
    
    @SerializedName("scientific_name")
    private String scientificName;
    
    @SerializedName("description")
    private String description;
    
    @SerializedName("solution")
    private String solution;
    
    @SerializedName("host")
    private List<String> host;
    
    @SerializedName("images")
    private List<Image> images;
    
    public static class Image {
        @SerializedName("license")
        private int license;
        
        @SerializedName("license_name")
        private String licenseName;
        
        @SerializedName("license_url")
        private String licenseUrl;
        
        @SerializedName("original_url")
        private String originalUrl;
        
        @SerializedName("regular_url")
        private String regularUrl;
        
        @SerializedName("medium_url")
        private String mediumUrl;
        
        @SerializedName("small_url")
        private String smallUrl;
        
        @SerializedName("thumbnail")
        private String thumbnail;
        
        public String getRegularUrl() {
            return regularUrl;
        }
    }
    
    public int getId() {
        return id;
    }
    
    public String getCommonName() {
        return commonName;
    }
    
    public String getScientificName() {
        return scientificName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getSolution() {
        return solution;
    }
    
    public List<String> getHost() {
        return host;
    }
    
    public List<Image> getImages() {
        return images;
    }
} 