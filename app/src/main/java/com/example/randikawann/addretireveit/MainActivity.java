package com.example.randikawann.addretireveit;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static final int PICKIMAGE = 100;
    ImageView userImage;
    Uri imageuri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userImage = findViewById(R.id.userImage);

        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
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
