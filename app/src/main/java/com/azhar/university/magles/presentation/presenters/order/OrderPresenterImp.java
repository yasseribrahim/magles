package com.azhar.university.magles.presentation.presenters.order;

import android.view.View;

import com.azhar.university.magles.MaglesApplication;
import com.azhar.university.magles.domain.controller.Controller;
import com.azhar.university.magles.domain.interactors.order.OrderInteractor;
import com.azhar.university.magles.domain.interactors.order.OrderInteractorImp;
import com.azhar.university.magles.domain.models.Order;
import com.azhar.university.magles.domain.views.OrderView;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public class OrderPresenterImp implements OrderPresenter, OrderInteractor.OrderCallbackStates {
    @Inject
    protected Retrofit retrofit;

    private OrderView view;
    private OrderInteractor interactor;

    public OrderPresenterImp(OrderView view) {
        MaglesApplication.getComponent().inject(this);
        this.view = view;
        this.interactor = new OrderInteractorImp(retrofit.create(Controller.OrderController.class), this);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        view = null;
        interactor.onDestroy();
    }

    @Override
    public String getErrorMessage(Throwable throwable) {
        if (view != null) {
            return view.getErrorMessage(throwable);
        }
        return null;
    }

    @Override
    public void getOrders(long ownerId) {
        interactor.getOrders(ownerId);
    }

    @Override
    public void list(long departmentId) {
        interactor.list(departmentId);
    }

    @Override
    public void list(long departmentId, long statusId) {
        interactor.list(departmentId, statusId);
    }

    @Override
    public void details(long orderId) {
        interactor.details(orderId);
    }

    @Override
    public void failure(String message, View.OnClickListener onClickListener) {
        if (view != null) {
            view.showError(message, onClickListener);
        }
    }

    @Override
    public void showProgress() {
        if (view != null) {
            view.showProgress();
        }
    }

    @Override
    public void hideProgress() {
        if (view != null) {
            view.hideProgress();
        }
    }

    @Override
    public void unAuthorized() {
        if (view != null) {
            view.unAuthorized();
        }
    }

    @Override
    public void onListComplete(List<Order> orders) {
        if (view != null) {
            view.onListComplete(orders);
        }
    }

    @Override
    public void onGetDetailsComplete(Order order) {
        if (view != null) {
            view.onGetDetailsComplete(order);
        }
    }
}
