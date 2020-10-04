package com.mkmcmxci.breakingbad.viewmodel;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.mkmcmxci.breakingbad.model.BCharacter;
import com.mkmcmxci.breakingbad.model.CharApiService;
import com.mkmcmxci.breakingbad.model.CharDao;
import com.mkmcmxci.breakingbad.model.CharDatabase;
import com.mkmcmxci.breakingbad.util.SessionManagement;

import java.util.ArrayList;
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

    private InsertCharTask task;
    private RetrieveCharTask taskRetrieve;

    private SessionManagement session = SessionManagement.getInstance(getApplication());

    private long timeInterval = 1800000000000L;


    public ListViewModel(@NonNull Application application) {
        super(application);
    }

    public void refresh() {

        long currentTime = System.nanoTime();

        if (session.loadSession() != 0 && currentTime - session.loadSession() < timeInterval) {

            fetchFromDatabase();

        } else {

            fetchFromAPI();

        }


    }


    private void fetchFromDatabase() {
        taskRetrieve = new RetrieveCharTask();
        taskRetrieve.execute();


    }

    private void fetchFromAPI() {
        disposable.add(
                bbService.getChar()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<BCharacter>>() {
                            @Override
                            public void onSuccess(List<BCharacter> charList) {
                                task = new InsertCharTask();
                                task.execute(charList);

                            }

                            @Override
                            public void onError(Throwable e) {

                                e.printStackTrace();

                            }
                        }));

    }


    private void retrieveChars(List<BCharacter> charList) {

        loading.setValue(false);
        bbChar.setValue(charList);


    }


    @Override
    protected void onCleared() {
        disposable.clear();

        if (task != null) {

            task.cancel(true);
            task = null;
        }

        if (taskRetrieve != null) {

            taskRetrieve.cancel(true);
            taskRetrieve = null;
        }


    }


    private class InsertCharTask extends AsyncTask<List<BCharacter>, Void, List<BCharacter>> {


        @Override
        protected List<BCharacter> doInBackground(List<BCharacter>... lists) {

            List<BCharacter> list = lists[0];
            CharDao dao = CharDatabase.getInstance(getApplication()).charDao();
            dao.deleteAll();

            ArrayList<BCharacter> newList = new ArrayList<>(list);

            List<Long> result = dao.insertAll(newList.toArray(new BCharacter[0]));

            int i = 0;

            while (i < list.size()) {

                list.get(i).uuid = result.get(i).intValue();
                i++;

            }

            return list;

        }

        @Override
        protected void onPostExecute(List<BCharacter> bCharacters) {
            retrieveChars(bCharacters);
            SessionManagement.saveSession(System.nanoTime());

        }
    }

    private class RetrieveCharTask extends AsyncTask<Void, Void, List<BCharacter>> {


        @Override
        protected List<BCharacter> doInBackground(Void... voids) {
            return CharDatabase.getInstance(getApplication()).charDao().getAll();
        }

        @Override
        protected void onPostExecute(List<BCharacter> bCharacters) {
            retrieveChars(bCharacters);

        }
    }


}
