package com.azhar.university.magles;

import com.azhar.university.magles.domain.modules.ApiModule;
import com.azhar.university.magles.domain.modules.PreferencesModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Yasser.Ibrahim on 6/28/2018.
 */

@Singleton
@Component(modules = {ApiModule.class, PreferencesModule.class})
public interface ApplicationComponent {
}
