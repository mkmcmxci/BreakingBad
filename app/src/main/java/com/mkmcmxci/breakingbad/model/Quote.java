package com.mkmcmxci.breakingbad.model;

import com.google.gson.annotations.SerializedName;

public class Quote {

    @SerializedName("quote_id")
    private int quote_id;
    @SerializedName("quote")
    private String quote;
    @SerializedName("author")
    private String author;


    public Quote() {
    }

    public Quote(int quote_id, String quote, String author) {
        this.quote_id = quote_id;
        this.quote = quote;
        this.author = author;
    }

    public int getQuote_id() {
        return quote_id;
    }

    public void setQuote_id(int quote_id) {
        this.quote_id = quote_id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
