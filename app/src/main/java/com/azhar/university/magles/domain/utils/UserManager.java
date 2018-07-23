package com.azhar.university.magles.domain.utils;

import android.content.SharedPreferences;

import com.azhar.university.magles.domain.models.Language;
import com.azhar.university.magles.domain.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by interactive on 7/23/18.
 */

public class UserManager {
    private static final String TAG = UserManager.class.getSimpleName();
    private volatile static User user;

    /**
     * Returns singleton class instance
     */
    public static User getCurrentUser(SharedPreferences preferences) {
        if (user == null) {
            synchronized (User.class) {
                if (user == null) {
                    buildUser(preferences);
                }
            }
        }
        return user;
    }

    private static void buildUser(SharedPreferences preferences) {
        if (preferences != null) {
            String json = preferences.getString(Constants.KEY_USER_LOGGED_OBJECT, null);
            user = new Gson().fromJson(json, new TypeToken<User>() {
            }.getType());
        }
    }

    public static User getUserDefault(SharedPreferences preferences, String username, String password) {
        User olderUser = getCurrentUser(preferences);
        user = new User(username, password);
        user.setLanguage((olderUser != null) ? olderUser.getLanguage() : Language.getDefaultLanguage());
        saveUser(preferences, user);
        return user;
    }

    public static void saveUser(SharedPreferences preferences, User member) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constants.KEY_USER_LOGGED_OBJECT, new Gson().toJson(member));
        editor.apply();
        buildUser(preferences);
    }

    public static void logout(SharedPreferences preferences) {
        user.setLogged(false);
        saveUser(preferences, user);
        user = null;
    }

    public static boolean isExistUserLoggedIn(SharedPreferences preferences) {
        return (getCurrentUser(preferences) != null);
    }
}
