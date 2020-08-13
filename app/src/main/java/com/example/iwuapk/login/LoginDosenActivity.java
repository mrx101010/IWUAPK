package com.example.iwuapk.login;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.iwuapk.R;
import com.example.iwuapk.layout.MenuAdminActivity;
import com.example.iwuapk.layout.MenuUserActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginDosenActivity extends AppCompatActivity implements View.OnClickListener {
    FirebaseAuth firebaseAuth;
    EditText etEmail, etPassword;
    CheckBox cbIngatSaya;
    ProgressBar pbMasuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_dosen);
        pbMasuk = (ProgressBar) findViewById(R.id.pb_masuk);
        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_password);
        cbIngatSaya = (CheckBox) findViewById(R.id.cb_ingatsaya);


        firebaseAuth = FirebaseAuth.getInstance();

        findViewById(R.id.btn_mulai).setOnClickListener(this);

        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("rememberDosen", "");
        if (checkbox.equals("true")){
            Intent intent = new Intent(getApplicationContext(), MenuUserActivity.class);
            startActivity(intent);
            finish();
        }else if (checkbox.equals("false")){
            Toast.makeText(getApplicationContext(), "Selamat datang, Dosen!", Toast.LENGTH_SHORT).show();
        }

        cbIngatSaya.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("rememberDosen", "true");
                    editor.apply();
                    Toast.makeText(getApplicationContext(), "Checked!", Toast.LENGTH_SHORT).show();

                }else if (!compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("rememberDosen", "false");
                    editor.apply();
                    Toast.makeText(getApplicationContext(), "Unchecked!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_mulai:
                LoginDosen();
                break;
        }
    }

    private void LoginDosen() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty()){
            Toast.makeText(LoginDosenActivity.this, "Email dibutuhkan", Toast.LENGTH_SHORT).show();
            etEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(LoginDosenActivity.this, "Tolong masukan email yang valid", Toast.LENGTH_SHORT).show();
            etEmail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            Toast.makeText(LoginDosenActivity.this, "Password dibutuhkan", Toast.LENGTH_SHORT).show();
            etPassword.requestFocus();
            return;
        }
        if (password.length()<6){
            Toast.makeText(LoginDosenActivity.this, "Password harus lebih dari 6 karakter", Toast.LENGTH_SHORT).show();
            etPassword.requestFocus();
            return;
        }

        pbMasuk.setVisibility(View.VISIBLE);

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                pbMasuk.setVisibility(View.GONE);
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Kamu berhasil login!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginDosenActivity.this, MenuUserActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "Username dan password kamu salah!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}