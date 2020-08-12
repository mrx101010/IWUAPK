package com.example.iwuapk.layout;

import android.content.DialogInterface;
import android.os.Bundle;

import com.example.iwuapk.adapter.TambahMahasiswaAdapter;
import com.example.iwuapk.model.Mahasiswa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.iwuapk.R;

import java.util.ArrayList;

public class TambahMahasiswaActivity extends AppCompatActivity {

    private RecyclerView MahasiswaRecyclerView;
    private ArrayList<Mahasiswa> mahasiswaArrayList;
    private TambahMahasiswaAdapter adapter;

    private String [] tvNama_mhs = new String[] {"Arif Rachmat","Arif Rachmat","Dadang Suhendar", "Dendi Kusnaendi", "Gilang", "Erni Setiyani"};
    private String [] tvAsal_sklh = new String[] {"SMKN 4 BANDUNG", "SMKN 3 BANDUNG", "SMP BHAYANGKARI", "SD CIJERAH", "SMKN 4 BANDUNG", "SMKN 8 BANDUNG"};
    private String [] tvFakultas = new String[] {"Teknik Informatika","Teknik Informatika","Teknik Informatika","Teknik Bangunan", "Tata Boga", "Teknik Informatika"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_mahasiswa);

        MahasiswaRecyclerView = (RecyclerView)findViewById(R.id.recyclerView_dataMahasiswa);

        mahasiswaArrayList = showDataMahasiswa();
        adapter = new TambahMahasiswaAdapter(this, mahasiswaArrayList);
        MahasiswaRecyclerView.setAdapter(adapter);
        MahasiswaRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false));




        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogForm();
            }
        });
    }

    private ArrayList<Mahasiswa> showDataMahasiswa(){
        ArrayList<Mahasiswa> list = new ArrayList<>();

        for (int i = 0; i < 5; i++){
            Mahasiswa mahasiswa = new Mahasiswa();
            mahasiswa.setNamaMahasiswa(tvNama_mhs[i]);
            mahasiswa.setAsalSekolah(tvAsal_sklh[i]);
            mahasiswa.setFakultas(tvFakultas[i]);
            list.add(mahasiswa);
        }
        return list;
    }

    private void showDialogForm(){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(TambahMahasiswaActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.form_add_mahasiswa, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);

        dialog.show();
    }
}