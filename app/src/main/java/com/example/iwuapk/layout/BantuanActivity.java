package com.example.iwuapk.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.iwuapk.R;

public class BantuanActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bantuan);

        findViewById(R.id.btn_whatsapp).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_whatsapp:
                Intent toWhatsApp = new Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/085748537214"));
                startActivity(toWhatsApp);
        }
    }
}