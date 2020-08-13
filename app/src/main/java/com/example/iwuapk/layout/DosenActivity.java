package com.example.iwuapk.layout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iwuapk.R;
import com.example.iwuapk.adapter.DosenAdapter;
import com.example.iwuapk.model.Dosen;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class DosenActivity extends AppCompatActivity {

    private RecyclerView DosenRecyclerView;
    private ArrayList<Dosen> dosenArrayList;
    private DosenAdapter adapter;

    private EditText edtNama_dosen;
    private Button btnTambahDosen;

    private String[] tvNamaDosen = new String[]{"Arif Rachmat", "Arif Rachmat", "Dadang Suhendar", "Dendi Kusnaendi", "Gilang", "Erni Setiyani"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen);


        DosenRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_dataDosen);


        dosenArrayList = showDataDosen();
        adapter = new DosenAdapter(this, dosenArrayList);
        DosenRecyclerView.setAdapter(adapter);
        DosenRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogForm();
            }
        });

        //Button add data dosen

    }

//    private void addDataDosen(){
//        String namaDosen = edtNamaDosen.getText().toString().trim();
//
//        if (!TextUtils.isEmpty(namaDosen)){
//
//            String id = databaseDosens.push().getKey();
//
//            Dosen dosen = new Dosen(id, namaDosen);
//
//            databaseDosens.child(id).setValue(dosen);
//
//            Toast.makeText(this,"Nama Dosen Ditambahkan", Toast.LENGTH_LONG).show();
//
//        }else{
//            Toast.makeText(this, "Masukan Nama",Toast.LENGTH_LONG).show();
//        }
//    }

    private ArrayList<Dosen> showDataDosen() {
        ArrayList<Dosen> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Dosen dosen = new Dosen();
            dosen.setNama_dosen(tvNamaDosen[i]);
            list.add(dosen);
        }
        return list;
    }

    private void showDialogForm() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(DosenActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.form_add_dosen, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);

        final EditText edtNamaDosen = dialogView.findViewById(R.id.et_nama_dosen);

        dialog.setView(dialogView);

        // set button
        final Button btnTambahDosen = dialogView.findViewById(R.id.btn_tambah_dosen);

        btnTambahDosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(edtNamaDosen.getText().toString())){
                    Toast.makeText(DosenActivity.this, "Masukan Nama Anda",Toast.LENGTH_SHORT).show();
                    return;
                }

                FirebaseDatabase database = FirebaseDatabase.getInstance();

                DatabaseReference myDataDosen = database.getReference();

                myDataDosen.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Object value = dataSnapshot.getValue();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(DosenActivity.this, "Failed to Read Value", Toast.LENGTH_SHORT).show();
                    }
                });

                myDataDosen.child("Dosen").child(edtNamaDosen.getText().toString()).child("Nama").setValue(edtNamaDosen.getText().toString());

                Toast.makeText(DosenActivity.this, "Data Telah Ditambahkan!", Toast.LENGTH_SHORT).show();

            }
        });

//        dialog.setPositiveButton("TAMBAH", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int which) {
//
//                //Validasi
//                if (TextUtils.isEmpty(edtNamaDosen.getText().toString())){
//                    Toast.makeText(DosenActivity.this, "Masukan Nama Anda",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//
//                DatabaseReference myDataDosen = database.getReference();
//
//                myDataDosen.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        Object value = dataSnapshot.getValue();
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                        Toast.makeText(DosenActivity.this, "Failed to Read Value", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//
//            }
//        });


//        dialog.setNegativeButton("BATAL", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });



        dialog.show();
    }
}