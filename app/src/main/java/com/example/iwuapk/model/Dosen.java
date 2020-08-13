package com.example.iwuapk.model;

public class Dosen {

    private String id_dosen;
    private String nama_dosen;


    public Dosen(String id_dosen, String nama_dosen) {
        this.id_dosen = id_dosen;
        this.nama_dosen = nama_dosen;
    }

    public Dosen() {

    }

    public String getId_dosen() {
        return id_dosen;
    }

    public void setId_dosen(String id_dosen) {
        this.id_dosen = id_dosen;
    }

    public String getNama_dosen() {
        return nama_dosen;
    }

    public void setNama_dosen(String nama_dosen) {
        this.nama_dosen = nama_dosen;
    }
}
