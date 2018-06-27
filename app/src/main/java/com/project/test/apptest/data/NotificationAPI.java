package com.project.test.apptest.data;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface NotificationAPI {
    @GET("/vtb")
    Observable<List<NotificationModel>> concreteNotification();
}
