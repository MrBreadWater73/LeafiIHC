package com.example.leafiihc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ActividadAdapter extends RecyclerView.Adapter<ActividadAdapter.ActividadViewHolder> {

    private List<AreaVerde.Actividad> actividadesList;
    private Context context;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    public ActividadAdapter(Context context, List<AreaVerde.Actividad> actividadesList) {
        this.context = context;
        this.actividadesList = actividadesList;
    }

    @NonNull
    @Override
    public ActividadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_actividad, parent, false);
        return new ActividadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActividadViewHolder holder, int position) {
        AreaVerde.Actividad actividad = actividadesList.get(position);
        
        holder.tvTipoActividad.setText(actividad.getTipo());
        holder.tvFechaActividad.setText(sdf.format(actividad.getFecha()));
        holder.tvDescripcionActividad.setText(actividad.getDescripcion());
        
        // Configurar icono según el tipo de actividad
        switch (actividad.getTipo()) {
            case "Riego":
                holder.ivIconoActividad.setImageResource(R.drawable.ic_water);
                break;
            case "Fertilización":
                holder.ivIconoActividad.setImageResource(R.drawable.ic_fertilizer);
                break;
            case "Poda":
                holder.ivIconoActividad.setImageResource(R.drawable.ic_pruning);
                break;
            default:
                holder.ivIconoActividad.setImageResource(R.drawable.ic_calendar);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return actividadesList.size();
    }

    public static class ActividadViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIconoActividad;
        TextView tvTipoActividad, tvFechaActividad, tvDescripcionActividad;

        public ActividadViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIconoActividad = itemView.findViewById(R.id.ivIconoActividad);
            tvTipoActividad = itemView.findViewById(R.id.tvTipoActividad);
            tvFechaActividad = itemView.findViewById(R.id.tvFechaActividad);
            tvDescripcionActividad = itemView.findViewById(R.id.tvDescripcionActividad);
        }
    }
}