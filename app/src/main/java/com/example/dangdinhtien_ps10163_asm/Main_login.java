package com.example.dangdinhtien_ps10163_asm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Main_login extends AppCompatActivity {
    TextView txtsignup;
    Button btnlogin;
    EditText edtEmail, edtPass;

    String loginUrl = "http://192.168.43.214/assignment/index.php";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        txtsignup = findViewById(R.id.txtSignup);
        btnlogin = findViewById(R.id.btnLogin);
        edtEmail = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPass);

        txtsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Main_login.this, "Sign Up", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Main_login.this, Main_register.class);
                startActivity(intent);
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringrequest = new StringRequest(
                        Request.Method.POST, loginUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        xulyLogin(response);


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(Main_login.this, "Volley error", Toast.LENGTH_SHORT).show();
                        Log.d("error",error.toString());

                    }
                }
                ) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> param=new HashMap<String,String>();


                        param.put("email",edtEmail.getText().toString());
                        param.put("password",edtPass.getText().toString());
                        param.put("tag","login");

                        return param;
                    }
                };
                Volley.newRequestQueue(Main_login.this).add(stringrequest);
            }
        });
    }
    private void xulyLogin(String response) {
        String thanhcong="";
        String name = "";
        try {
            JSONObject jsonobject=new JSONObject(response);
            thanhcong=jsonobject.getString("thanhcong");

            //doc tat ca du lieu tu json bo vao ArrayList
            if(Integer.parseInt(thanhcong)==1)//thanh cong
            {
                JSONObject user = jsonobject.getJSONObject("user");
                name = user.getString("name");
                Toast.makeText(this, "Logged in successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Main_login.this, MainActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
            else //that bai
            {
                Log.d("login","LoginFail");
                Toast.makeText(this, "Login unsuccessfully!", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
