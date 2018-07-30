package com.azhar.university.magles.domain.interactors.order;

import com.azhar.university.magles.domain.interactors.MainInteractor;
import com.azhar.university.magles.domain.models.Order;

import java.io.File;
import java.util.List;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public interface OrderInteractor extends MainInteractor {
    void getOrders(long ownerId);

    void list(long departmentId);

    void list(long departmentId, long statusId);

    void details(long orderId);

    interface OrderCallbackStates extends CallbackStates {
        void onListComplete(List<Order> orders);

        void onGetDetailsComplete(Order order);
    }
}
