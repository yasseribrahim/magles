package com.azhar.university.magles.presentation.ui.utils;

import android.support.v4.content.res.ResourcesCompat;

import com.azhar.university.magles.MaglesApplication;
import com.azhar.university.magles.R;

/**
 * Created by interactive on 7/30/18.
 */

public class UIUtils {
    public static int getStatusColor(long id) {
        switch ((int) id) {
            case 1:
                return ResourcesCompat.getColor(MaglesApplication.getApplication().getResources(), R.color.status_1, null);
            case 2:
                return ResourcesCompat.getColor(MaglesApplication.getApplication().getResources(), R.color.status_2, null);
            case 3:
                return ResourcesCompat.getColor(MaglesApplication.getApplication().getResources(), R.color.status_3, null);
            case 4:
                return ResourcesCompat.getColor(MaglesApplication.getApplication().getResources(), R.color.status_4, null);
            case 5:
                return ResourcesCompat.getColor(MaglesApplication.getApplication().getResources(), R.color.status_5, null);
            case 6:
                return ResourcesCompat.getColor(MaglesApplication.getApplication().getResources(), R.color.status_6, null);
            default:
                return ResourcesCompat.getColor(MaglesApplication.getApplication().getResources(), R.color.status_1, null);
        }
    }
}
