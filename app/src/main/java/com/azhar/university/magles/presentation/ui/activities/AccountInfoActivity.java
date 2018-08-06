package com.azhar.university.magles.presentation.ui.activities;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;

import com.azhar.university.magles.R;
import com.azhar.university.magles.domain.models.User;
import com.azhar.university.magles.domain.utils.UserManager;
import com.azhar.university.magles.domain.views.UserView;
import com.azhar.university.magles.presentation.presenters.user.UserPresenter;
import com.azhar.university.magles.presentation.presenters.user.UserPresenterImp;
import com.azhar.university.magles.presentation.ui.utils.DatesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A login screen that offers login via email/password.
 */
public class AccountInfoActivity extends BaseActivity implements UserView {
    @BindView(R.id.account_info_form)
    View accountInfoFormView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.full_name)
    AutoCompleteTextView name;
    @BindView(R.id.email)
    AutoCompleteTextView email;
    @BindView(R.id.phone)
    AutoCompleteTextView phone;
    @BindView(R.id.address)
    AutoCompleteTextView address;
    @BindView(R.id.national_id)
    AutoCompleteTextView nationalId;
    @BindView(R.id.birth_date)
    AutoCompleteTextView birthDate;
    @BindView(R.id.note)
    AutoCompleteTextView note;
    @BindView(R.id.department)
    AutoCompleteTextView department;


    private UserPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);

        ButterKnife.bind(this);

        presenter = new UserPresenterImp(this);

        setupUI();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    private void setupUI() {
        setupSupportedActionBarWithHome(toolbar);
        setActionBarTitle(R.string.title_edit_profile);

        User user = UserManager.getInstance().getCurrentUser();
        name.setText(user.getName());
        email.setText(user.getEmail());
        phone.setText(user.getPhone());
        address.setText(user.getAddress());
        nationalId.setText(user.getNationalId());
        birthDate.setText(DatesUtils.formatDateOnly(user.getBirthDate()));
        department.setText(user.getDepartment().getName());
        note.setText(user.getNote());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_done, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_done:
                hideKeyboard();
                if (isAccountInfoChange()) {
                    editProfile();
                } else {
                    finish();
                }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected View getSnackBarAnchorView() {
        return accountInfoFormView;
    }

    /**
     * Attempts to sign in or register the account specified by the account_info form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual account_info attempt is made.
     */
    private void editProfile() {
        reset();

        // Store values at the time of the account_info attempt.
        String name = this.name.getText().toString();

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

        if (cancel) {
            // There was an error; don't attempt account_info and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user account_info attempt.
            presenter.editProfile(name);
        }
    }

    private void reset() {
        name.setError(null);
    }

    private boolean isAccountInfoChange() {
        User user = UserManager.getInstance().getCurrentUser();
        if (!user.getName().equals(this.name.getText().toString())) {
            return true;
        }
        return false;
    }

    private boolean isNameValid(String name) {
        //TODO: Replace this with your own logic
        return name.length() > 6;
    }

    @Override
    public void onLoginComplete() {
    }

    @Override
    public void onLogoutComplete() {
    }

    @Override
    public void onChangeProfilePictureComplete() {
    }

    @Override
    public void onEditProfileComplete() {
        showInfoSnackBar(R.string.message_edit_profile_complete);
        finish();
    }

    @Override
    public void showConnectionError(View.OnClickListener onClickListener) {
        showConnectionSnackBar(onClickListener);
    }

    @Override
    public void showError(String message, View.OnClickListener onClickListener) {
        showRetrySnackBar(message, onClickListener);
    }
}

