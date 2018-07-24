package com.azhar.university.magles;

import com.azhar.university.magles.domain.modules.ApiModule;
import com.azhar.university.magles.domain.modules.ApplicationModule;
import com.azhar.university.magles.domain.modules.PreferencesModule;
import com.azhar.university.magles.presentation.presenters.user.UserPresenterImp;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Yasser.Ibrahim on 6/28/2018.
 */

@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class, PreferencesModule.class})
public interface ApplicationComponent {
    void inject(UserPresenterImp presenter);
}