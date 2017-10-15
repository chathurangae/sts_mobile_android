package app.sts.com.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import app.sts.com.model.User;

public class PreferenceManager {
    private static final String PREFERENCE_NAME = "Sts";
    private static final String USER_KEY = "User";

    private SharedPreferences preferences;

    public PreferenceManager(Context context) {
        this.preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public void putUser(User user) {
        this.putObject(USER_KEY, user);
    }

    public User getUser() {
        return this.getObject(USER_KEY, User.class);
    }

    @SuppressWarnings("unchecked")
    private <T> T getObject(String key, Class<T> type) {
        String json = this.preferences.getString(key, "");
        Object result = null;
        if (!json.isEmpty()) {
            Gson gson = new Gson();
            result = gson.fromJson(json, type);
        }
        return (T) result;
    }

    private void putObject(String key, Object object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.putString(key, json);
        editor.apply();
    }

    public void signOut() {
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.remove(USER_KEY);
        editor.apply();
    }
}
