package com.example.catwalkm;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    Button signup,signin;
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signin = (Button) findViewById(R.id.buttonSignin);
        signup = (Button) findViewById(R.id.buttonSignup);
        username = (EditText) findViewById(R.id.unameLogin);
        password = (EditText) findViewById(R.id.passwordLogin);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });
        signup.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        }));

    }








        public void register(){

            final String uusername=username.getText().toString().trim();
            final String upassword=password.getText().toString().trim();

            if(uusername.isEmpty()){
                username.setError("Please fill this field");
                username.requestFocus();
                return;
            }
            if(upassword.isEmpty()) {
                password.setError("Please fill this field");
                password.requestFocus();
                return;
            }
            if(upassword.length()<8){
                password.setError("Password length is not enough");
                password.requestFocus();
                return;
            }


            String api = "http://192.168.8.102:8096/api/auth/signin";

            JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, api, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.e("Rest Response", response.toString());
                    Toast.makeText( MainActivity.this, "Rest Response\", response.toString", Toast.LENGTH_SHORT).show();

                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Rest Response", error.toString());
                            Toast.makeText(MainActivity.this, "Rest Response\", response.toString", Toast.LENGTH_SHORT).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> data = new HashMap<String, String>();
                    data.put("username", uusername);
                    data.put("password",upassword);
                    return data;
                }


            };
            RequestQueue requestQueue = (RequestQueue) Volley.newRequestQueue(this);
            requestQueue.add(objectRequest);
        }




}









