package com.example.prouasa11202113352;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditData extends AppCompatActivity {
    EditText ekode,ematkul,esks,enangka,enhuruf,epredikat;
    Button updatetombol;

    DatabaseReference dbr;
    ModelNilai modelNilai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        ekode=findViewById(R.id.editkode);
        ematkul=findViewById(R.id.editMatkul);
        esks=findViewById(R.id.editsks);
        enangka=findViewById(R.id.editnangka);
        enhuruf=findViewById(R.id.editnhuruf);
        epredikat=findViewById(R.id.editpredikat);

        dbr= FirebaseDatabase.getInstance().getReference();
        modelNilai=new ModelNilai();

        Bundle bundle=getIntent().getExtras();
        ekode.setText(bundle.getString("kode"));
        ematkul.setText(bundle.getString("matkul"));
        esks.setText(bundle.getString("sks"));
        enangka.setText(bundle.getString("nangka"));
        enhuruf.setText(bundle.getString("nhuruf"));
        epredikat.setText(bundle.getString("predikat"));

        updatetombol=findViewById(R.id.buttonUpdate);

        updatetombol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelNilai.setKode(ekode.getText().toString());
                modelNilai.setMatkul(ematkul.getText().toString());
                modelNilai.setSks(esks.getText().toString());
                modelNilai.setNangka(enangka.getText().toString());
                modelNilai.setNhuruf(enhuruf.getText().toString());
                modelNilai.setPredikat(epredikat.getText().toString());

                String kunci=(bundle.getString("kunci"));
                Intent intent=new Intent(EditData.this, TampilNilai.class);
                startActivity(intent);
                dbr.child("Nilai").child(kunci).setValue(modelNilai).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(EditData.this, "Update sukses", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}