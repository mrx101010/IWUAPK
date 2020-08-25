package com.example.iwuapk.layout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iwuapk.R;
import com.example.iwuapk.adapter.MahasiswaAdapter;
import com.example.iwuapk.model.Mahasiswa;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MahasiswaUpdateActivity extends AppCompatActivity {

    EditText edtNama, edtAsal;
    Spinner spinnerProdi;
    Button btnUpdate;

    String idMahasiswa, idDosen, nama, asal, prodi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_update);
        initId();

        Intent intent = getIntent();

        idMahasiswa = intent.getStringExtra(MahasiswaAdapter.MAHASISWA_ID);
        idDosen = intent.getStringExtra(MahasiswaAdapter.DOSEN_ID);
        nama = intent.getStringExtra(MahasiswaAdapter.MAHASISWA_NAME);
        asal = intent.getStringExtra(MahasiswaAdapter.MAHASISWA_ASAL);

        edtNama.setText(nama);
        edtAsal.setText(asal);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edtNama.getText().toString().trim())) {
                    Toast.makeText(MahasiswaUpdateActivity.this,
                            "Mohon Isi Nama Dengan Lengkap", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(edtAsal.getText().toString().trim())) {
                    Toast.makeText(MahasiswaUpdateActivity.this,
                            "Mohon Isi Asal sekolah Dengan Lengkap", Toast.LENGTH_SHORT).show();
                    return;
                }

                updateMahasiswa(idMahasiswa, idDosen);
                finish();
            }
        });
    }

    private void initId() {
        edtNama = findViewById(R.id.et_update_name_mhs);
        edtAsal = findViewById(R.id.et_update_sekolah_mhs);
        btnUpdate = findViewById(R.id.btn_update_mahasiswa);
        spinnerProdi = findViewById(R.id.spinner_update_prodi);
    }

    private boolean updateMahasiswa(String idMahasiswa, String idDosen) {
        DatabaseReference drMahasiswa = FirebaseDatabase.getInstance()
                .getReference("mahasiswa")
                .child(idDosen)
                .child(idMahasiswa);

        nama = edtNama.getText().toString().trim();
        asal = edtAsal.getText().toString().trim();
        prodi = spinnerProdi.getSelectedItem().toString().trim();

        Mahasiswa mahasiswa = new Mahasiswa(idMahasiswa, nama, asal, prodi);

        drMahasiswa.setValue(mahasiswa);

        Toast.makeText(this, "Artist updated", Toast.LENGTH_LONG).show();

        return true;
    }
}