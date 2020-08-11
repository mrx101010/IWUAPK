package com.example.iwuapk.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.iwuapk.R;
import com.example.iwuapk.register.RegisterAdminActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MenuAdminActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);
        findViewById(R.id.ib_dosen).setOnClickListener(this);
        findViewById(R.id.ib_tambah_dosen).setOnClickListener(this);
        findViewById(R.id.ib_tambah_admin).setOnClickListener(this);
        findViewById(R.id.ib_logout).setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ib_dosen:
                Toast.makeText(getApplicationContext(), "User Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_tambah_dosen:
                Toast.makeText(getApplicationContext(), "Register User Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_tambah_admin:
                Intent i = new Intent(MenuAdminActivity.this, RegisterAdminActivity.class);
                startActivity(i);
                break;
            case R.id.ib_logout:
                FirebaseAuth.getInstance().signOut();
                Intent logout = new Intent(MenuAdminActivity.this, LoginActivity.class);
                logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(logout);
                finish();
        }
    }
}