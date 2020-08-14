package com.example.iwuapk.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.iwuapk.R;
import com.example.iwuapk.layout.MenuAdminActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class RegisterAdminActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    EditText etEmail, etPassword;
    ProgressBar pbTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_dosen);
        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_password);
        pbTambah = (ProgressBar) findViewById(R.id.pb_tambah);

        findViewById(R.id.btn_tambah).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
    switch (view.getId()){
        case R.id.btn_tambah:
            TambahAdmin();
            break;
    }

    }

    private void TambahAdmin() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty()){
            Toast.makeText(RegisterAdminActivity.this, "Email dibutuhkan", Toast.LENGTH_SHORT).show();
            etEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(RegisterAdminActivity.this, "Tolong masukan email yang valid", Toast.LENGTH_SHORT).show();
            etEmail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            Toast.makeText(RegisterAdminActivity.this, "Password dibutuhkan", Toast.LENGTH_SHORT).show();
            etPassword.requestFocus();
            return;
        }
        if (password.length()<6){
            Toast.makeText(RegisterAdminActivity.this, "Password harus lebih dari 6 karakter", Toast.LENGTH_SHORT).show();
            etPassword.requestFocus();
            return;
        }

        pbTambah.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                pbTambah.setVisibility(View.GONE);
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Admin berhasil terdaftar!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(RegisterAdminActivity.this, MenuAdminActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(), "Akun kamu sudah terdaftar!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}