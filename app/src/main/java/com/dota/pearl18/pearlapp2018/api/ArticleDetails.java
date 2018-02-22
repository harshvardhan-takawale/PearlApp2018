package com.dota.pearl18.pearlapp2018.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;



public class ArticleDetails {
    @SerializedName("_id")
    String id;
    @SerializedName("title")
    String title;
    @SerializedName("updatedAt")
    String time;
    @SerializedName("text")
    String content;
    @SerializedName("keywords")
    ArrayList<String> keywords;
    @SerializedName("authors")
    ArrayList<String> authors;

    public ArticleDetails(String id,String title, String time, String content, ArrayList<String> keywords, ArrayList<String> authors) {
        this.id = id;
        this.title=title;//TODO: ADD TITLE when we get in response
        this.time = time;
        this.content = content;
        this.keywords = keywords;
        this.authors = authors;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }
}
