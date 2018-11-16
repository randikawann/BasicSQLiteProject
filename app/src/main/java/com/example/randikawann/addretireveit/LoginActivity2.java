package com.example.randikawann.addretireveit;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity2 extends AppCompatActivity {
    EditText etPassword;
    EditText etEmail;
    Button btLogIn;
    TextView tvGoReg;

    private MyHelper mMyHelper;
    private SQLiteDatabase mSQLiteDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        etPassword = findViewById(R.id.etEmail);
        etEmail = findViewById(R.id.etUserName);
        tvGoReg = findViewById(R.id.tvGoReg);
        btLogIn = findViewById(R.id.btLogIn);

        final String password = etPassword.getText().toString();
        final String email = etEmail.getText().toString();

        //database
        mMyHelper = new MyHelper(LoginActivity2.this, "STUDDB",null,1);
        mSQLiteDb = mMyHelper.getWritableDatabase();


        btLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(email.equals("")){
                    Toast.makeText(LoginActivity2.this , "Please Enter email" , Toast.LENGTH_SHORT).show();
                }
                if(password.equals("")){
                    Toast.makeText(LoginActivity2.this , "Please Password" , Toast.LENGTH_SHORT).show();
                }
                //get data from database
                Cursor c = mSQLiteDb.query("users1", null, null, null, null, null,null);
                while (c.moveToNext()) {
                    String DBEmail = c.getString(c.getColumnIndex("email"));
                    String DBPassword = c.getString(c.getColumnIndex("DBPassword"));
                    if(email.equals(DBPassword) && password.equals(DBPassword)){
                        Intent goProfileIntent = new Intent(LoginActivity2.this, ProfileActivity.class);
                        goProfileIntent.putExtra("email",DBEmail);
                        startActivity(goProfileIntent);

                    }
                }

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
