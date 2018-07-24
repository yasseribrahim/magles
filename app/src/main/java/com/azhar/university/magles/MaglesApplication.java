package com.azhar.university.magles;

import android.app.Application;

import com.azhar.university.magles.domain.modules.ApiModule;
import com.azhar.university.magles.domain.modules.ApplicationModule;
import com.azhar.university.magles.domain.modules.PreferencesModule;

import java.io.File;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public class MaglesApplication extends Application {
    private static ApplicationComponent component;
    private static Application application;

    public static Application getApplication() {
        return application;
    }

    public static ApplicationComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;

        buildDagger();
    }

    public void buildDagger() {
        component = DaggerApplicationComponent.
                builder().
                apiModule(new ApiModule(new File(getCacheDir(), "responses"), this, BuildConfig.HOST)).
                build();
    }
}