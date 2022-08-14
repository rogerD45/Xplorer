package com.example.xplorer.the_one.homeUpload;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences userSession;
    static SharedPreferences.Editor editor;
    Context context;


    public static final String SESSION_USERSESSION = "userLoginSession";
    public static final String SESSION_REMEMBERME = "rememberMe";


    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_FULLNAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_DOB = "dob";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_PASSWORD = "pass";

    private static final String IS_REMEMBERME = "IsRememberMe";
    public static final String KEY_SESSIONPHONE = "phone";
    public static final String KEY_SESSIONPASSWORD = "pass";




    public SessionManager(Context _context,String sessionManager) {
        context = _context;
        userSession = _context.getSharedPreferences(sessionManager, Context.MODE_PRIVATE);
        editor = userSession.edit();


    }


    public static void createLoginSession(String name, String email, String gender, String dob, String phone, String pass) {

        editor.putBoolean(IS_LOGIN, true);


        editor.putString(KEY_FULLNAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_GENDER, gender);
        editor.putString(KEY_DOB, dob);
        editor.putString(KEY_PHONE, phone);
        editor.putString(KEY_PASSWORD, pass);


        editor.commit();
    }

    public HashMap<String, String> getUserDetailsFromSession() {

        HashMap<String, String> userData = new HashMap<String, String>();

        userData.put(KEY_FULLNAME, userSession.getString(KEY_FULLNAME, null));
        userData.put(KEY_EMAIL, userSession.getString(KEY_EMAIL, null));
        userData.put(KEY_GENDER, userSession.getString(KEY_GENDER, null));
        userData.put(KEY_DOB, userSession.getString(KEY_DOB, null));
        userData.put(KEY_PHONE, userSession.getString(KEY_PHONE, null));
        userData.put(KEY_PASSWORD, userSession.getString(KEY_PASSWORD, null));


        return userData;
    }

    public boolean checkLogin() {

        if (userSession.getBoolean(IS_LOGIN, false)) {

            return true;

        } else {
            return false;
        }

    }

    public void logoutUserFromSession() {
        editor.clear();
        editor.commit();

    }

    public static void createRememberMeSession(String phone, String pass) {

        editor.putBoolean(IS_REMEMBERME,true);

        editor.putString(KEY_SESSIONPHONE, phone);
        editor.putString(KEY_SESSIONPASSWORD, pass);

    }

    public HashMap<String, String> getRememberMeDetailsFromSession() {

        HashMap<String, String> userData = new HashMap<String, String>();

        userData.put(KEY_SESSIONPHONE, userSession.getString(KEY_SESSIONPHONE, null));
        userData.put(KEY_SESSIONPASSWORD, userSession.getString(KEY_SESSIONPASSWORD, null));

        return userData;
    }

    public boolean checkRememberMe() {

        if (userSession.getBoolean(IS_REMEMBERME, false)) {

            return true;

        } else {
            return false;
        }

    }


}
