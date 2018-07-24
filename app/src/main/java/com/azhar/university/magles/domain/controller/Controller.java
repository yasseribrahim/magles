package com.azhar.university.magles.domain.controller;

import com.azhar.university.magles.BuildConfig;
import com.azhar.university.magles.domain.models.User;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by interactive on 7/23/18.
 */

public interface Controller {
    interface UserController {
        @GET(BuildConfig.BASE_URL + "authentication/login")
        Observable<User> login(@Header("Authorization") String authorization);
    }
}
