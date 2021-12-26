package com.pickpamphlet.easydeals.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import com.pickpamphlet.easydeals.utilis.Apis;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by panshul on 28/9/17.
 */

public class ForgotPassword extends AppCompatActivity {

    EditText _phone;
    Button _forgotButton;
    TextView _loginLink;
    String phone;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        getSupportActionBar().hide();

        _phone = (EditText) findViewById(R.id.input_phone);
        _forgotButton = (Button) findViewById(R.id.btn_forgot);
        _loginLink = (TextView) findViewById(R.id.link_back);

        _loginLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                //        Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                //     startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });

        _forgotButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                phone = _phone.getText().toString();
                if (phone.equals(""))
                    _phone.setError("Enter Mobile Number");
                else
                    Forgrot();
            }
        });
    }



    public void Forgrot() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Apis.ForgetPassword,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("log", "" + response);

                        signupTrace(response);

               //         Dialogs.disDialog();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                //        Dialogs.disDialog();
                        Toast.makeText(ForgotPassword.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                })

        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
          //      map.put("action", "forgot_password");
                map.put("phone", phone);
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

    private void signupTrace(String response)  {
        try {
            JSONObject jsonObject = new JSONObject(response);
            String password = jsonObject.getString("passw");
                Log.e("log", "" + response);
                Toast.makeText(ForgotPassword.this, password, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ForgotPassword.this, LoginActivity.class));
                finish();

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("exp",e.toString());
        }
    }
}