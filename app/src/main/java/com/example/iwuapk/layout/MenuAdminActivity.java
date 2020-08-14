package com.example.iwuapk.layout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.iwuapk.R;
import com.example.iwuapk.register.RegisterAdminActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MenuAdminActivity extends AppCompatActivity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);
        findViewById(R.id.ib_dosen).setOnClickListener(this);
        findViewById(R.id.ib_tambah_dosen).setOnClickListener(this);
        findViewById(R.id.ib_logout).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ib_dosen:
                Intent dosen = new Intent(getApplicationContext(), DosenActivity.class);
                startActivity(dosen);
                break;
            case R.id.ib_tambah_dosen:
                Intent i = new Intent(MenuAdminActivity.this, RegisterAdminActivity.class);
                startActivity(i);
                break;
            case R.id.ib_logout:
                final AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Keluar")
                        .setMessage("Apa kamu yakin ingin keluar?")
                        .setPositiveButton("Iya", null)
                        .setNegativeButton("Tidak", null)
                        .show();

                Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                negativeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("rememberAdmin", "false");
                        editor.apply();

                        finish();
                    }
                });
        }
    }
}