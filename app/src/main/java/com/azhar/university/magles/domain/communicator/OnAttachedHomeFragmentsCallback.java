package com.azhar.university.magles.domain.communicator;

import com.azhar.university.magles.presentation.ui.fragments.MoreFragment;
import com.azhar.university.magles.presentation.ui.fragments.OrdersFragment;

/**
 * Created by yasser.ibrahim on 8/6/2018.
 */

public interface OnAttachedHomeFragmentsCallback {
    void onFragmentAttachedOrders(OrdersFragment ordersFragment);

    void onFragmentAttachedMore(MoreFragment moreFragment);
}
