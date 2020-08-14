package com.example.iwuapk.layout;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iwuapk.R;
import com.example.iwuapk.adapter.MahasiswaAdapter;
import com.example.iwuapk.model.Mahasiswa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MahasiswaActivity extends AppCompatActivity {

    private RecyclerView mahasiswaRecyclerView;
    private ArrayList<Mahasiswa> mahasiswaArrayList;
    private MahasiswaAdapter adapter;
    private DatabaseReference databaseMahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa);

        databaseMahasiswa = FirebaseDatabase.getInstance().getReference("mahasiswa");

        mahasiswaArrayList = new ArrayList<>();

        mahasiswaRecyclerView = findViewById(R.id.recyclerView_dataMahasiswa);
        mahasiswaRecyclerView.setHasFixedSize(true);
        mahasiswaRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogForm();
            }
        });


        databaseMahasiswa.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot mahasiswaSnapshot : dataSnapshot.getChildren()) {
                        Mahasiswa mahasiswa = mahasiswaSnapshot.getValue(Mahasiswa.class);
                        mahasiswaArrayList.add(mahasiswa);
                    }
                    adapter = new MahasiswaAdapter(mahasiswaArrayList);
                    mahasiswaRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showDialogForm() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(MahasiswaActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.form_add_mahasiswa, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);

        final EditText edtNamaMahasiswa = dialogView.findViewById(R.id.et_nama_mhs);
        final EditText edtAsal = dialogView.findViewById(R.id.et_sekolah_mhs);
        final Spinner spinnerProdi = dialogView.findViewById(R.id.spinner_prodi);
        final Button btnTambahMhs = dialogView.findViewById(R.id.btn_tambah_mahasiswa);

        dialog.setView(dialogView);


        //set Button & Validasi

        btnTambahMhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edtNamaMahasiswa.getText().toString())) {
                    Toast.makeText(MahasiswaActivity.this, "Masukan Nama Anda", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(edtAsal.getText().toString())) {
                    Toast.makeText(MahasiswaActivity.this, "Masukan Asal Sekolah Anda", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(spinnerProdi.getSelectedItem().toString())) {
                    Toast.makeText(MahasiswaActivity.this, "Pilih Prodi Anda", Toast.LENGTH_SHORT).show();
                    return;
                }

                String nama = edtNamaMahasiswa.getText().toString().trim();
                String asal = edtAsal.getText().toString().trim();
                String prodi = spinnerProdi.getSelectedItem().toString().trim();


                String id = databaseMahasiswa.push().getKey();

                Mahasiswa mahasiswa = new Mahasiswa(id, nama, asal, prodi);

                databaseMahasiswa.child(id).setValue(mahasiswa);

                Toast.makeText(MahasiswaActivity.this, "Data berhasil dimasukan", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }
}