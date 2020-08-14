package com.example.iwuapk.layout;

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
    private DatabaseReference databaseDosen;

    private EditText edtNama_dosen;
    private Button btnTambahDosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen);

        databaseDosen = FirebaseDatabase.getInstance().getReference("dosen");
        dosenArrayList = new ArrayList<>();

        DosenRecyclerView = findViewById(R.id.recyclerView_dataDosen);
        DosenRecyclerView.setHasFixedSize(true);
        DosenRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogForm();
            }
        });

        databaseDosen.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot dosenSnapshot : dataSnapshot.getChildren()) {
                        Dosen dosen = dosenSnapshot.getValue(Dosen.class);
                        dosenArrayList.add(dosen);
                    }
                    adapter = new DosenAdapter(dosenArrayList);
                    DosenRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


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

                if (TextUtils.isEmpty(edtNamaDosen.getText().toString())) {
                    Toast.makeText(DosenActivity.this, "Masukan Nama Anda", Toast.LENGTH_SHORT).show();
                    return;
                }

                String nama = edtNamaDosen.getText().toString().trim();

                String id = databaseDosen.push().getKey();

                Dosen dosen = new Dosen(id, nama);

                databaseDosen.child(id).setValue(dosen);
                Toast.makeText(DosenActivity.this, "Data Telah Ditambahkan!", Toast.LENGTH_SHORT).show();

            }
        });
        dialog.show();
    }
}