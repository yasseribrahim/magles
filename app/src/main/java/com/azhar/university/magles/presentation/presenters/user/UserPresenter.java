package com.azhar.university.magles.presentation.presenters.user;

import com.azhar.university.magles.domain.models.User;
import com.azhar.university.magles.presentation.presenters.MainPresenter;

import java.io.File;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public interface UserPresenter extends MainPresenter {
    void login();

    void logout();

    void editProfile(User user);
}
