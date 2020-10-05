package com.mkmcmxci.breakingbad.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.mkmcmxci.breakingbad.model.Quote;
import com.mkmcmxci.breakingbad.model.QuoteApiService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class QuoteViewModel extends AndroidViewModel {

    public MutableLiveData<List<Quote>> quote = new MutableLiveData<>();

    public MutableLiveData<Boolean> loading = new MutableLiveData<>();

    private QuoteApiService quoteService = new QuoteApiService();

    private CompositeDisposable disposable = new CompositeDisposable();


    public QuoteViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetch(String name) {

        fetchFromAPI(name);

    }

    private void fetchFromAPI(String name) {
        disposable.addAll(
                quoteService.getQuoteByChar(name).subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<List<Quote>>() {

                    @Override
                    public void onSuccess(List<Quote> quoteList) {


                        loading.setValue(false);

                        quote.setValue(quoteList);

                    }

                    @Override
                    public void onError(Throwable e) {

                        e.printStackTrace();

                    }

                }));

    }

    @Override
    protected void onCleared() {
        disposable.clear();
    }
}
