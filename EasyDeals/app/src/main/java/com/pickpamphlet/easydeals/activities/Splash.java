package com.pickpamphlet.easydeals.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;

import com.pickpamphlet.easydeals.utilis.SaveSharedPreference;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Created by panshul on 15/9/17.
 */

public class Splash extends Activity {

        // launcher screen timer
        private static int SPLASH_TIME_OUT = 2500;
    private static String userid = "";


    Handler handler;
        Runnable runnable;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            printHashKey(Splash.this);
            userid = SaveSharedPreference.getUserID(Splash.this);

            handler = new Handler();
            runnable = new Runnable()
            {
                @Override
                public void run()
                {
                    if (userid != "") {
                        Intent i = new Intent(Splash.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        if (SaveSharedPreference.getIntro(Splash.this).equals("1")) {
                            Intent i = new Intent(Splash.this, LoginActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            Intent i = new Intent(Splash.this, Intro.class);    //Intro
                            startActivity(i);
                            finish();
                        }
                    }
               //     Intent home_activity=new Intent(Splash.this,LoginActivity.class);
                //    startActivity(home_activity);
                 //   finish();
                }
            };
            handler.postDelayed(runnable, SPLASH_TIME_OUT);
        }

        @Override
        public void onBackPressed() {
            super.onBackPressed();
            handler.removeCallbacks(runnable);
        }

    public  void printHashKey(Splash pContext) {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.pickpamphlet.easydeals",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }
    }

