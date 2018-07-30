package com.azhar.university.magles.domain.interactors.order;

import android.view.View;

import com.azhar.university.magles.domain.controller.Controller;
import com.azhar.university.magles.domain.interactors.BaseInteractor;
import com.azhar.university.magles.domain.models.Order;
import com.azhar.university.magles.domain.models.User;
import com.azhar.university.magles.domain.utils.UserManager;

import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.HttpException;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public class OrderInteractorImp extends BaseInteractor implements OrderInteractor {
    private final Controller.OrderController controller;
    private final OrderCallbackStates callback;

    public OrderInteractorImp(Controller.OrderController controller, OrderCallbackStates callback) {
        this.controller = controller;
        this.callback = callback;
    }

    @Override
    public void getOrders(final long ownerId) {
        callback.showProgress();
        User user = UserManager.getInstance().getCurrentUser();
        prepare(controller.getOrders(user.getAuthorization(), ownerId), new OrderListObserver(callback, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOrders(ownerId);
            }
        }));
    }

    @Override
    public void list(final long departmentId) {
        callback.showProgress();
        User user = UserManager.getInstance().getCurrentUser();
        prepare(controller.list(user.getAuthorization(), departmentId), new OrderListObserver(callback, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list(departmentId);
            }
        }));
    }

    @Override
    public void list(final long departmentId, final long statusId) {
        callback.showProgress();
        User user = UserManager.getInstance().getCurrentUser();
        prepare(controller.list(user.getAuthorization(), departmentId, statusId), new OrderListObserver(callback, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list(departmentId, statusId);
            }
        }));
    }

    @Override
    public void details(final long orderId) {
        callback.showProgress();
        User user = UserManager.getInstance().getCurrentUser();
        prepare(controller.details(user.getAuthorization(), orderId), new OrderDetailsObserver(callback, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details(orderId);
            }
        }));
    }

    private final class OrderListObserver extends BaseObserver<List<Order>> {
        private View.OnClickListener listener;

        public OrderListObserver(CallbackStates callback, View.OnClickListener listener) {
            super(callback);
            this.listener = listener;
        }

        @Override
        public void onNext(List<Order> orders) {
            callback.onListComplete(orders);
            super.onNext(orders);
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);if (e instanceof HttpException) {
                if (((HttpException) e).code() == HttpsURLConnection.HTTP_UNAUTHORIZED) {
                    callback.unAuthorized();
                    return;
                }
            }
            String message = callback.getErrorMessage(e);
            callback.failure(message != null ? message : e.getMessage(), listener);
        }
    }

    private final class OrderDetailsObserver extends BaseObserver<Order> {

        private View.OnClickListener listener;

        public OrderDetailsObserver(CallbackStates callback, View.OnClickListener listener) {
            super(callback);
            this.listener = listener;
        }

        @Override
        public void onNext(Order order) {
            callback.onGetDetailsComplete(order);
            super.onNext(order);
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            String message = callback.getErrorMessage(e);
            callback.failure(message != null ? message : e.getMessage(), listener);
        }
    }
}
