package com.mkmcmxci.breakingbad.model;

import android.util.Log;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CharApi {

    @GET("characters")
    Single<List<BCharacter>> getChar();

    @GET("characters/{id}")
    Single<List<BCharacter>> getCharById(@Path("id") int id);
}
