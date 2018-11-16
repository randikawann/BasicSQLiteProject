package com.example.randikawann.addretireveit;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText etUserName;
    EditText etEmail;
    EditText etPassword;
    Button btRegister;
    TextView tvGoLogin;


    private MyHelper mMyHelper;
    private SQLiteDatabase mSQLiteDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUserName = findViewById(R.id.etUserName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etEmail);
        btRegister = findViewById(R.id.btLogIn);
        tvGoLogin = findViewById(R.id.tvGoLogin);



        //database
        mMyHelper = new MyHelper(RegisterActivity.this, "STUDDB",null,1);
        mSQLiteDb = mMyHelper.getWritableDatabase();

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put("user",etUserName.getText().toString());
                cv.put("email", etEmail.getText().toString());
                cv.put("password", etPassword.getText().toString());

                long id = mSQLiteDb.insert("employers",null,cv);
                Log.i("showerrors","added id "+id);
                Toast.makeText(RegisterActivity.this,"User Added succesfully",Toast.LENGTH_SHORT).show();

                Intent goRegIntent = new Intent(RegisterActivity.this, ProfileActivity.class);
                goRegIntent.putExtra("email",etEmail.getText().toString());
                startActivity(goRegIntent);

            }
        });
        tvGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goRegIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(goRegIntent);
            }
        });
    }
}
