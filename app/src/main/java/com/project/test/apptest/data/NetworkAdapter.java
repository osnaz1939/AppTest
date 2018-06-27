package com.project.test.apptest.data;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NetworkAdapter {
    private static NotificationAPI sNotificationAPI;
    private Retrofit retrofit;
    
    public NetworkAdapter() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://disk.tsft.ru")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        sNotificationAPI = retrofit.create(NotificationAPI.class);
    }
    
    public static NotificationAPI getApi() {
        return sNotificationAPI;
    }
}
