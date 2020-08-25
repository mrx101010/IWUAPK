package com.example.iwuapk.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iwuapk.R;
import com.example.iwuapk.adapter.DosenAdapter;
import com.example.iwuapk.model.Dosen;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DosenUpdateActivity extends AppCompatActivity {

    EditText edtNamaDosen;
    Button btnUpdateDosen;

    String idDosen, namaDosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_update);
        initId();


        Intent intent = getIntent();

        idDosen = intent.getStringExtra(DosenAdapter.DOSEN_ID);
        namaDosen = intent.getStringExtra(DosenAdapter.DOSEN_NAME);

        edtNamaDosen.setText(namaDosen);

        btnUpdateDosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edtNamaDosen.getText().toString().trim())){
                    Toast.makeText(DosenUpdateActivity.this,
                            "Mohon Isi Nama Dengan Lengkap", Toast.LENGTH_SHORT).show();
                    return;
                }

                updateDosen(idDosen);
                finish();
            }
        });

    }

    private void initId(){
        edtNamaDosen = findViewById(R.id.et_nama_dosen_update);
        btnUpdateDosen = findViewById(R.id.btn_Update_dosen);
    }

    private boolean updateDosen(String idDosen){
        DatabaseReference dbDosen = FirebaseDatabase.getInstance()
                .getReference("dosen")
                .child(idDosen);

        namaDosen =  edtNamaDosen.getText().toString().trim();

        Dosen dosen = new Dosen(idDosen, namaDosen);

        dbDosen.setValue(dosen);

        Toast.makeText(this, "Data Dosen Berhasil Diupdate!", Toast.LENGTH_SHORT).show();

        return true;
    }

}