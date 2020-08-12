package com.example.iwuapk.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.iwuapk.R;

public class MenuUserActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_user);

        findViewById(R.id.ib_dosen).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ib_dosen:
                Intent dosen = new Intent(getApplicationContext(), DosenActivity.class);
                startActivity(dosen);
        }
    }
}