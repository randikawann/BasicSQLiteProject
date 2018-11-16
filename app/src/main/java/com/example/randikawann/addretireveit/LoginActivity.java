package com.example.randikawann.addretireveit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText etPassword;
    EditText etEmail;
    Button btLogIn;
    TextView tvGoReg;

    private MyHelper mMyHelper;
    private SQLiteDatabase mSQLiteDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etPassword = findViewById(R.id.etEmail);
        etEmail = findViewById(R.id.etUserName);
        tvGoReg = findViewById(R.id.tvGoReg);
        btLogIn = findViewById(R.id.btLogIn);


        //database



        btLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String password = etPassword.getText().toString();
                String email = etEmail.getText().toString();

                if(email.equals("")){
                    Toast.makeText(LoginActivity.this , "Please Enter email" , Toast.LENGTH_SHORT).show();
                }
                if(password.equals("")){
                    Toast.makeText(LoginActivity.this , "Please Password" , Toast.LENGTH_SHORT).show();
                }

                else {
                    try {
                        mMyHelper = new MyHelper(LoginActivity.this, "CUSTDB",null,1);
                        mSQLiteDb = mMyHelper.getWritableDatabase();
                        mSQLiteDb = mMyHelper.getReadableDatabase();
                        //get data from database
                        Cursor c = mSQLiteDb.query("customers" , null , null , null , null , null , null);
                        while (c.moveToNext()) {
                            String DBEmail = c.getString(c.getColumnIndex("email"));
                            String DBPassword = c.getString(c.getColumnIndex("password"));

//                            Log.i("showerrors","database email: "+DBEmail);
//                            Log.i("showerrors","database password: "+DBPassword);
                            if (email.equals(DBEmail) && password.equals(DBPassword)) {
                                Intent goProfileIntent = new Intent(LoginActivity.this , ProfileActivity.class);
//                                Log.i("showerrors","database email: "+email);
                                goProfileIntent.putExtra("email" , email);
                                startActivity(goProfileIntent);

                            }
                        }
                    } catch (Exception e) {
//                        Toast.makeText(LoginActivity.this , "database error with loginactivity 2" , Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        tvGoReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goRegIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(goRegIntent);
            }
        });
    }
}
