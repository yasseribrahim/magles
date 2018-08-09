package com.azhar.university.magles.domain.interactors.user;

import android.view.View;

import com.azhar.university.magles.domain.controller.Controller;
import com.azhar.university.magles.domain.interactors.BaseInteractor;
import com.azhar.university.magles.domain.models.User;
import com.azhar.university.magles.domain.utils.UserManager;

import javax.net.ssl.HttpsURLConnection;

import io.reactivex.Observable;
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
        prepare(Observable.just(Boolean.TRUE), new LogoutObserver(callback));
    }

    @Override
    public void editProfile(final User user) {
        callback.showProgress();
        User oldUser = UserManager.getInstance().getCurrentUser();
        prepare(controller.edit(oldUser.getAuthorization(), user), new EditProfileObserver(callback, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfile(user);
            }
        }));
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
                    return;
                }
            }
            String message = callback.getErrorMessage(e);
            callback.failure(message != null ? message : e.getMessage(), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login();
                }
            });
        }
    }

    private final class LogoutObserver extends BaseObserver<Boolean> {

        public LogoutObserver(CallbackStates callback) {
            super(callback);
        }

        @Override
        public void onNext(Boolean result) {
            UserManager.getInstance().logout();
            callback.onLogoutComplete();
            super.onNext(result);
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            String message = callback.getErrorMessage(e);
            callback.failure(message != null ? message : e.getMessage(), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    logout();
                }
            });
        }
    }

    private final class EditProfileObserver extends BaseObserver<User> {
        private View.OnClickListener listener;

        public EditProfileObserver(CallbackStates callback, View.OnClickListener listener) {
            super(callback);
            this.listener = listener;
        }

        @Override
        public void onNext(User user) {
            UserManager.getInstance().prepareAndStoreCurrentUser(user);
            callback.onEditProfileComplete();
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
                    return;
                }
            }
            String message = callback.getErrorMessage(e);
            callback.failure(message != null ? message : e.getMessage(), listener);
        }
    }
}
