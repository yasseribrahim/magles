package com.azhar.university.magles.domain.views;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public interface UserView extends BaseView {
    void onLoginComplete();

    void onLogoutComplete();

    void onEditProfileComplete();

    void onChangeProfilePictureComplete();
}
