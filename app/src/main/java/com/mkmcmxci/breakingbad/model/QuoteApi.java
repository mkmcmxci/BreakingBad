package com.mkmcmxci.breakingbad.model;

import java.util.List;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface QuoteApi {

    @GET
    Single<List<Quote>> getQuoteByChar(@Url String url);

}
