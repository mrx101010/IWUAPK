package com.example.iwuapk.intro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.iwuapk.R;
import com.example.iwuapk.layout.LoginActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager viewPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndicator;
    Button btnLanjut, btnMulai;
    int position = 0;
    Animation btnAnim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        if (restorePrefData()){
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
            finish();
        }


//        ini views
        btnLanjut = findViewById(R.id.btn_lanjut);
        btnMulai = findViewById(R.id.btn_mulai);
        tabIndicator = findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);

        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Perkenalan", "Selamat datang di aplikasi IWU!\n Aplikasi ini berfungsi untuk melihat data mahasiswa baru yang dosen masukan ke International Women's University!", R.drawable.logo));
        mList.add(new ScreenItem("Admin", "Admin berfungsi mengatur data dosen dan mahasiswa yang bisa diinputkan di aplikasi ini!", R.drawable.admin));
        mList.add(new ScreenItem("Dosen", "Dosen hanya dapat melihat data dosen siapa saja yang dimasukkan ke aplikasi melalui izin admin!", R.drawable.dosen));


        // setup ViewPager
        viewPager = findViewById(R.id.screen_view_pager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);
        viewPager.setAdapter(introViewPagerAdapter);

//        setup tablayout with viewpager
        tabIndicator.setupWithViewPager(viewPager);

//        next button
        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                position = viewPager.getCurrentItem();
                if (position < mList.size()){
                    position++;
                    viewPager.setCurrentItem(position);
                }

                if (position == mList.size()-1){
//                    TODO  : show the GETSTARTED Button and hide the indicator and the next button

                    loadLastScreen();
                }

            }
        });

//        tablayout add change listener
        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == mList.size()-1){
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        btnMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                open main activity
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);

                savePrefsData();
                finish();
            }
        });

    }

    private boolean restorePrefData() {
     SharedPreferences preferences = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
     Boolean isIntroActivityOpenedBefore = preferences.getBoolean("isIntroOpened", false);
     return isIntroActivityOpenedBefore;


    }

    private void savePrefsData() {

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isIntroOpened", true);
        editor.commit();

    }

    private void loadLastScreen() {
        btnLanjut.setVisibility(View.INVISIBLE);
        btnMulai.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.VISIBLE);
        btnMulai.setAnimation(btnAnim);
    }
}