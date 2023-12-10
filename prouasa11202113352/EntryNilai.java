package com.example.prouasa11202113352;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EntryNilai extends AppCompatActivity implements View.OnClickListener {

    EditText ckode,cmatkul,csks,cnangka,churuf,cpredikat;
    Button buttonsave, buttoncancel;
    DatabaseReference dbr;
    ModelNilai modelNilai;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_nilai);

        dbr = FirebaseDatabase.getInstance().getReference().child("Nilai");

        modelNilai = new ModelNilai();
        ckode = findViewById(R.id.editkode);
        cmatkul = findViewById(R.id.editMatkul);
        csks = findViewById(R.id.editsks);
        cnangka = findViewById(R.id.editangka);
        churuf = findViewById(R.id.edithuruf);
        cpredikat = findViewById(R.id.editpredikat);


        buttonsave = findViewById(R.id.buttonSave);
        buttoncancel = findViewById(R.id.buttonCancel);

        buttonsave.setOnClickListener(this);
        buttoncancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //save
        if(view.getId()==R.id.buttonSave)
        {
            modelNilai.setKode(ckode.getText().toString());
            modelNilai.setMatkul(cmatkul.getText().toString());
            modelNilai.setSks(csks.getText().toString());
            modelNilai.setNangka(cnangka.getText().toString());
            modelNilai.setNhuruf(churuf.getText().toString());
            modelNilai.setPredikat(cpredikat.getText().toString());

            dbr.push().setValue(modelNilai);
            Toast.makeText(this, "Data Tersimpan !", Toast.LENGTH_SHORT).show();
        }else{
            if(view.getId()==R.id.buttonCancel){
                Intent intent=new Intent(EntryNilai.this, MainActivity.class);
                startActivity(intent);
            }
        }
    }
}