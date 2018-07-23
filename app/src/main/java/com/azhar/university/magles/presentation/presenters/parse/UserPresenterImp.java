package com.azhar.university.magles.presentation.presenters.parse;

import android.view.View;

import com.azhar.university.magles.domain.interactors.user.UserInteractor;
import com.azhar.university.magles.domain.interactors.user.UserInteractorImpl;
import com.azhar.university.magles.domain.views.ParseView;

import java.io.File;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public class UserPresenterImp implements ParsePresenter, UserInteractor.UserCallbackStates {
    private ParseView view;
    private UserInteractor interactor;

    public UserPresenterImp(ParseView view) {
        this.view = view;
        this.interactor = new UserInteractorImpl(controller, callback);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        view = null;
        interactor.onDestroy();
    }

    @Override
    public void register(String email, String password, String fullName) {
        interactor.register(email, password, fullName, this);
    }

    @Override
    public void login(String email, String password) {
        interactor.login(email, password, this);
    }

    @Override
    public void logout() {
        interactor.logout(this);
    }

    @Override
    public void editProfile(String fullName) {
        interactor.editProfile(fullName, this);
    }

    @Override
    public void changeProfilePicture(File file) {
        interactor.changeProfilePicture(file, this);
    }

    @Override
    public void failure(String message, View.OnClickListener onClickListener) {
        if (view != null) {
            view.showError(message, onClickListener);
        }
    }

    @Override
    public void showProgress() {
        if (view != null) {
            view.showProgress();
        }
    }

    @Override
    public void hideProgress() {
        if (view != null) {
            view.hideProgress();
        }
    }

    @Override
    public void unAuthorized() {
        if (view != null) {
            view.unAuthorized();
        }
    }

    @Override
    public void onRegisterComplete() {
        if (view != null) {
            view.onRegisterComplete();
        }
    }

    @Override
    public void onLoginComplete() {
        if (view != null) {
            view.onLoginComplete();
        }
    }

    @Override
    public void onLogoutComplete() {
        if (view != null) {
            view.onLogoutComplete();
        }
    }

    @Override
    public void onEditProfileComplete() {
        if (view != null) {
            view.onEditProfileComplete();
        }
    }

    @Override
    public void onChangeProfilePictureComplete() {
        if (view != null) {
            view.onChangeProfilePictureComplete();
        }
    }
}
