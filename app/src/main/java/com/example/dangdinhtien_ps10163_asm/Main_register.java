package com.example.dangdinhtien_ps10163_asm;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Main_register extends AppCompatActivity {
    Button btnCancel, btnRgister;
    EditText edtName, edtEmail, edtPass;

    String registerUrl = "http://192.168.43.214/assignment/index.php";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        btnCancel = findViewById(R.id.btnCancel);
        btnRgister = findViewById(R.id.btnRigister);
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPass);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnRgister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringRequest stringrequest = new StringRequest(
                        Request.Method.POST, registerUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        xulyRegister(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(Main_register.this, "Volley error", Toast.LENGTH_SHORT).show();
                        Log.d("error",error.toString());

                    }
                }
                ) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> param=new HashMap<String,String>();

                        param.put("name",edtName.getText().toString());
                        param.put("email",edtEmail.getText().toString());
                        param.put("password",edtPass.getText().toString());
                        param.put("tag","register");

                        return param;
                    }
                };
                Volley.newRequestQueue(Main_register.this).add(stringrequest);

            }
        });
    }
    private void xulyRegister(String response) {

        String thanhcong="";
        try {
            JSONObject jsonobject=new JSONObject(response);
            thanhcong=jsonobject.getString("thanhcong");

            //doc tat ca du lieu tu json bo vao ArrayList
            if(Integer.parseInt(thanhcong)==1)//thanh cong
            {
                Toast.makeText(this, "Sign Up in successfully!", Toast.LENGTH_SHORT).show();
                finish();
            }
            else //that bai
            {
                Toast.makeText(this, "Sign Up unsuccessfully!", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
