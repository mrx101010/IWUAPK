package com.example.iwuapk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iwuapk.R;
import com.example.iwuapk.model.Mahasiswa;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MyViewHolder> {

    private ArrayList<Mahasiswa> mahasiswaArrayList;
    private String id;

    public MahasiswaAdapter(ArrayList<Mahasiswa> mahasiswaArrayList, String id) {
        this.mahasiswaArrayList = mahasiswaArrayList;
        this.id = id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mahasiswa, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        Mahasiswa mahasiswa = mahasiswaArrayList.get(position);

        holder.tvNama_mhs.setText(mahasiswa.getNamaMahasiswa());
        holder.tvAsalSekolah_mhs.setText(mahasiswa.getAsalSekolah());
        holder.tvFakultas_mhs.setText(mahasiswa.getProdi());

        holder.cvBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mahasiswa mahasiswa1 = mahasiswaArrayList.get(position);
                Context context = view.getContext();

                DatabaseReference drMahasiswa = FirebaseDatabase.getInstance()
                        .getReference("mahasiswa")
                        .child(id)
                        .child(mahasiswa1.getIdMahasiswa());
                drMahasiswa.removeValue();
                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mahasiswaArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvNama_mhs;
        TextView tvAsalSekolah_mhs;
        TextView tvFakultas_mhs;
        CardView cvBtnUpdate, cvBtnDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNama_mhs = (TextView) itemView.findViewById(R.id.tvNama_Mahasiwa);
            tvAsalSekolah_mhs = (TextView) itemView.findViewById(R.id.tvAsal_sekolah);
            tvFakultas_mhs = (TextView) itemView.findViewById(R.id.tvFalkultas);
            cvBtnUpdate = (CardView) itemView.findViewById(R.id.cv_btn_update);
            cvBtnDelete = (CardView) itemView.findViewById(R.id.cv_btn_delete);
        }
    }
}
