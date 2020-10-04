package com.mkmcmxci.breakingbad.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuoteApi {

    @GET("api/quote")
    Single<List<Quote>> getQuoteByChar(@Query("author") String name);

}
