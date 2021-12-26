package com.pickpamphlet.easydeals.utilis;

/**
 * Created by Win 7 on 10/1/2015.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class SaveSharedPreference {
    static final String PREF_COMPANY_NAME = "username";
    static final String PREF_LAST_NAME = "lastname";
    static final String PREF_USER_MOBILE = "mobile";
    static final String PREF_USER_EMAIL = "email";
    static final String PREF_USER_IMAGE = "image";
    static final String PREF_USER_AUTH = "place";
    static final String PREF_USER_ID = "id";
    static final String bal_star = "bal_star";
    static final String PREF_SSS = "ssn";
    static final String PREF_Facebook = "fb";
    static final String PREF_Twitter = "tw";
    static final String PREF_Check = "ch";
    static final String Birth = "birth";
    static final String Annversary = "Annversary";
    static final String Gender = "gender";
    static final String USER = "user";
    static final String MERCHANT = "merchant";
    static final String peryear = "peryear";
    static final String verified = "verified";
    static final String intro = "intro";
    static final String SHARED_PREF_NAME = "FCMSharedPref";
    static final String TOKEN = "token";

    //is_customer
    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUser(Context ctx, String user) {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(USER, user);
        editor.commit();
    }

    public static String getUser(Context ctx) {
        return getSharedPreferences(ctx).getString(USER, "");
    }

    public static void setMerchant(Context ctx, String merchanttype) {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(MERCHANT, merchanttype);
        editor.commit();
    }

    public static String getMerchant(Context ctx) {
        return getSharedPreferences(ctx).getString(MERCHANT, "");
    }

    public static void setCompanyName(Context ctx, String userName) {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_COMPANY_NAME, userName);
        editor.commit();
    }

    public static String getCompanyName(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_COMPANY_NAME, "");
    }

    public static void setLastName(Context ctx, String userName) {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_LAST_NAME, userName);
        editor.commit();
    }

    public static String getLastName(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_LAST_NAME, "");
    }

    public static void setMobile(Context ctx, String userName) {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_MOBILE, userName);
        editor.commit();
    }

    public static String getMobile(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_MOBILE, "");
    }

    public static void setUserID(Context ctx, String userid) {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_ID, userid);
        editor.commit();
    }

    public static String getUserID(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_ID, "");
    }


    public static void setUserEMAIL(Context ctx, String useremail) {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_EMAIL, useremail);
        editor.commit();
    }

    public static String getUserEMAIL(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_EMAIL, "");
    }

    public static void setUserIMAGE(Context ctx, String userimage) {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_IMAGE, userimage);
        editor.commit();
    }

    public static String getUserIMAGE(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_IMAGE, "");
    }


    public static void setaddress(Context ctx, String mobile) {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(bal_star, mobile);
        editor.commit();
    }

    public static String getaddress(Context ctx) {
        return getSharedPreferences(ctx).getString(bal_star, "");
    }

    public static void setOficeAdd(Context ctx, String place) {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_AUTH, place);
        editor.commit();
    }

    public static String getOficeAdd(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_AUTH, "");
    }

    public static void setOfficeEmail(Context ctx, String place) {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_SSS, place);
        editor.commit();
    }

    public static String getOfficeEmail(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_SSS, "");
    }

    public static void setOtp(Context ctx, String place) {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_Facebook, place);
        editor.commit();
    }

    public static String getOtp(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_Facebook, "");
    }

    public static void setofficePhone(Context ctx, String place) {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_Twitter, place);
        editor.commit();
    }

    public static String getofficePhone(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_Twitter, "");
    }

    public static void setCover(Context ctx, String place) {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_Check, place);
        editor.commit();
    }

    public static String getCover(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_Check, "");
    }

    public static void setBirth(Context ctx, String place) {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(Birth, place);
        editor.commit();
    }

    public static String getBirth(Context ctx) {
        return getSharedPreferences(ctx).getString(Birth, "");
    }

    public static void setPeryear(Context ctx, String place) {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(peryear, place);
        editor.commit();
    }

    public static String getPeryear(Context ctx) {
        return getSharedPreferences(ctx).getString(peryear, "");
    }

    public static void setcat(Context ctx, String place) {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(verified, place);
        editor.commit();
    }

    public static String getcat(Context ctx) {
        return getSharedPreferences(ctx).getString(verified, "");
    }

    public static void setsub(Context ctx, String place) {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(Gender, place);
        editor.commit();
    }

    public static String getsub(Context ctx) {
        return getSharedPreferences(ctx).getString(Gender, "");
    }

    public static void setIntro(Context ctx, String place) {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(intro, place);
        editor.commit();
    }

    public static String getIntro(Context ctx) {
        return getSharedPreferences(ctx).getString(intro, "");
    }

    public static void setfollow(Context ctx, String place) {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(Annversary, place);
        editor.commit();
    }

    public static String getfollow(Context ctx) {
        return getSharedPreferences(ctx).getString(Annversary, "");
    }

    public static void setemai_View(Context ctx, String place) {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString("emailview", place);
        editor.commit();
    }

    public static String getemai_View(Context ctx) {
        return getSharedPreferences(ctx).getString("emailview", "");
    }

    public static void setphon_View(Context ctx, String place) {
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString("phone", place);
        editor.commit();
    }

    public static String getphon_View(Context ctx) {
        return getSharedPreferences(ctx).getString("phone", "");
    }


    //this method will save the device token to shared preferences
    public static boolean saveDeviceToken(Context ctx, String token){
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(TOKEN, token);
        editor.apply();
        return true;
    }

    //this method will fetch the device token from shared preferences
    public static String getDeviceToken(Context ctx){
        return  getSharedPreferences(ctx).getString(TOKEN, "");
    }

}