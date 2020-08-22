package com.example.iwuapk.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iwuapk.R;
import com.example.iwuapk.model.Mahasiswa;

import java.util.ArrayList;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MyViewHolder> {



    private ArrayList<Mahasiswa> mahasiswaArrayList;

    public MahasiswaAdapter(ArrayList<Mahasiswa> mahasiswaArrayList) {
        this.mahasiswaArrayList = mahasiswaArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mahasiswa, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Mahasiswa mahasiswa = mahasiswaArrayList.get(position);

        holder.tvNama_mhs.setText(mahasiswa.getNamaMahasiswa());
        holder.tvAsalSekolah_mhs.setText(mahasiswa.getAsalSekolah());
        holder.tvFakultas_mhs.setText(mahasiswa.getProdi());

    }

    @Override
    public int getItemCount() {
        return mahasiswaArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvNama_mhs;
        TextView tvAsalSekolah_mhs;
        TextView tvFakultas_mhs;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNama_mhs = (TextView) itemView.findViewById(R.id.tvNama_Mahasiwa);
            tvAsalSekolah_mhs = (TextView) itemView.findViewById(R.id.tvAsal_sekolah);
            tvFakultas_mhs = (TextView) itemView.findViewById(R.id.tvFalkultas);
        }
    }
}
