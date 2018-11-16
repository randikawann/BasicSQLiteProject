package com.example.randikawann.addretireveit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    EditText etUserName;
    EditText etEmail;
    Button btLogIn;
    TextView etGoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etGoLogin = findViewById(R.id.etGoLogin);

        etGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goRegIntent = new Intent(RegisterActivity.this, LoginActivity2.class);
                startActivity(goRegIntent);
            }
        });
    }
}
