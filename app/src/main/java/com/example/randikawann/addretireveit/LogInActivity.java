package com.example.randikawann.addretireveit;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {
    EditText etUserName;
    EditText etMobileNumber;
    Button btAddValue;
    Button btShowValue;

    private MyHelper mMyHelper;
    private SQLiteDatabase mSQLiteDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        etUserName = findViewById(R.id.etUserName);
        etMobileNumber = findViewById(R.id.etMobileNumber);
        btAddValue = findViewById(R.id.btAddValue);
        btShowValue = findViewById(R.id.btShowValue);

        //database
        mMyHelper = new MyHelper(LogInActivity.this, "STUDDB",null,1);
        mSQLiteDb = mMyHelper.getWritableDatabase();


        btAddValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put("name",etUserName.getText().toString());
                cv.put("mobilenumber", etMobileNumber.getText().toString());

                long id = mSQLiteDb.insert("student",null,cv);
                Toast.makeText(LogInActivity.this,"Add user id = "+id,Toast.LENGTH_SHORT).show();
            }
        });

        btShowValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = mSQLiteDb.query("student", null, null, null, null, null,null);
                while (c.moveToNext()) {
                    Toast.makeText(LogInActivity.this , c.getString(c.getColumnIndex("name")) , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
