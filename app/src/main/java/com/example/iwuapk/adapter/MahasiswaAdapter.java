package com.example.iwuapk.adapter;

import android.content.Context;
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

    private LayoutInflater inflater;
    private ArrayList<Mahasiswa> mahasiswaArrayList;

    public MahasiswaAdapter(Context ctx, ArrayList<Mahasiswa>mahasiswaArrayList){
        inflater = LayoutInflater.from(ctx);
        this.mahasiswaArrayList = mahasiswaArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.item_mahasiswa,parent,false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvNama_mhs.setText(mahasiswaArrayList.get(position).getNamaMahasiswa());
        holder.tvAsalSekolah_mhs.setText(mahasiswaArrayList.get(position).getAsalSekolah());
        holder.tvFakultas_mhs.setText(mahasiswaArrayList.get(position).getFakultas());

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
