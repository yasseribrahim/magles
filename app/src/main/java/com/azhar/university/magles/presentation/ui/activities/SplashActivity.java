package com.azhar.university.magles.presentation.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;

import com.azhar.university.magles.R;
import com.azhar.university.magles.domain.utils.UserManager;
import com.azhar.university.magles.domain.views.UserView;
import com.azhar.university.magles.presentation.presenters.user.UserPresenter;
import com.azhar.university.magles.presentation.presenters.user.UserPresenterImp;
import com.azhar.university.magles.presentation.ui.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity implements UserView {

    private static final int SPLASH_DELAY_MILLIS = 2000;

    @BindView(R.id.containerView)
    RelativeLayout containerView;

    private UserPresenter presenter;
    private Handler handler;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (UserManager.getInstance().isExistUserLoggedIn()) {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                if (Utils.isConnectingToInternet()) {
                    presenter.login();
                }
                finish();
            } else {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        presenter = new UserPresenterImp(this);
        handler = new Handler();

        gotoApp();
    }

    private void gotoApp() {
        if (isGooglePlayServicesAvailable(this)) {
            handler.postDelayed(runnable, SPLASH_DELAY_MILLIS);
        } else {
            showRetrySnackBar(getString(R.string.message_general_error), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoApp();
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected View getSnackBarAnchorView() {
        return containerView;
    }

    @Override
    public void onLoginComplete() {

    }

    @Override
    public void onLogoutComplete() {

    }

    @Override
    public void onEditProfileComplete() {

    }

    @Override
    public void onChangeProfilePictureComplete() {

    }

    @Override
    public void unAuthorized() {
    }
}
