package com.azhar.university.magles.presentation.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.azhar.university.magles.R;
import com.azhar.university.magles.domain.communicator.OnAttachedHomeFragmentsCallback;
import com.azhar.university.magles.domain.communicator.OnLogoutCallback;
import com.azhar.university.magles.presentation.ui.fragments.MoreFragment;
import com.azhar.university.magles.presentation.ui.fragments.OrdersFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener, OnLogoutCallback, OnAttachedHomeFragmentsCallback {
    @BindView(R.id.main_container)
    ConstraintLayout container;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private OrdersFragment ordersFragment;
    private MoreFragment moreFragment;

    private boolean doubleBackToExitPressedOnce;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        setupUI();

        navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected View getSnackBarAnchorView() {
        return container;
    }

    private void setupUI() {
        setupSupportedActionBar(toolbar);

        ordersFragment = OrdersFragment.newInstance();
        moreFragment = MoreFragment.newInstance();
        replace(ordersFragment, R.string.title_my_orders);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                replace(ordersFragment, R.string.title_my_orders);
                return true;
            case R.id.navigation_dashboard:

                return true;
            case R.id.navigation_notifications:

                return true;
            case R.id.navigation_more:
                replace(moreFragment, R.string.title_more);
                return true;
        }
        return false;
    }

    @Override
    public void onFragmentAttachedOrders(OrdersFragment ordersFragment) {
        this.ordersFragment = ordersFragment;
    }

    @Override
    public void onFragmentAttachedMore(MoreFragment moreFragment) {
        this.moreFragment = moreFragment;
    }

    private void replace(Fragment fragment, int titleId) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        setActionBarTitle(titleId);
    }

    @Override
    public void onLogoutCallback() {
        startActivity(new Intent(this, SplashActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, R.string.message_exit, Toast.LENGTH_SHORT).show();

        handler.postDelayed(runnable, 2000);
    }
}
