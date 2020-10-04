package com.mkmcmxci.breakingbad.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mkmcmxci.breakingbad.model.BCharacter;
import com.mkmcmxci.breakingbad.model.CharApiService;
import com.mkmcmxci.breakingbad.model.Quote;
import com.mkmcmxci.breakingbad.model.QuoteApiService;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DetailViewModel extends ViewModel {

    public MutableLiveData<BCharacter> singleChar = new MutableLiveData<>();

    public MutableLiveData<List<Quote>> quoteList = new MutableLiveData<>();

    public MutableLiveData<Boolean> loading = new MutableLiveData<>();


    private CompositeDisposable disposable = new CompositeDisposable();

    private CharApiService bbService = new CharApiService();

    private QuoteApiService quoteService = new QuoteApiService();

    public void fetch(int id, String name) {

        fetchFromAPI(id, name);

    }

    private void fetchFromAPI(int id, String name) {
        disposable.addAll(
                bbService.getCharById(id)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<BCharacter>>() {
                            @Override
                            public void onSuccess(List<BCharacter> bCharacters) {

                                loading.setValue(false);
                                singleChar.setValue(bCharacters.get(0));

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                        }),

                quoteService.getQuoteByChar(name).subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<List<Quote>>() {
                    @Override
                    public void onSuccess(List<Quote> quotes) {

                        quoteList.setValue(quotes);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                })

        );

    }

    @Override
    protected void onCleared() {
        disposable.clear();
    }
}




