package com.example.dangdinhtien_ps10163_asm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Main_login extends AppCompatActivity {
    TextView txtsignup;
    Button btnlogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        txtsignup = findViewById(R.id.txtSignup);
        btnlogin = findViewById(R.id.btnLogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Main_login.this, "Login Thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Main_login.this, MainActivity.class);
                startActivity(intent);
            }
        });
        txtsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Main_login.this, "Register", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Main_login.this, Main_register.class);
                startActivity(intent);
            }
        });
    }
}
