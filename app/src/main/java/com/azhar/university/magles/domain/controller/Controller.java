package com.azhar.university.magles.domain.controller;

import com.azhar.university.magles.BuildConfig;
import com.azhar.university.magles.domain.models.Order;
import com.azhar.university.magles.domain.models.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by interactive on 7/23/18.
 */

public interface Controller {
    interface UserController {
        @GET(BuildConfig.BASE_URL + "authentication/login")
        Observable<User> login(@Header("Authorization") String authorization);

        @POST(BuildConfig.BASE_URL + "users/edit")
        Observable<User> edit(@Header("Authorization") String authorization, @Body User user);
    }

    interface OrderController {
        @GET(BuildConfig.BASE_URL + "orders/myorders/{ownerId}")
        Observable<List<Order>> getOrders(@Header("Authorization") String authorization, @Path("ownerId") long ownerId);

        @GET(BuildConfig.BASE_URL + "orders/list/{departmentId}")
        Observable<List<Order>> list(@Header("Authorization") String authorization, @Path("departmentId") long departmentId);

        @GET(BuildConfig.BASE_URL + "orders/list/{departmentId}/{statusId}")
        Observable<List<Order>> list(@Header("Authorization") String authorization, @Path("departmentId") long departmentId, @Path("statusId") long statusId);

        @GET(BuildConfig.BASE_URL + "orders/details/{orderId}")
        Observable<Order> details(@Header("Authorization") String authorization, @Path("orderId") long orderId);
    }
}
