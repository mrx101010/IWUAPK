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
import com.example.iwuapk.layout.MahasiswaActivity;
import com.example.iwuapk.model.Dosen;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DosenAdapter extends RecyclerView.Adapter<DosenAdapter.ViewHolder> {

    public static final String DOSEN_ID = "dosenid";
    public static final String DOSEN_NAME = "dosenname";
    private ArrayList<Dosen> dosenArrayList;

    public DosenAdapter(ArrayList<Dosen> dosenArrayList) {
        this.dosenArrayList = dosenArrayList;
    }

    @Override
    public DosenAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dosen, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DosenAdapter.ViewHolder holder, final int position) {

        holder.tv_NamaDosen.setText(dosenArrayList.get(position).getNama_dosen());
        holder.cvItemDosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dosen dosen = dosenArrayList.get(position);

                Context context = view.getContext();

                Intent intent = new Intent(context, MahasiswaActivity.class);
                intent.putExtra(DOSEN_ID, dosen.getId_dosen());
                intent.putExtra(DOSEN_NAME, dosen.getNama_dosen());

                context.startActivity(intent);
            }
        });

        holder.cvBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dosen dosen = dosenArrayList.get(position);

                Context context = view.getContext();

                DatabaseReference drDosen = FirebaseDatabase.getInstance()
                        .getReference("dosen").child(dosen.getId_dosen());
                DatabaseReference drMahasiswa = FirebaseDatabase.getInstance()
                        .getReference("mahasiswa").child(dosen.getId_dosen());
                drDosen.removeValue();
                drMahasiswa.removeValue();
                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        holder.cvBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return dosenArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_NamaDosen;
        CardView cvItemDosen, cvBtnUpdate, cvBtnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvItemDosen = itemView.findViewById(R.id.cv_itemDosen);
            tv_NamaDosen = (TextView) itemView.findViewById(R.id.tvNama_dosen);
            cvBtnUpdate = (CardView) itemView.findViewById(R.id.cv_btn_update);
            cvBtnDelete = (CardView) itemView.findViewById(R.id.cv_btn_delete);
        }
    }
}