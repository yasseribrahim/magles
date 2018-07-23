package com.azhar.university.magles.domain.views;

import com.azhar.university.magles.domain.models.MoreItem;

import java.util.List;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public interface MoreView extends BaseView {
    void onGetMoreItemsComplete(List<MoreItem> items);
}
