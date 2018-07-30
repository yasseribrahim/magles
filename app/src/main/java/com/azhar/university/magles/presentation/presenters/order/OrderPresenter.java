package com.azhar.university.magles.presentation.presenters.order;

import com.azhar.university.magles.presentation.presenters.MainPresenter;

import java.io.File;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public interface OrderPresenter extends MainPresenter {
    void getOrders(long ownerId);

    void list(long departmentId);

    void list(long departmentId, long statusId);

    void details(long orderId);
}
