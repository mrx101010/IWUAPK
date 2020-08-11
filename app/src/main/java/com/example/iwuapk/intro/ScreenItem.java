package com.example.iwuapk.intro;

public class ScreenItem {
    String Judul, Deskripsi;
    int ScreenImg;

    public ScreenItem(String judul, String deskripsi, int screenImg) {
        Judul = judul;
        Deskripsi = deskripsi;
        ScreenImg = screenImg;
    }

    public String getJudul() {
        return Judul;
    }

    public void setJudul(String judul) {
        Judul = judul;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }

    public int getScreenImg() {
        return ScreenImg;
    }

    public void setScreenImg(int screenImg) {
        ScreenImg = screenImg;
    }
}
