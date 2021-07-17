package com.example.trainningandroidstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.trainningandroidstudio.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding viewBinding;
    ArrayList<DoVat> arrayDoVat;
    DoVatAdapter adapter;
    public static Database database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        viewBinding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(viewBinding.getRoot());
        arrayDoVat = new ArrayList<>();
        adapter = new DoVatAdapter(MainActivity.this,R.layout.dovat_line,arrayDoVat);
        database = new Database(this,"Quanly.sqlite",null,1);
        database.queryData("CREATE TABLE IF NOT EXISTS DoVat( Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten VARCHAR(150), MoTa VARCHAR(200), HinhAnh BLOB)");
        //get data
        Cursor cursor = database.getData("SELECT * FROM DoVat");
        while(cursor.moveToNext()){
            arrayDoVat.add(new DoVat(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getBlob(3)
            ));
        }
        adapter.notifyDataSetChanged();


        viewBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });

        viewBinding.listViewDoVat.setAdapter(adapter);
    }
    public void xoaCongViec(int id){

        database.queryData("DELETE FROM DoVat WHERE Id = '"+id+"'");
        Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show();
        arrayDoVat.clear();
        Cursor cursor = database.getData("SELECT * FROM DoVat");
        while(cursor.moveToNext()){
            arrayDoVat.add(new DoVat(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getBlob(3)
            ));
            adapter.notifyDataSetChanged();
        }



    }

}