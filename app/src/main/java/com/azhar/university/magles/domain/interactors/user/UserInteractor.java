package com.azhar.university.magles.domain.interactors.user;

import com.azhar.university.magles.domain.interactors.MainInteractor;
import com.azhar.university.magles.domain.models.User;

import java.io.File;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public interface UserInteractor extends MainInteractor {
    void login();

    void logout();

    void editProfile(User user);

    interface UserCallbackStates extends CallbackStates {
        void onLoginComplete();

        void onLogoutComplete();

        void onEditProfileComplete();
    }
}
