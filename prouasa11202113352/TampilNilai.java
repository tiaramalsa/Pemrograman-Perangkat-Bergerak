package com.example.prouasa11202113352;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TampilNilai extends AppCompatActivity {
    RecyclerView recyclerViewNilai;
    DatabaseReference dbr;
    ArrayList<ModelNilai> modelNilaiArrayList=new ArrayList<>();
    ModelNilai modelNilai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_nilai);

        recyclerViewNilai=findViewById(R.id.rela_nilai);

        dbr= FirebaseDatabase.getInstance().getReference();
        tampil_nilai();
        recyclerViewNilai.setLayoutManager(new LinearLayoutManager(this));
    }

    private void tampil_nilai() {
        dbr.child("Nilai").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    ModelNilai modelNilai=dataSnapshot.getValue(ModelNilai.class);
                    modelNilai.setKey(dataSnapshot.getKey());
                    modelNilaiArrayList.add(modelNilai);
                }
                AdapterNilai adapterNilai=new AdapterNilai(TampilNilai.this, modelNilaiArrayList);
                recyclerViewNilai.setAdapter(adapterNilai);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}