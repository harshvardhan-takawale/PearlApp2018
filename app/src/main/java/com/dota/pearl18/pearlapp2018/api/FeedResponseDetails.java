package com.dota.pearl18.pearlapp2018.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Vineeth on 3/16/2018.
 */

public class FeedResponseDetails {
    @SerializedName("page")
    private int page;

    @SerializedName("total")
    private int totalresults = 0;

    @SerializedName("docs")
    List<FeedDetails> docs;

    @SerializedName("pages")
    int totalPages;

    public FeedResponseDetails(int page, int totalresults, List<FeedDetails> docs, int totalPages) {
        this.page = page;
        this.totalresults = totalresults;
        this.docs = docs;
        this.totalPages = totalPages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalresults() {
        return this.totalresults;
    }

    public void setTotalresults(int totalresults) {
        this.totalresults = totalresults;
    }

    public List<FeedDetails> getDocs() {
        return docs;
    }

    public void setDocs(List<FeedDetails> docs) {
        this.docs = docs;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
