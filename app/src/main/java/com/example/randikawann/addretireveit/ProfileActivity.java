package com.example.randikawann.addretireveit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    EditText etUserName;
    EditText etEmail;
    EditText etpassword;
    EditText etOther;
    Button btSaveChange;

    ImageView imgEditName;
    ImageView imgPassword;
    ImageView imgOther;

    String intentEmail;

    private static final int PICKIMAGE = 100;
    ImageView userImage;
    Uri imageuri;

    private MyHelper mMyHelper;
    private SQLiteDatabase mSQLiteDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        etUserName = findViewById(R.id.etUserName);
        etEmail = findViewById(R.id.etEmail);
        etpassword = findViewById(R.id.etpassword);
        etOther = findViewById(R.id.etOther);
        userImage = findViewById(R.id.userImage);
        btSaveChange = findViewById(R.id.btSaveChange);

        imgEditName = findViewById(R.id.imgEditName);
        imgPassword = findViewById(R.id.imgPassword);
        imgOther = findViewById(R.id.imgOther);

        //database
        mMyHelper = new MyHelper(ProfileActivity.this, "STUDDB",null,1);
        mSQLiteDb = mMyHelper.getWritableDatabase();

        //Toolbar
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //set disable
        etUserName.setEnabled(false);
        etEmail.setEnabled(false);
        etpassword.setEnabled(false);
        etOther.setEnabled(false);


        imgEditName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etUserName.setEnabled(true);
            }
        });
        imgPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etpassword.setEnabled(true);
            }
        });
        imgOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etOther.setEnabled(true);
            }
        });

        intentEmail = getIntent().getExtras().getString("email");

        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });


        try {
            updateDetails();
        }catch(Exception e){
            Toast.makeText(ProfileActivity.this , "database error from profile activity" , Toast.LENGTH_SHORT).show();
        }



    }

    private void updateDetails() {
        @SuppressLint("Recycle") Cursor c = mSQLiteDb.query("employer", null, null, null, null, null,null);
        while (c.moveToNext()) {
            String email = c.getString(c.getColumnIndex("email"));

            if(intentEmail.equals(email)){

                String user = c.getString(c.getColumnIndex("user"));
                String email1 = c.getString(c.getColumnIndex("email"));
                String password = c.getString(c.getColumnIndex("password"));

                Toast.makeText(ProfileActivity.this , c.getString(c.getColumnIndex("name")) , Toast.LENGTH_SHORT).show();

                etUserName.setText(user);
                etEmail.setText(email1);
                etpassword.setText(password);


            }


        }
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICKIMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode , int resultCode , @Nullable Intent data) {
        super.onActivityResult(requestCode , resultCode , data);
        if(resultCode == RESULT_OK && requestCode == PICKIMAGE){
            imageuri = data.getData();
            userImage.setImageURI(imageuri);
        }
    }
}