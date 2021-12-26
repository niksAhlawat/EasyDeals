package com.pickpamphlet.easydeals.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
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
import com.pickpamphlet.easydeals.utilis.Apis;
import com.pickpamphlet.easydeals.utilis.InternetStatus;
import com.pickpamphlet.easydeals.utilis.SaveSharedPreference;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by panshul on 3/10/17.
 */

public class Change_Password extends AppCompatActivity implements View.OnClickListener {

    EditText oldpas,newpas,conpas;
    Button confirm;
    String soldpas,snewpas,sconpas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);
        oldpas=(EditText)findViewById(R.id.ed_oldpas);
        newpas=(EditText)findViewById(R.id.ed_newpas);
        conpas=(EditText)findViewById(R.id.ed_conpas);
        confirm=(Button)findViewById(R.id.btn_submit);
        confirm.setOnClickListener(this);

        conpas.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (InternetStatus.isConnectingToInternet(Change_Password.this)) {
                        if (valid()) {
                            changePassword();
                        }
                    }
                    handled = true;
                }
                return handled;
            }
        });

    }

    public void changePassword() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Apis.ResetPassword,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("log", "" + response);
                        try {
                            signupTrace(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Change_Password.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                })

        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("contact_no", SaveSharedPreference.getMobile(Change_Password.this));
                map.put("old_pass",soldpas);
                map.put("new_pass", snewpas);
                map.put("re_pass", sconpas);
                Log.e("mapvalue",""+map);
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

    private void signupTrace(String response ) throws JSONException {
        JSONObject jsonObject= new JSONObject(response);
        if(jsonObject.getString("status").equals("1")){
            Log.e("log", "" + response);
            String mesg= jsonObject.getString("msg");
            Log.e("msg",""+mesg);
            Toast.makeText(Change_Password.this,mesg, Toast.LENGTH_SHORT).show();
            finish();
        }
        else if (jsonObject.getString("status").equals("0")){
            String mesg= jsonObject.getString("msg");
            Toast.makeText(Change_Password.this,mesg, Toast.LENGTH_SHORT).show();
        }
        else {
            Snackbar.make(findViewById(android.R.id.content), " Server failed", Snackbar.LENGTH_LONG)
                    .setAction("Undo", null)
                    .setActionTextColor(Color.RED)
                    .show();
        }
    }

    private boolean valid() {
        soldpas = oldpas.getText().toString();
        snewpas = newpas.getText().toString();
        sconpas = conpas.getText().toString();

        if (soldpas.equals("")) {
            oldpas.setError("Enter Old Password");
            return false;
        }
        else if (snewpas.equals("")) {
            newpas.setError("Enter new password");
            return false;
        }else if (sconpas.equals("")) {
            conpas.setError("Please confirm password");
            return false;
        }else if (!snewpas.equals(sconpas)) {
            conpas.setError("password mismatch");
            return false;
        }
        else if (snewpas.length()<6) {
            newpas.setError("password length should be greater than six");
            return false;
        }else if (sconpas.length()<6) {
            conpas.setError("password length should be greater than six");
            return false;
        } else {
            return true;
        }
    }


    @Override
    public void onClick(View v) {

        if (v==confirm){
            if (InternetStatus.isConnectingToInternet(Change_Password.this)) {
                if (valid()) {
                    changePassword();
                }
            } else {
                Snackbar.make(findViewById(android.R.id.content), "No Internet Access", Snackbar.LENGTH_LONG)
                        .setAction("Undo", null)
                        .setActionTextColor(Color.RED)
                        .show();
            }
        }
    }
}

