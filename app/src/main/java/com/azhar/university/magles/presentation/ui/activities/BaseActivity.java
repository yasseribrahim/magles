package com.azhar.university.magles.presentation.ui.activities;

import com.azhar.university.magles.R;
import com.azhar.university.magles.presentation.ui.dialogs.ProgressDialogFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected abstract View getSnackBarAnchorView();

    public Snackbar showRetrySnackBar(String message, View.OnClickListener clickListener) {
        Snackbar snackbar = Snackbar.make(getSnackBarAnchorView(), message, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(R.string.label_retry, clickListener);
        snackbar.show();
        return snackbar;
    }

    protected void setActionBarTitle(int titleId) {
        getSupportActionBar().setTitle(titleId);
    }

    protected void setupSupportedActionBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        toolbar.setTitleTextAppearance(this, R.style.ToolBarShadowStyle);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    protected void setupSupportedActionBarWithHome(Toolbar toolbar) {
        setupSupportedActionBarWithHome(toolbar, R.drawable.ic_home_white);
    }

    protected void hideKeyboard() {
        try {
            if (getCurrentFocus() != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        } catch (Exception e) {
        }
    }

    protected void hideKeyboard(Dialog dialog) {
        try {
            if (dialog.getCurrentFocus() != null) {
                InputMethodManager imm = (InputMethodManager) this.getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(dialog.getCurrentFocus().getWindowToken(), 0);
            }
        } catch (Exception ignored) {
        }
    }

    protected void setupSupportedActionBarWithHome(Toolbar toolbar, int homeUpIndicatorId) {
        setSupportActionBar(toolbar);
        toolbar.setTitleTextAppearance(this, R.style.ToolBarShadowStyle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(homeUpIndicatorId);
    }

    public Snackbar showConnectionSnackBar(View.OnClickListener clickListener) {
        Snackbar snackbar = Snackbar.make(getSnackBarAnchorView(), R.string.message_error_connection, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(R.string.label_retry, clickListener);
        snackbar.show();
        return snackbar;
    }

    public Snackbar showInfoSnackBar(@StringRes int message) {
        Snackbar snackbar = Snackbar.make(getSnackBarAnchorView(), message, Snackbar.LENGTH_LONG);
        snackbar.show();
        return snackbar;
    }

    public Snackbar showInfoSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(getSnackBarAnchorView(), message, Snackbar.LENGTH_LONG);
        snackbar.show();
        return snackbar;
    }

    public boolean isGooglePlayServicesAvailable(Activity activity) {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(activity);
        if (status != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(status)) {
                googleApiAvailability.getErrorDialog(activity, status, 2404).show();
            }
            return false;
        }
        return true;
    }

    public void showProgress() {
        ProgressDialogFragment.show(getSupportFragmentManager());
    }

    public void hideProgress() {
        ProgressDialogFragment.hide(getSupportFragmentManager());
    }
}

