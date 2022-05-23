package com.example.pruebadatabase;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebadatabase.data.DatoEntity;

import java.util.List;

public class AdapterRecylerView extends RecyclerView.Adapter<AdapterRecylerView.ViewHolder> {

    List<DatoEntity> datoEntityList;
    Activity context;

    public AdapterRecylerView(List<DatoEntity> datoEntityList, Activity context) {
        this.datoEntityList = datoEntityList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterRecylerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecylerView.ViewHolder holder, int position) {

        DatoEntity item = datoEntityList.get(position);
        holder.textView.setText("Nombre: " + item.getName() + " | Edad: " + item.getAge());


    }

    @Override
    public int getItemCount() {
        return datoEntityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textViewNombreItem);
        }
    }
}
