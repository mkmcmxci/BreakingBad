package com.mkmcmxci.breakingbad.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.mkmcmxci.breakingbad.model.BCharacter;
import com.mkmcmxci.breakingbad.model.CharApiService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ListViewModel extends AndroidViewModel {

    public MutableLiveData<List<BCharacter>> bbChar = new MutableLiveData<>();

    public MutableLiveData<Boolean> loading = new MutableLiveData<>();


    private CharApiService bbService = new CharApiService();

    private CompositeDisposable disposable = new CompositeDisposable();

    public ListViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchFromAPI() {
        disposable.add(
                bbService.getChar()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<BCharacter>>() {
                            @Override
                            public void onSuccess(List<BCharacter> charList) {

                                loading.setValue(false);
                                bbChar.setValue(charList);


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
