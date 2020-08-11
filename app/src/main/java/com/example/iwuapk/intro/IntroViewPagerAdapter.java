package com.example.iwuapk.intro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.iwuapk.R;

import java.util.List;

public class IntroViewPagerAdapter extends PagerAdapter {

    Context context;
    List<ScreenItem> listScreen;

    public IntroViewPagerAdapter(Context context, List<ScreenItem> listScreen) {
        this.context = context;
        this.listScreen = listScreen;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.layout_screen, null);

        ImageView imgSlide = layoutScreen.findViewById(R.id.iv_slide);
        TextView tvJudul = layoutScreen.findViewById(R.id.tv_judul);
        TextView tvDeskripsi = layoutScreen.findViewById(R.id.tv_desk);

        tvJudul.setText(listScreen.get(position).getJudul());
        tvDeskripsi.setText(listScreen.get(position).getDeskripsi());
        imgSlide.setImageResource(listScreen.get(position).getScreenImg());

        container.addView(layoutScreen);

        return layoutScreen;

    }

    @Override
    public int getCount() {
        return listScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}