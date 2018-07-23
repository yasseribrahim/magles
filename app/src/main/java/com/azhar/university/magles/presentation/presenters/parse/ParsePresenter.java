package com.azhar.university.magles.presentation.presenters.parse;

import com.azhar.university.magles.presentation.presenters.MainPresenter;

import java.io.File;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public interface ParsePresenter extends MainPresenter {
    void register(String email, String password, String fullName);

    void login(String email, String password);

    void logout();

    void editProfile(String fullName);

    void changeProfilePicture(File file);
}
