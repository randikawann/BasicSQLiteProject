package com.example.randikawann.addretireveit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity2 extends AppCompatActivity {
    EditText etUserName;
    EditText etEmail;
    Button btLogIn;
    TextView tvGoReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        tvGoReg = findViewById(R.id.tvGoReg);
        btLogIn = findViewById(R.id.btLogIn);

        btLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goProfileIntent = new Intent(LoginActivity2.this, ProfileActivity.class);
                startActivity(goProfileIntent);
            }
        });

        tvGoReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goRegIntent = new Intent(LoginActivity2.this, RegisterActivity.class);
                startActivity(goRegIntent);
            }
        });
    }
}
