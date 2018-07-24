package com.azhar.university.magles.domain.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.azhar.university.magles.MaglesApplication;
import com.azhar.university.magles.domain.models.Language;
import com.azhar.university.magles.domain.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by interactive on 7/23/18.
 */

public class UserManager {
    private static final UserManager MANAGER = new UserManager();
    private final SharedPreferences PREFERENCES;
    private volatile User user;

    private UserManager() {
        PREFERENCES = PreferenceManager.getDefaultSharedPreferences(MaglesApplication.getApplication());
        buildUser();
    }

    public static UserManager getInstance() {
        return MANAGER;
    }

    /**
     * Returns singleton class instance
     */
    public User getCurrentUser() {
        if (user == null) {
            synchronized (User.class) {
                if (user == null) {
                    buildUser();
                }
            }
        }
        return user;
    }

    private void buildUser() {
        if (PREFERENCES != null) {
            String json = PREFERENCES.getString(Constants.KEY_USER_LOGGED_OBJECT, null);
            user = new Gson().fromJson(json, new TypeToken<User>() {
            }.getType());
        }
    }

    public User initializeCurrentUser(String username, String password) {
        User olderUser = getCurrentUser();
        user = new User(username, password);
        user.setLanguage((olderUser != null) ? olderUser.getLanguage() : Language.getDefaultLanguage());
        saveUser(user);
        return user;
    }

    public void saveUser(User user) {
        SharedPreferences.Editor editor = PREFERENCES.edit();
        editor.putString(Constants.KEY_USER_LOGGED_OBJECT, new Gson().toJson(user));
        editor.apply();
        buildUser();
    }

    public void prepareAndStoreCurrentUser(User user) {
        user.setPassword(getCurrentUser().getPassword());
        user.setAuthorization(getCurrentUser().getAuthorization());
        user.setLanguage(getCurrentUser().getLanguage());
        user.setLogged(true);
        saveUser(user);
    }

    public void logout() {
        user.setLogged(false);
        saveUser(user);
        user = null;
    }

    public boolean isExistUserLoggedIn() {
        return (getCurrentUser() != null) && user.isLogged() && user.isEnabled();
    }
}
