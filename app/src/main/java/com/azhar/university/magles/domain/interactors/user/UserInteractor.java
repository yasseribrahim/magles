package com.azhar.university.magles.domain.interactors.user;

import com.azhar.university.magles.domain.interactors.MainInteractor;

import java.io.File;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public interface UserInteractor extends MainInteractor {
    void login();

    void logout();

    void editProfile(String fullName);

    void changeProfilePicture(File file);

    interface UserCallbackStates extends CallbackStates {
        void onLoginComplete();

        void onLogoutComplete();

        void onEditProfileComplete();

        void onChangeProfilePictureComplete();
    }
}
