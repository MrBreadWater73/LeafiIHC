package com.example.leafiihc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class EnfermedadAdapter extends RecyclerView.Adapter<EnfermedadAdapter.EnfermedadViewHolder> {

    private Context context;
    private List<Enfermedad> enfermedadesList;

    public EnfermedadAdapter(Context context, List<Enfermedad> enfermedadesList) {
        this.context = context;
        this.enfermedadesList = enfermedadesList;
    }

    @NonNull
    @Override
    public EnfermedadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_enfermedad, parent, false);
        return new EnfermedadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EnfermedadViewHolder holder, int position) {
        Enfermedad enfermedad = enfermedadesList.get(position);

        holder.tvNombre.setText(enfermedad.getNombre());
        holder.tvNombreCientifico.setText(enfermedad.getNombreCientifico());
        holder.tvDescripcion.setText(enfermedad.getDescripcion());
        holder.tvSolucion.setText(enfermedad.getSolucion());
        holder.tvPlantasAfectadas.setText("Plantas afectadas: " + enfermedad.getPlantasAfectadas());

        // Cargar imagen desde recursos locales
        holder.ivEnfermedad.setImageResource(enfermedad.getImagenResourceId());
    }

    @Override
    public int getItemCount() {
        return enfermedadesList.size();
    }

    public static class EnfermedadViewHolder extends RecyclerView.ViewHolder {
        ImageView ivEnfermedad;
        TextView tvNombre;
        TextView tvNombreCientifico;
        TextView tvDescripcion;
        TextView tvSolucion;
        TextView tvPlantasAfectadas;

        public EnfermedadViewHolder(@NonNull View itemView) {
            super(itemView);
            ivEnfermedad = itemView.findViewById(R.id.ivEnfermedad);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvNombreCientifico = itemView.findViewById(R.id.tvNombreCientifico);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvSolucion = itemView.findViewById(R.id.tvSolucion);
            tvPlantasAfectadas = itemView.findViewById(R.id.tvPlantasAfectadas);
        }
    }
} 