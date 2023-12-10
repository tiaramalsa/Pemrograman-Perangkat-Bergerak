package com.example.prouasa11202113352;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void entry(View view) {
        Intent intent=new Intent(MainActivity.this, EntryNilai.class);
        startActivity(intent);
    }

    public void tampilNilai(View view) {
        Intent intent=new Intent(MainActivity.this, TampilNilai.class);
        startActivity(intent);
    }
}