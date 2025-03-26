package com.example.leafiihc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PlantaAdapter extends RecyclerView.Adapter<PlantaAdapter.PlantaViewHolder> {

    private List<Planta> plantasList;
    private List<Planta> plantasListFull;
    private Context context;
    private OnPlantaClickListener listener;

    public interface OnPlantaClickListener {
        void onPlantaClick(Planta planta, int position);
    }

    public PlantaAdapter(Context context, List<Planta> plantasList, OnPlantaClickListener listener) {
        this.context = context;
        this.plantasList = plantasList;
        this.plantasListFull = new ArrayList<>(plantasList);
        this.listener = listener;
    }

    @NonNull
    @Override
    public PlantaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_planta, parent, false);
        return new PlantaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantaViewHolder holder, int position) {
        Planta planta = plantasList.get(position);

        holder.tvPlantaNombre.setText(planta.getNombre());
        holder.tvPlantaNombreCientifico.setText(planta.getNombreCientifico());
        holder.tvPlantaDescripcion.setText(planta.getDescripcion());
        holder.ivPlantaImagen.setImageResource(planta.getImagenResourceId());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onPlantaClick(planta, holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return plantasList.size();
    }

    public void filter(String text) {
        plantasList.clear();
        if (text.isEmpty()) {
            plantasList.addAll(plantasListFull);
        } else {
            text = text.toLowerCase();
            for (Planta planta : plantasListFull) {
                if (planta.getNombre().toLowerCase().contains(text) ||
                        planta.getNombreCientifico().toLowerCase().contains(text)) {
                    plantasList.add(planta);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class PlantaViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPlantaImagen;
        TextView tvPlantaNombre, tvPlantaNombreCientifico, tvPlantaDescripcion;

        public PlantaViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPlantaImagen = itemView.findViewById(R.id.ivPlantaImagen);
            tvPlantaNombre = itemView.findViewById(R.id.tvPlantaNombre);
            tvPlantaNombreCientifico = itemView.findViewById(R.id.tvPlantaNombreCientifico);
            tvPlantaDescripcion = itemView.findViewById(R.id.tvPlantaDescripcion);
        }
    }
}