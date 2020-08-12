package com.example.iwuapk.layout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iwuapk.R;
import com.example.iwuapk.adapter.DosenAdapter;
import com.example.iwuapk.model.Dosen;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DosenActivity extends AppCompatActivity {

    private RecyclerView DosenRecyclerView;
    private ArrayList<Dosen> dosenArrayList;
    private DosenAdapter adapter;

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

    }

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

        dialog.show();
    }
}