package com.azhar.university.magles.presentation.ui.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.azhar.university.magles.MaglesApplication;

/**
 * Created by Yasser.Ibrahim on 6/28/2018.
 */

public class Utils {

    public static boolean isConnectingToInternet() {
        if (MaglesApplication.getApplication().getApplicationContext() != null) {

            ConnectivityManager cm = (ConnectivityManager) MaglesApplication.getApplication().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
