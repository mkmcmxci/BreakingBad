package com.mkmcmxci.breakingbad.model;

import android.util.Log;

import com.mkmcmxci.breakingbad.util.GetApiUrl;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CharApiService {

    private CharApi api;

    public CharApiService() {
        api = new Retrofit.Builder()
                .baseUrl(GetApiUrl.getBase())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(CharApi.class);

    }

    public Single<List<BCharacter>> getChar() {

        return api.getChar();
    }

    public Single<List<BCharacter>> getCharById(int id) {


        return api.getCharById(id);
    }
}
