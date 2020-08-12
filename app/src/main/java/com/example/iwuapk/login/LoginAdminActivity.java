package com.example.iwuapk.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iwuapk.R;
import com.example.iwuapk.layout.MenuAdminActivity;

public class LoginAdminActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;
    CheckBox cbIngatSaya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        cbIngatSaya = findViewById(R.id.cb_ingatsaya);
        btnLogin = findViewById(R.id.btn_login);

        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("remember", "");
        if (checkbox.equals("true")){
            Intent intent = new Intent(getApplicationContext(), MenuAdminActivity.class);
            startActivity(intent);
            finish();
        }else if (checkbox.equals("false")){
            Toast.makeText(getApplicationContext(), "Selamat datang!", Toast.LENGTH_SHORT).show();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (username.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Email dibutuhkan", Toast.LENGTH_SHORT).show();
                    etUsername.requestFocus();
                    return;
                }

                if (password.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Password dibutuhkan", Toast.LENGTH_SHORT).show();
                    etPassword.requestFocus();
                    return;
                }
                if (password.length()<6){
                    Toast.makeText(getApplicationContext(), "Password harus lebih dari 6 karakter", Toast.LENGTH_SHORT).show();
                    etPassword.requestFocus();
                    return;
                }

                if (username.equals("admin") && password.equals("123456")){
                    Toast.makeText(getApplicationContext(), "Selamat datang, Admin!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MenuAdminActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Username dan password kamu salah!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cbIngatSaya.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "true");
                    editor.apply();
                    Toast.makeText(getApplicationContext(), "Checked!", Toast.LENGTH_SHORT).show();

                }else if (!compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "false");
                    editor.apply();
                    Toast.makeText(getApplicationContext(), "Unchecked!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}