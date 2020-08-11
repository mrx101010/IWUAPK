package com.example.iwuapk.layout;
import androidx.appcompat.app.AppCompatActivity;;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.iwuapk.R;
import com.example.iwuapk.login.LoginUserActivity;
import com.example.iwuapk.login.LoginAdminActivity;

public class LoginActivity extends AppCompatActivity {

    ImageButton ibAdmin, ibDosen, ibBantuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        ibAdmin = (ImageButton) findViewById(R.id.ib_admin);
        ibAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, LoginAdminActivity.class);
                startActivity(i);
            }
        });

        ibDosen = (ImageButton) findViewById(R.id.ib_dosen);
        ibDosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, LoginUserActivity.class);
                startActivity(i);
            }
        });
        
        ibBantuan = (ImageButton) findViewById(R.id.ib_bantuan);
        ibBantuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, BantuanActivity.class);
                startActivity(i);
            }
        });
    }
}