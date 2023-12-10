package com.example.prouasa11202113352;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterNilai extends RecyclerView.Adapter<AdapterNilai.myViewHolder> {
    Activity activity;
    ArrayList<ModelNilai> modelNilaiArrayList;
    DatabaseReference dbr;

    public AdapterNilai(Activity activity, ArrayList<ModelNilai> modelNilaiArrayList) {
        this.activity = activity;
        this.modelNilaiArrayList = modelNilaiArrayList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(activity);
        View view=inflater.inflate(R.layout.format_tampil_nilai,parent,false);
        return new AdapterNilai.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.ckode.setText(modelNilaiArrayList.get(position).getKode());
        holder.cmatkul.setText(modelNilaiArrayList.get(position).getMatkul());
        holder.csks.setText(modelNilaiArrayList.get(position).getSks());
        holder.cnangka.setText(modelNilaiArrayList.get(position).getNangka());
        holder.cnhuruf.setText(modelNilaiArrayList.get(position).getNhuruf());
        holder.cpredikat.setText(modelNilaiArrayList.get(position).getPredikat());
        holder.card01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),EditData.class);
                intent.putExtra("kunci",modelNilaiArrayList.get(position).getKey());
                intent.putExtra("kode",modelNilaiArrayList.get(position).getKode());
                intent.putExtra("matkul",modelNilaiArrayList.get(position).getMatkul());
                intent.putExtra("sks",modelNilaiArrayList.get(position).getSks());
                intent.putExtra("nangka",modelNilaiArrayList.get(position).getNangka());
                intent.putExtra("nhuruf",modelNilaiArrayList.get(position).getNhuruf());
                intent.putExtra("predikat",modelNilaiArrayList.get(position).getPredikat());
                view.getContext().startActivity(intent);
            }
        });
        holder.hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(activity);
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbr= FirebaseDatabase.getInstance().getReference();
                        dbr.child("Nilai").child(modelNilaiArrayList.get(position).getKey())
                                .removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(activity, "Hapus berhasil", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setMessage("Apakah yakin data "+modelNilaiArrayList.get(position).getMatkul()+" akan di hapus?");
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelNilaiArrayList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView ckode,cmatkul,csks,cnangka,cnhuruf,cpredikat;
        ImageView hapus;
        CardView card01;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            ckode=itemView.findViewById(R.id.kode);
            cmatkul=itemView.findViewById(R.id.matkul);
            csks=itemView.findViewById(R.id.sks);
            cnangka=itemView.findViewById(R.id.nangka);
            cnhuruf=itemView.findViewById(R.id.nhuruf);
            cpredikat=itemView.findViewById(R.id.predikat);
            hapus=itemView.findViewById(R.id.tombolhapus);
            card01=itemView.findViewById(R.id.cardview_nilai);

        }
    }
}
