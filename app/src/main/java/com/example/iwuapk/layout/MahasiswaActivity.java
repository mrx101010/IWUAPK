package com.example.iwuapk.layout;

import android.app.Activity;
import android.content.Intent;
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.iwuapk.R;
import com.example.iwuapk.adapter.DosenAdapter;
import com.example.iwuapk.adapter.MahasiswaAdapter;
import com.example.iwuapk.model.Dosen;
import com.example.iwuapk.model.Mahasiswa;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MahasiswaActivity extends AppCompatActivity {

    private RecyclerView mahasiswaRecyclerView;
    private ArrayList<Mahasiswa> mahasiswaArrayList;
    private ArrayList<Dosen> dosenArrayList;
    private MahasiswaAdapter adapter;
    private DatabaseReference databaseMahasiswa;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String id;

    private EditText edtNamaMahasiswa;
    private EditText edtAsal;
    private Spinner spinnerProdi;
    private Button btnTambahMhs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa);


        mahasiswaArrayList = new ArrayList<>();
        dosenArrayList = new ArrayList<>();

        mahasiswaRecyclerView = (RecyclerView)findViewById(R.id.recyclerView_dataMahasiswa);

//        mahasiswaRecyclerView = findViewById(R.id.recyclerView_dataMahasiswa);
        mahasiswaRecyclerView.setHasFixedSize(true);
        mahasiswaRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();

        id = intent.getStringExtra(DosenAdapter.DOSEN_ID);
        String name = intent.getStringExtra(DosenAdapter.DOSEN_NAME);
        databaseMahasiswa = FirebaseDatabase.getInstance().getReference("mahasiswa").child(id);



        TextView dosenName = findViewById(R.id.tvJudul_Mahasiswa);
        dosenName.setText(name);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogForm();
            }
        });

        loadRecyclerViewData();


        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                Function
                swipeRefreshLayout.setRefreshing(false);
                loadRecyclerViewData();
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

        final AlertDialog show = dialog.show();


        //set Button & Validasi

        btnTambahMhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edtNamaMahasiswa.getText().toString())) {
                    Toast.makeText(MahasiswaActivity.this, "Masukan Nama Anda",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(edtAsal.getText().toString())) {
                    Toast.makeText(MahasiswaActivity.this, "Masukan Asal Sekolah Anda",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(spinnerProdi.getSelectedItem().toString())) {
                    Toast.makeText(MahasiswaActivity.this, "Pilih Prodi Anda",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                String nama = edtNamaMahasiswa.getText().toString().trim();
                String asal = edtAsal.getText().toString().trim();
                String prodi = spinnerProdi.getSelectedItem().toString().trim();


                String id = databaseMahasiswa.push().getKey();

                Mahasiswa mahasiswa = new Mahasiswa(id, nama, asal, prodi);

                databaseMahasiswa.child(id).setValue(mahasiswa);

                Toast.makeText(MahasiswaActivity.this, "Data berhasil dimasukan",
                        Toast.LENGTH_SHORT).show();

                show.dismiss();
            }
        });
    }

    private void loadRecyclerViewData() {
        databaseMahasiswa.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mahasiswaArrayList.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot mahasiswaSnapshot : dataSnapshot.getChildren()) {
                        Mahasiswa mahasiswa = mahasiswaSnapshot.getValue(Mahasiswa.class);
                        mahasiswaArrayList.add(mahasiswa);
                    }

//                    adapter = new MahasiswaAdapter(mahasiswaArrayList,MahasiswaActivity.this);

                    adapter = new MahasiswaAdapter(mahasiswaArrayList, id);
                    mahasiswaRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//    private void updateMahasiswa(Mahasiswa mahasiswa){
//
//        databaseMahasiswa.child("mahasiswa")
//                .child(mahasiswa.getNamaMahasiswa())
//                .setValue(mahasiswa)
//                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//
//                        Snackbar.make(findViewById(R.id.btn_update_mahasiswa), "Data Mahasiswa Berhasil Diubah", Snackbar.LENGTH_LONG).setAction("Ok", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                finish();
//                            }
//                        }).show();
//
//                    }
//                });
//    }

//    public static Intent getActIntent(Activity activity){
//        return new Intent(activity, MahasiswaActivity.class);
//    }





//    public void showUpdateDialog(final String id, String nama, String asal, String prodi){
//
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
//
//        LayoutInflater inflater = getLayoutInflater();
//
//        final View dialogView = inflater.inflate(R.layout.form_update_mahasiswa,null);
//
//        dialogBuilder.setView(dialogView);
//
//        final EditText editTextNamaMhs = (EditText) dialogView.findViewById(R.id.et_update_name_mhs);
//        final EditText editTextAsalMhs = (EditText) dialogView.findViewById(R.id.et_update_sekolah_mhs);
//        final Spinner spinnerProdiMhs = (Spinner) dialogView.findViewById(R.id.spinner_update_prodi);
//        final Button buttonUpdateMhs = (Button) dialogView.findViewById(R.id.btn_update_mahasiswa);
//
//        buttonUpdateMhs.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//
//                String nama = editTextNamaMhs.getText().toString().trim();
//                String asal = editTextAsalMhs.getText().toString().trim();
//                String prodi = spinnerProdiMhs.getSelectedItem().toString();
//
//                if (TextUtils.isEmpty(nama)){
//                    editTextNamaMhs.setError("Nama required");
//                    return;
//                }
//
//                if (TextUtils.isEmpty(asal)){
//                    editTextAsalMhs.setError("Asal Sekolah required");
//                    return;
//                }
//
//                updateMahasiswa(id, nama, asal, prodi);
//
//            }
//        });
//
//        dialogBuilder.setTitle("Update Data Mahasiswa"+id);
//
//        AlertDialog alertDialog = dialogBuilder.create();
//        alertDialog.show();
//
//    }
//
//    private boolean updateMahasiswa(String id, String nama, String asal, String prodi){
//
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("mahasiswa").child(id);
//
//        Mahasiswa mahasiswa = new Mahasiswa(id, nama, asal, prodi);
//
//        databaseReference.setValue(mahasiswa);
//
//        return true;
//    }
}