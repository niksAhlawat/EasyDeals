package com.pickpamphlet.easydeals.activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pickpamphlet.easydeals.R;
import com.pickpamphlet.easydeals.utilis.Apis;
import com.pickpamphlet.easydeals.utilis.SaveSharedPreference;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Activity_OTP extends Activity implements View.OnClickListener {

    EditText ed1, ed2, ed3, ed4;
    ProgressBar progressBar;
    private static final int PROGRESS = 1;
    int progressStatus = 0;
    private Handler handler = new Handler();
    TextView txttimer, txtleft, txt_resend;
    static int value = 60;
    Button confirm;
    String sname, semail, spassword, sphone, sdob, otp;
    String tempOtp = "";
    private int count = 0;
    CountDownTimer downTimer;
    BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_layout);

        init();


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            sname = getIntent().getExtras().getString("name");
            semail = getIntent().getExtras().getString("email");
            spassword = getIntent().getExtras().getString("pass");
            sphone = getIntent().getExtras().getString("mobile");
        }

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equalsIgnoreCase("otp")) {
                    final String message = intent.getStringExtra("message").replace(" ", "");
                    //Do whatever you want with the code here
                    Log.e("msg", message);

                    ed1.setText("" + message.charAt(0));
                    ed2.setText("" + message.charAt(1));
                    ed3.setText("" + message.charAt(2));
                    ed4.setText("" + message.charAt(3));
                }
            }
        };

    }


    @Override
    public void onResume() {
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("otp"));
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    public void doSignup() {
      //  Dialogs.showProDialog(Activity_OTP.this, "Loading");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Apis.Base,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("log", "" + response);
                        try {
                            signupTrace(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
               //         Dialogs.disDialog();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
               //         Dialogs.disDialog();
                        Toast.makeText(Activity_OTP.this, "" + error.networkResponse.statusCode, Toast.LENGTH_LONG).show();
                    }
                })

        {

            @Override
            final protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("action", "user_register");
                map.put("first_name", sname);
                map.put("email", semail);
                map.put("password", spassword);
                map.put("registeredVia", "mobile");
                map.put("mobile", sphone);
                Log.e("mapvalue", "" + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.getCache().clear();
    }


    private void signupTrace(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        if (jsonObject.getString("statusCode").equals("1")) {
            Log.e("log", "" + response);
            String mesg = jsonObject.getString("msg");
            SaveSharedPreference.setUserID(Activity_OTP.this, jsonObject.getJSONObject("user_deatils").getString("user_id"));
            Toast.makeText(Activity_OTP.this, mesg, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Activity_OTP.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.finish();
            downTimer.cancel();
        } else if (jsonObject.getString("statusCode").equals("0")) {
        //    Dialogs.showDialog(Activity_OTP.this, jsonObject.getString("msg"));
        } else {
  //          Dialogs.showDialog(Activity_OTP.this, "Server Failed");
        }
    }

    @Override
    public void onBackPressed() {

        downTimer.cancel();
        finish();
    }

    void init() {
        confirm = (Button) findViewById(R.id.btn_confirm);
        confirm.setOnClickListener(this);
        ed1 = (EditText) findViewById(R.id.ed1);
        ed2 = (EditText) findViewById(R.id.ed2);
        ed3 = (EditText) findViewById(R.id.ed3);
        ed4 = (EditText) findViewById(R.id.ed4);
        txtleft = (TextView) findViewById(R.id.txt_left);
        txttimer = (TextView) findViewById(R.id.txt_timer);
        txt_resend = (TextView) findViewById(R.id.txt_resend);

        txt_resend.setVisibility(View.INVISIBLE);
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.circular_progress);
        progressBar = (ProgressBar) findViewById(R.id.pro_bar);
        progressBar.setProgress(0);
        progressBar.setMax(120);
        progressBar.setSecondaryProgress(120);
        progressBar.setProgressDrawable(drawable);


        downTimer = new CountDownTimer(120000, 1000) {

            public void onTick(long millisUntilFinished) {
                progressBar.setProgress(120 - (int) millisUntilFinished / 1000);
                txttimer.setText("" + millisUntilFinished / 1000);
                Log.e("LOg time", "" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                txtleft.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                txttimer.setVisibility(View.INVISIBLE);
                txttimer.setText("Time Out ! Resend Otp ?");
                txt_resend.setVisibility(View.VISIBLE);
                downTimer.cancel();
            }

        }.start();


        txttimer.setOnClickListener(this);
        txt_resend.setOnClickListener(this);
        ed1.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (ed1.getText().toString().length() == 1)     //size as per your requirement
                {
                    ed2.requestFocus();

                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void afterTextChanged(Editable s) {
            }

        });

        ed2.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (ed2.getText().toString().length() == 1)     //size as per your requirement
                {
                    ed3.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

        });
        ed3.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (ed3.getText().toString().length() == 1)     //size as per your requirement
                {
                    ed4.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

        });
        ed4.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (ed4.getText().toString().length() == 1)     //size as per your requirement
                {
                    // ed3.requestFocus();
       //             Dialogs.hideSoftKeyboard(Activity_OTP.this, ed4);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void afterTextChanged(Editable s) {

            }

        });
    }


    @Override
    public void onClick(View view) {
        if (view == confirm) {
            otp = ed1.getText().toString() + ed2.getText().toString() + ed3.getText().toString() + ed4.getText().toString();
            if (otp.equals(SaveSharedPreference.getOtp(Activity_OTP.this)))
                doSignup();
            else {
      //          Dialogs.showCenterToast(getApplicationContext(), "Worng OTP");
        }
        }
        else if (view == txt_resend) {
            if (count == 1) {
                Toast.makeText(Activity_OTP.this, "You have tried more than 1 times,please check your network", Toast.LENGTH_LONG).show();
            } else {
                count++;
                doSignup1();
            }
        } else if (view == txttimer) {

        }
    }

    public void doSignup1() {
 //       Dialogs.showProDialog(Activity_OTP.this, "Loading");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Apis.Base,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("log", "" + response);
                        try {
                            signupTrace1(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
     //                   Dialogs.disDialog();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
     //                   Dialogs.disDialog();
                        Toast.makeText(Activity_OTP.this, "" + error.networkResponse.statusCode, Toast.LENGTH_LONG).show();
                    }
                })

        {

            @Override
            final protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("action", "send_otp");
                map.put("registeredVia", "mobile");
                map.put("mobile", sphone);
                map.put("email", semail);
                Log.e("mapvalue", "" + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.getCache().clear();

    }

    private void signupTrace1(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        if (jsonObject.getString("statusCode").equals("1")) {
            Log.e("log", "" + response);
            String otp = jsonObject.getString("otp_no");
            SaveSharedPreference.setOtp(Activity_OTP.this, jsonObject.getString("otp_no"));
            //Toast.makeText(Activity_OTP.this, otp, Toast.LENGTH_SHORT).show();
            init();
            txt_resend.setVisibility(View.INVISIBLE);
        } else if (jsonObject.getString("statusCode").equals("0")) {
   //         Dialogs.showDialog(Activity_OTP.this, jsonObject.getString("msg"));
        } else {
 //           Dialogs.showDialog(Activity_OTP.this, "Server Failed");
        }
    }
}
