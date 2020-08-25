package com.example.iwuapk.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iwuapk.R;
import com.example.iwuapk.layout.MahasiswaUpdateActivity;
import com.example.iwuapk.model.Mahasiswa;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MyViewHolder> {

    public static final String MAHASISWA_ID = "mahasiswaid";
    public static final String DOSEN_ID = "dosenid";
    public static final String MAHASISWA_NAME = "mahasiswaname";
    public static final String MAHASISWA_ASAL = "mahasiswaasal";
//    public static final String MAHASISWA_PRODI = "mahasiswaprodi";

    private Context mcontext;

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
    public void onBindViewHolder(@NonNull MahasiswaAdapter.MyViewHolder holder, final int position) {

        final Mahasiswa mahasiswa = mahasiswaArrayList.get(position);

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

        holder.cvUpdateMahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, MahasiswaUpdateActivity.class);
                intent.putExtra(DOSEN_ID, id);
                intent.putExtra(MAHASISWA_ID, mahasiswa.getIdMahasiswa());
                intent.putExtra(MAHASISWA_NAME, mahasiswa.getNamaMahasiswa());
                intent.putExtra(MAHASISWA_ASAL, mahasiswa.getAsalSekolah());
//                intent.putExtra(MAHASISWA_PRODI, mahasiswa.getProdi());

                context.startActivity(intent);
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
        CardView cvBtnDelete;
        CardView cvUpdateMahasiswa;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNama_mhs = (TextView) itemView.findViewById(R.id.tvNama_Mahasiwa);
            tvAsalSekolah_mhs = (TextView) itemView.findViewById(R.id.tvAsal_sekolah);
            tvFakultas_mhs = (TextView) itemView.findViewById(R.id.tvFalkultas);
            cvBtnDelete = (CardView) itemView.findViewById(R.id.cv_btn_delete_mhs);
            cvUpdateMahasiswa = itemView.findViewById(R.id.cv_btn_update_mhs);
        }
    }
}

