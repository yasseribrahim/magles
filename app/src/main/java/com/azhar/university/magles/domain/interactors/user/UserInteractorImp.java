package com.azhar.university.magles.domain.interactors.user;

import android.view.View;

import com.azhar.university.magles.domain.controller.Controller;
import com.azhar.university.magles.domain.interactors.BaseInteractor;
import com.azhar.university.magles.domain.models.User;
import com.azhar.university.magles.domain.utils.UserManager;

import java.io.File;

import javax.net.ssl.HttpsURLConnection;

import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public class UserInteractorImp extends BaseInteractor implements UserInteractor {
    private final Controller.UserController controller;
    private final UserCallbackStates callback;

    public UserInteractorImp(Controller.UserController controller, UserCallbackStates callback) {
        this.controller = controller;
        this.callback = callback;
    }

    @Override
    public void login() {
        callback.showProgress();
        User user = UserManager.getInstance().getCurrentUser();
        prepare(controller.login(user.getAuthorization()), new LoginObserver(callback));
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
            UserManager.getInstance().prepareAndStoreCurrentUser(user);
            callback.onLoginComplete();
            super.onNext(user);
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            if (e instanceof HttpException) {
                if (((HttpException) e).code() == HttpsURLConnection.HTTP_UNAUTHORIZED) {
                    callback.unAuthorized();
                }
            }
            callback.failure(e.getMessage(), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login();
                }
            });
        }
    }
}
