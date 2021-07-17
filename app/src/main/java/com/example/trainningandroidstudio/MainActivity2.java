package com.example.trainningandroidstudio;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.trainningandroidstudio.databinding.ActivityMain2Binding;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity2 extends AppCompatActivity {

    ActivityMain2Binding viewBinding2;
    int REQUEST_CODE_CAMERA = 232;
    int REQUEST_CODE_FOLDER = 313;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
        viewBinding2 = ActivityMain2Binding.inflate(inflater);
        setContentView(viewBinding2.getRoot());
        viewBinding2.imCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityCompat.requestPermissions(MainActivity2.this,
                        new String[]{Manifest.permission.CAMERA},REQUEST_CODE_CAMERA);
            }
        });

        viewBinding2.imfolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityCompat.requestPermissions(MainActivity2.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_FOLDER);
            }
        });

        viewBinding2.btnThem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chuyen data tu imView sang byte
                BitmapDrawable bitmapDrawable = (BitmapDrawable) viewBinding2.imageIm.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100,byteArray);
                byte[] byteHinh = byteArray.toByteArray();

                MainActivity.database.insert_DOVAT(
                    viewBinding2.editNameDoVat.getText().toString().trim(),
                    viewBinding2.editMota.getText().toString().trim(),
                        byteHinh
                );
                Toast.makeText(MainActivity2.this, "added", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity2.this,MainActivity.class));
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    if(requestCode == REQUEST_CODE_CAMERA && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }
    else if(requestCode == REQUEST_CODE_FOLDER && grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent,REQUEST_CODE_FOLDER);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CAMERA && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            viewBinding2.imageIm.setImageBitmap(bitmap);
        }
        else if(requestCode == REQUEST_CODE_FOLDER && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                viewBinding2.imageIm.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    }
