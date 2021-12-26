package com.pickpamphlet.easydeals.activities;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.pickpamphlet.easydeals.database.DatabaseAccess;
import com.pickpamphlet.easydeals.utilis.Apis;
import com.pickpamphlet.easydeals.utilis.SaveSharedPreference;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by panshul on 28/9/17.
 */

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    EditText _phone, _password;
    Button _loginButton;
    TextView _signupLink, _forgotLink, _termsLink;
    String phone, password;
    DatabaseAccess databaseAccess;


    public static final int MY_LOCATION_REQUEST_CODE = 10;

    public static String[] permissions = new String[]{
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_SMS,
            Manifest.permission.SEND_SMS,
            Manifest.permission.RECEIVE_SMS,
            android.Manifest.permission.ACCESS_NETWORK_STATE,
            android.Manifest.permission.CALL_PHONE

            //     android.Manifest.permission.ACCESS_COARSE_LOCATION,
       //     android.Manifest.permission.ACCESS_FINE_LOCATION,
            //         android.Manifest.permission.CAMERA,

    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        getSupportActionBar().hide();


        _phone = (EditText) findViewById(R.id.input_phone);
        _password = (EditText) findViewById(R.id.input_password);
        _loginButton = (Button) findViewById(R.id.btn_login);
        _signupLink = (TextView) findViewById(R.id.link_signup);
        _forgotLink = (TextView) findViewById(R.id.link_forgot);
        _termsLink = (TextView) findViewById(R.id.terms);

        checkPermissions(LoginActivity.this);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), Trial.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        _forgotLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), ForgotPassword.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        _termsLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), TermsnConditions.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
       }

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        phone = _phone.getText().toString();
        password = _password.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }


    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        phone = _phone.getText().toString();
        password = _password.getText().toString();

        if (phone.isEmpty() || !Patterns.PHONE.matcher(phone).matches() || phone.length() !=10) {
            _phone.setError("Enter a valid phone number");     //!android.util.Patterns.EMAIL_ADDRESS
            valid = false;
        } else {
            _phone.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 16) {
            _password.setError("Between 4 and 16 alphanumeric characters");
            valid = false;
        } else {
            _password.setError(null);
        }

        return valid;
    }

    /*
    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        startActivity( new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    */
    public void onLoginSuccess() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Apis.Login,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("log", "response");
                        LoginTrace(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("contact_no", phone);
                map.put("password", password);
                Log.e("map_values",""+map);
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

    private void LoginTrace(String response )  {
        try {
            Log.e("response", "" + response);
            JSONArray jsonarray = new JSONArray(response);

            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonObject = jsonarray.getJSONObject(i);

                if (jsonObject.getString("status").equals("A")) {
                    SaveSharedPreference.setUserID(LoginActivity.this, jsonObject.getString("id"));
                    SaveSharedPreference.setCompanyName(LoginActivity.this, jsonObject.getString("company_name"));
                    SaveSharedPreference.setUserEMAIL(LoginActivity.this, jsonObject.getString("email"));
                    SaveSharedPreference.setMobile(LoginActivity.this, jsonObject.getString("contact_no"));

                    //   Toast.makeText(LoginActivity.this,mesg,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                } else if (jsonObject.getString("status").equals(" ")) {
                //    String mesg = jsonObject.getString("msg");
                 //   Toast.makeText(LoginActivity.this, mesg, Toast.LENGTH_SHORT).show();
                }
            }
        }
        catch (Exception e){
            Log.e("error",""+e.toString());
        }
    }

    public static boolean checkPermissions(Context context) {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(context, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        Log.e("log", "" + listPermissionsNeeded);
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions((Activity) context, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), MY_LOCATION_REQUEST_CODE);
            return false;
        }
        return true;
    }

}

