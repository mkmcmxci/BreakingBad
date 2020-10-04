package com.mkmcmxci.breakingbad.model;

import com.mkmcmxci.breakingbad.util.GetApiUrl;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuoteApiService {

    private QuoteApi api;

    public QuoteApiService() {

        api = new Retrofit.Builder()
                .baseUrl(GetApiUrl.getBase())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(QuoteApi.class);

    }

    public Single<List<Quote>> getQuoteByChar(String name) {

        return api.getQuoteByChar(name);

    }

}
