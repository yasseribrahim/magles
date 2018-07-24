package com.azhar.university.magles.presentation.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.azhar.university.magles.R;
import com.azhar.university.magles.domain.views.UserView;
import com.azhar.university.magles.presentation.presenters.user.UserPresenter;
import com.azhar.university.magles.presentation.presenters.user.UserPresenterImp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class RegistrationActivity extends BaseActivity implements UserView {
    @BindView(R.id.registration_form)
    View registrationFormView;
    @BindView(R.id.full_name)
    AutoCompleteTextView name;
    @BindView(R.id.email)
    AutoCompleteTextView email;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.confirmation_password)
    EditText confirmationPassword;

    private UserPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ButterKnife.bind(this);

        presenter = new UserPresenterImp(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    protected View getSnackBarAnchorView() {
        return registrationFormView;
    }

    @OnClick(R.id.join_now_button)
    public void onJoinNowClicked() {
        joinNow();
    }

    /**
     * Attempts to sign in or register the account specified by the registration form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual registration attempt is made.
     */
    private void joinNow() {
        reset();

        // Store values at the time of the registration attempt.
        String name = this.name.getText().toString();
        String email = this.email.getText().toString();
        String password = this.password.getText().toString();
        String confirmationPassword = this.confirmationPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid full name.
        if (TextUtils.isEmpty(name)) {
            this.name.setError(getString(R.string.error_field_required));
            focusView = this.name;
            cancel = true;
        } else if (!isNameValid(name)) {
            this.name.setError(getString(R.string.error_invalid_full_name));
            focusView = this.name;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            this.email.setError(getString(R.string.error_field_required));
            focusView = this.email;
            cancel = true;
        } else if (!isEmailValid(email)) {
            this.email.setError(getString(R.string.error_invalid_email));
            focusView = this.email;
            cancel = true;
        }

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            this.password.setError(getString(R.string.error_invalid_password));
            focusView = this.password;
            cancel = true;
        }

        // Check for a valid confiramtion password, if the user entered one.
        // Check for a valid email address.
        if (TextUtils.isEmpty(confirmationPassword)) {
            this.confirmationPassword.setError(getString(R.string.error_field_required));
            focusView = this.confirmationPassword;
            cancel = true;
        } else if (!isConfirmationPasswordValid(password, confirmationPassword)) {
            this.confirmationPassword.setError(getString(R.string.error_invalid_confirmation_password));
            focusView = this.confirmationPassword;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt registration and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user registration attempt.
//            presenter.register(email, password, name);
        }
    }

    private void reset() {
        // Reset errors.
        email.setError(null);
        password.setError(null);
        name.setError(null);
        confirmationPassword.setError(null);
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    private boolean isConfirmationPasswordValid(String password, String confirmationPassword) {
        //TODO: Replace this with your own logic
        return password.equals(confirmationPassword);
    }

    private boolean isNameValid(String name) {
        //TODO: Replace this with your own logic
        return name.length() > 6;
    }

//    @Override
//    public void onRegisterComplete() {
//        startActivity(new Intent(this, LoginActivity.class));
//        finish();
//    }

    @Override
    public void onLoginComplete() {
    }

    @Override
    public void onLogoutComplete() {

    }

    @Override
    public void onEditProfileComplete() {

    }

    @Override
    public void onChangeProfilePictureComplete() {

    }

    @Override
    public void showConnectionError(View.OnClickListener onClickListener) {
        showConnectionSnackBar(onClickListener);
    }

    @Override
    public void showError(String message, View.OnClickListener onClickListener) {
        showRetrySnackBar(message, onClickListener);
    }

    @Override
    public void unAuthorized() {

    }
}

