package com.azhar.university.magles.domain.views;

import com.azhar.university.magles.domain.models.Order;

import java.util.List;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public interface OrderView extends BaseView {
    void onListComplete(List<Order> orders);

    void onGetDetailsComplete(Order order);
}
