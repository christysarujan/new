package com.example.catwalkm;


import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.android.volley.Request;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




public class SignUp extends AppCompatActivity {


    EditText fname, lname, uemail;
    TextInputEditText upassword;

    Button signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        uemail = (EditText) findViewById(R.id.emailRegistration);

        upassword = (TextInputEditText) findViewById(R.id.passwordRegistration);

        signup = (Button) findViewById(R.id.buttonRegistration);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetUsers();
            }
        });




    }


    public void GetUsers() {
        final String name = fname.getText().toString().trim();
        final String username = lname.getText().toString().trim();
        final String email = uemail.getText().toString().trim();
        final String password = upassword.getEditableText().toString().trim();



        if (name.isEmpty()) {
            fname.setError("Please fill this field");
            fname.requestFocus();
            return;
        }
        if (username.isEmpty()) {
            lname.setError("Please fill this Field");
            lname.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            uemail.setError("Please fill this field");
            uemail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            uemail.setError("Please enter valid email address");
            uemail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            upassword.setError("please fill this field");
            upassword.requestFocus();
            return;
        }
        if (password.length() < 8) {
            upassword.setError("Password length  is not enough");
            upassword.requestFocus();
            return;
        }


        String api = "http:/10.10.5.138:8080/api/auth/signup";



        final JSONObject request = new JSONObject();
        try {
            request.put("name",name);
            request.put("username",username);
            request.put("email",email);
            request.put("role","user");
            request.put("password",password);


        }catch (Exception e) {
            e.printStackTrace();
        }





        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, api, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    boolean error=response.getBoolean("Error");
                    String message=response.getString("Message");

                    if(!error) {
                        JSONArray user=response.getJSONArray("User");
                        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                    }



                }catch(JSONException e) {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();


                }



            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

    }
});
        User.getInstance().addToRequestQueue(objectRequest);










        };

}








