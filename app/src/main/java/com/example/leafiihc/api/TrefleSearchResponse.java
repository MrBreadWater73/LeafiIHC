package com.example.leafiihc.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class TrefleSearchResponse {
    @SerializedName("data")
    private List<TreflePlant> data;

    @SerializedName("meta")
    private Meta meta;

    @SerializedName("links")
    private Links links;

    public static class Meta {
        @SerializedName("total")
        private int total;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }

    public static class Links {
        @SerializedName("self")
        private String self;

        @SerializedName("first")
        private String first;

        @SerializedName("next")
        private String next;

        @SerializedName("last")
        private String last;

        public String getSelf() {
            return self;
        }

        public String getFirst() {
            return first;
        }

        public String getNext() {
            return next;
        }

        public String getLast() {
            return last;
        }
    }

    public List<TreflePlant> getData() {
        return data;
    }

    public Meta getMeta() {
        return meta;
    }

    public Links getLinks() {
        return links;
    }

    public void setData(List<TreflePlant> data) {
        this.data = data;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public void setLinks(Links links) {
        this.links = links;
    }
} 