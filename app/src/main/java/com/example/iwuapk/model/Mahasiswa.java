package com.example.iwuapk.model;

public class Mahasiswa {
    private String idMahasiswa;
    private String namaMahasiswa;
    private String asalSekolah;
    private String prodi;

    public Mahasiswa() {

    }

    public Mahasiswa(String idMahasiswa, String namaMahasiswa, String asalSekolah, String prodi) {
        this.idMahasiswa = idMahasiswa;
        this.namaMahasiswa = namaMahasiswa;
        this.asalSekolah = asalSekolah;
        this.prodi = prodi;
    }

    public String getNamaMahasiswa() {
        return namaMahasiswa;
    }

    public void setNamaMahasiswa(String namaMahasiswa) {
        this.namaMahasiswa = namaMahasiswa;
    }

    public String getAsalSekolah() {
        return asalSekolah;
    }

    public void setAsalSekolah(String asalSekolah) {
        this.asalSekolah = asalSekolah;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }
}
