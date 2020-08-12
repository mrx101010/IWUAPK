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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TambahDosenAdapter extends RecyclerView.Adapter<TambahDosenAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Dosen> dosenArrayList;

    public TambahDosenAdapter(Context ctx, ArrayList<Dosen>dosenArrayList){
        inflater = LayoutInflater.from(ctx);
        this.dosenArrayList = dosenArrayList;
    }

    @Override
    public TambahDosenAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_tambah_dosen, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TambahDosenAdapter.ViewHolder holder, int position) {

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
