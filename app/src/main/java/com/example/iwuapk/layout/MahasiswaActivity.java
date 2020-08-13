package com.example.iwuapk.layout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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

    private RecyclerView MahasiswaRecyclerView;
    private ArrayList<Mahasiswa> mahasiswaArrayList;
    private MahasiswaAdapter adapter;


    private String[] tvNama_mhs = new String[]{"Arif Rachmat", "Arif Rachmat", "Dadang Suhendar", "Dendi Kusnaendi", "Gilang", "Erni Setiyani"};
    private String[] tvAsal_sklh = new String[]{"SMKN 4 BANDUNG", "SMKN 3 BANDUNG", "SMP BHAYANGKARI", "SD CIJERAH", "SMKN 4 BANDUNG", "SMKN 8 BANDUNG"};
    private String[] tvFakultas = new String[]{"Teknik Informatika", "Teknik Informatika", "Teknik Informatika", "Teknik Bangunan", "Tata Boga", "Teknik Informatika"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa);

        MahasiswaRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_dataMahasiswa);

        mahasiswaArrayList = showDataMahasiswa();
        adapter = new MahasiswaAdapter(this, mahasiswaArrayList);
        MahasiswaRecyclerView.setAdapter(adapter);
        MahasiswaRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogForm();
            }
        });
    }

    private ArrayList<Mahasiswa> showDataMahasiswa() {
        ArrayList<Mahasiswa> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Mahasiswa mahasiswa = new Mahasiswa();
            mahasiswa.setNamaMahasiswa(tvNama_mhs[i]);
            mahasiswa.setAsalSekolah(tvAsal_sklh[i]);
            mahasiswa.setFakultas(tvFakultas[i]);
            list.add(mahasiswa);
        }
        return list;
    }

    private void showDialogForm() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(MahasiswaActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.form_add_mahasiswa, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);

        final EditText edtNamaMahasiswa = dialogView.findViewById(R.id.et_nama_mhs);
        final EditText edtAsal = dialogView.findViewById(R.id.et_sekolah_mhs);
        final Spinner  spinnerProdi = dialogView.findViewById(R.id.spinner_prodi);
        final Button btnTambahMhs = dialogView.findViewById(R.id.btn_tambah_mahasiswa);

        dialog.setView(dialogView);


        //set Button & Validasi

        btnTambahMhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edtNamaMahasiswa.getText().toString())){
                    Toast.makeText(MahasiswaActivity.this, "Masukan Nama Anda", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(edtAsal.getText().toString())){
                    Toast.makeText(MahasiswaActivity.this, "Masukan Asal Sekolah Anda", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(spinnerProdi.getSelectedItem().toString())){
                    Toast.makeText(MahasiswaActivity.this, "Pilih Prodi Anda", Toast.LENGTH_SHORT).show();
                    return;
                }

                FirebaseDatabase database = FirebaseDatabase.getInstance();

                DatabaseReference myDataMahasiswa = database.getReference();

                myDataMahasiswa.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Object value = dataSnapshot.getValue();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(MahasiswaActivity.this, "Failed to Read Value", Toast.LENGTH_SHORT).show();

                    }
                });

                myDataMahasiswa.child("Mahasiswa").child(edtNamaMahasiswa.getText().toString()).child("Nama").setValue(edtNamaMahasiswa.getText().toString());
                myDataMahasiswa.child("Mahasiswa").child(edtAsal.getText().toString()).child("Asal Sekolah").setValue(edtAsal.getText().toString());
                myDataMahasiswa.child("Mahasiswa").child(spinnerProdi.getSelectedItem().toString()).child("Prodi").setValue(spinnerProdi.getSelectedItem().toString());

                Toast.makeText(MahasiswaActivity.this, "Data Mahasiswa Telah Ditambahkan!", Toast.LENGTH_SHORT).show();



            }
        });

        dialog.show();
    }
}