package com.azhar.university.magles.presentation.presenters.user;

import android.view.View;

import com.azhar.university.magles.MaglesApplication;
import com.azhar.university.magles.domain.controller.Controller;
import com.azhar.university.magles.domain.interactors.user.UserInteractor;
import com.azhar.university.magles.domain.interactors.user.UserInteractorImp;
import com.azhar.university.magles.domain.views.UserView;

import java.io.File;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public class UserPresenterImp implements UserPresenter, UserInteractor.UserCallbackStates {
    @Inject
    protected Retrofit retrofit;

    private UserView view;
    private UserInteractor interactor;

    public UserPresenterImp(UserView view) {
        MaglesApplication.getComponent().inject(this);
        this.view = view;
        this.interactor = new UserInteractorImp(retrofit.create(Controller.UserController.class), this);
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
    public String getErrorMessage(Throwable throwable) {
        if (view != null) {
            view.getErrorMessage(throwable);
        }
        return null;
    }

    @Override
    public void login() {
        interactor.login();
    }

    @Override
    public void logout() {
        interactor.logout();
    }

    @Override
    public void editProfile(String fullName) {
        interactor.editProfile(fullName);
    }

    @Override
    public void changeProfilePicture(File file) {
        interactor.changeProfilePicture(file);
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
