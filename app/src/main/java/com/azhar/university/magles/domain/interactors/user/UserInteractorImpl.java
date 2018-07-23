package com.azhar.university.magles.domain.interactors.user;

import android.view.View;

import com.azhar.university.magles.domain.controller.Controller;
import com.azhar.university.magles.domain.interactors.BaseInteractor;
import com.azhar.university.magles.domain.models.User;

import java.io.File;

import io.reactivex.disposables.Disposable;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public class UserInteractorImpl extends BaseInteractor implements UserInteractor {
    private final Controller.UserController controller;
    private final UserCallbackStates callback;

    public UserInteractorImpl(Controller.UserController controller, UserCallbackStates callback) {
        this.controller = controller;
        this.callback = callback;
    }

    @Override
    public void login() {
        callback.showProgress();
        prepare(controller.login(), new LoginObserver(callback));
    }

    @Override
    public void logout() {
        callback.showProgress();
    }

    @Override
    public void editProfile(String fullName) {
        callback.showProgress();
    }

    @Override
    public void changeProfilePicture(final File file) {
        callback.showProgress();
    }

    private final class LoginObserver extends BaseObserver<User> {

        public LoginObserver(CallbackStates callback) {
            super(callback);
        }

        @Override
        public void onNext(User user) {
            callback.onLoginComplete(user);
            super.onNext(user);
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            callback.failure(e.getMessage(), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login();
                }
            });
        }
    }
}
