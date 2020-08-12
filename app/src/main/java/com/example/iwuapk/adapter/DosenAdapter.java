package com.example.iwuapk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iwuapk.R;
import com.example.iwuapk.model.Dosen;

import java.util.ArrayList;

public class DosenAdapter extends RecyclerView.Adapter<DosenAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Dosen> dosenArrayList;

    public DosenAdapter(Context ctx, ArrayList<Dosen>dosenArrayList){
        inflater = LayoutInflater.from(ctx);
        this.dosenArrayList = dosenArrayList;
    }

    @Override
    public DosenAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_dosen, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DosenAdapter.ViewHolder holder, int position) {

        holder.tv_NamaDosen.setText(dosenArrayList.get(position).getNama_dosen());
    }

    @Override
    public int getItemCount() {
        return dosenArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_NamaDosen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_NamaDosen = (TextView) itemView.findViewById(R.id.tvNama_dosen);
        }
    }
}
