package com.project.test.apptest.presentation;

import com.project.test.apptest.data.NetworkAdapter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TestPresenter {
    
    private TestActivity mView;
    private NetworkAdapter mNetworkAdapter = new NetworkAdapter();
    private static TestPresenter instance;
    
    public static TestPresenter getInstance() {
        if (instance == null) {
            instance = new TestPresenter();
        }
        return instance;
    }
    
    public void bindView(TestActivity test) {
        this.mView = test;
    }
    
    public void startInterval() {
        Observable.interval(12000, TimeUnit.MILLISECONDS)
                .subscribe(this::createResponse);
    }
    
    private void createResponse(final Long aLong) throws IOException {
        mNetworkAdapter.getApi().concreteNotification()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(it -> Observable.fromIterable(it))
                .filter(it -> it.getStartDateTime().getTime() < System.currentTimeMillis())
                .filter(it -> it.getEndDateTime().getTime() > System.currentTimeMillis())
                .toList()
                .subscribe(mView::updateNotification);
    }
    
}
