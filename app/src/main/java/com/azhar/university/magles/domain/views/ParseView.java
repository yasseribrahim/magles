package com.azhar.university.magles.domain.views;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public interface ParseView extends BaseView {
    void onRegisterComplete();

    void onLoginComplete();

    void onLogoutComplete();

    void onEditProfileComplete();

    void onChangeProfilePictureComplete();
}
