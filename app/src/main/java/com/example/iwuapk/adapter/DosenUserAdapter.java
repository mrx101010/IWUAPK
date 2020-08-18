package com.example.iwuapk.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iwuapk.R;
import com.example.iwuapk.layout.MahasiswaActivity;
import com.example.iwuapk.layout.MahasiswaUserActivity;
import com.example.iwuapk.model.Dosen;

import java.util.ArrayList;

public class DosenUserAdapter extends RecyclerView.Adapter<DosenUserAdapter.ViewHolder> {

    public static final String DOSEN_IDD = "dosenid";
    public static final String DOSEN_NAMEE = "dosenname";
    private ArrayList<Dosen> dosenArrayList;

    public DosenUserAdapter(ArrayList<Dosen> dosenArrayList) {
        this.dosenArrayList = dosenArrayList;
    }

    @Override
    public DosenUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dosen, parent, false);

        return new DosenUserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DosenUserAdapter.ViewHolder holder, final int position) {

        holder.tv_NamaDosen.setText(dosenArrayList.get(position).getNama_dosen());
        holder.cvItemDosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dosen dosen = dosenArrayList.get(position);

                Context context = view.getContext();

                Intent intent = new Intent(context, MahasiswaUserActivity.class);
                intent.putExtra(DOSEN_IDD, dosen.getId_dosen());
                intent.putExtra(DOSEN_NAMEE, dosen.getNama_dosen());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dosenArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_NamaDosen;
        CardView cvItemDosen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvItemDosen = itemView.findViewById(R.id.cv_itemDosen);
            tv_NamaDosen = (TextView) itemView.findViewById(R.id.tvNama_dosen);
        }
    }
}