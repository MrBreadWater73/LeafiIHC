package com.example.leafiihc;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Date;

public class TareaAdapter extends RecyclerView.Adapter<TareaAdapter.TareaViewHolder> {

    private List<Tarea> tareasList;
    private Context context;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    private OnTareaListener onTareaListener;

    public interface OnTareaListener {
        void onTareaCompletadaChange(Tarea tarea, boolean isChecked);
        void onTareaDelete(Tarea tarea, int position);
    }

    // Constructor con listener
    public TareaAdapter(Context context, List<Tarea> tareasList, OnTareaListener onTareaListener) {
        this.context = context;
        this.tareasList = tareasList;
        this.onTareaListener = onTareaListener;
    }

    // Constructor sin listener para compatibilidad
    public TareaAdapter(Context context, List<Tarea> tareasList) {
        this.context = context;
        this.tareasList = tareasList;
        // Implementación vacía del listener para evitar NullPointerException
        this.onTareaListener = new OnTareaListener() {
            @Override
            public void onTareaCompletadaChange(Tarea tarea, boolean isChecked) {
                // No hacer nada
            }

            @Override
            public void onTareaDelete(Tarea tarea, int position) {
                // No hacer nada
            }
        };
    }

    @NonNull
    @Override
    public TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tarea, parent, false);
        return new TareaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TareaViewHolder holder, int position) {
        Tarea tarea = tareasList.get(position);

        holder.tvTituloTarea.setText(tarea.getTitulo());
        holder.tvDescripcionTarea.setText(tarea.getDescripcion());

        // Verificar si la categoría es null (para compatibilidad con versiones anteriores)
        if (tarea.getCategoria() != null) {
            holder.tvCategoriaTarea.setText(tarea.getCategoria());
        } else {
            holder.tvCategoriaTarea.setText("Otro");
        }

        // Usar getFecha() o getFechaVencimiento() según disponibilidad
        Date fechaParaMostrar = tarea.getFechaVencimiento() != null ? tarea.getFechaVencimiento() : tarea.getFecha();
        holder.tvFechaVencimiento.setText(sdf.format(fechaParaMostrar));

        // Verificar si la prioridad es null
        if (tarea.getPrioridad() != null) {
            holder.tvPrioridadTarea.setText(tarea.getPrioridad());
        } else {
            holder.tvPrioridadTarea.setText("Media");
        }

        holder.cbTareaCompletada.setChecked(tarea.isCompletada());

        // Aplicar estilo tachado si la tarea está completada
        if (tarea.isCompletada()) {
            holder.tvTituloTarea.setPaintFlags(holder.tvTituloTarea.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvDescripcionTarea.setPaintFlags(holder.tvDescripcionTarea.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.tvTituloTarea.setPaintFlags(holder.tvTituloTarea.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            holder.tvDescripcionTarea.setPaintFlags(holder.tvDescripcionTarea.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

        // Configurar color de prioridad
        String prioridad = tarea.getPrioridad() != null ? tarea.getPrioridad() : "Media";
        switch (prioridad) {
            case "Alta":
                holder.tvPrioridadTarea.setTextColor(context.getResources().getColor(android.R.color.holo_red_light));
                break;
            case "Media":
                holder.tvPrioridadTarea.setTextColor(context.getResources().getColor(android.R.color.holo_orange_light));
                break;
            case "Baja":
                holder.tvPrioridadTarea.setTextColor(context.getResources().getColor(android.R.color.holo_green_dark));
                break;
        }

        // Configurar listeners
        holder.cbTareaCompletada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = holder.cbTareaCompletada.isChecked();
                onTareaListener.onTareaCompletadaChange(tarea, isChecked);

                // Actualizar estilo tachado
                if (isChecked) {
                    holder.tvTituloTarea.setPaintFlags(holder.tvTituloTarea.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    holder.tvDescripcionTarea.setPaintFlags(holder.tvDescripcionTarea.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    holder.tvTituloTarea.setPaintFlags(holder.tvTituloTarea.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    holder.tvDescripcionTarea.setPaintFlags(holder.tvDescripcionTarea.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                }
            }
        });

        holder.ivEliminarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTareaListener.onTareaDelete(tarea, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return tareasList.size();
    }

    public static class TareaViewHolder extends RecyclerView.ViewHolder {
        CheckBox cbTareaCompletada;
        TextView tvTituloTarea, tvDescripcionTarea, tvCategoriaTarea, tvFechaVencimiento, tvPrioridadTarea;
        ImageView ivEliminarTarea;

        public TareaViewHolder(@NonNull View itemView) {
            super(itemView);
            cbTareaCompletada = itemView.findViewById(R.id.cbTareaCompletada);
            tvTituloTarea = itemView.findViewById(R.id.tvTituloTarea);
            tvDescripcionTarea = itemView.findViewById(R.id.tvDescripcionTarea);
            tvCategoriaTarea = itemView.findViewById(R.id.tvCategoriaTarea);
            tvFechaVencimiento = itemView.findViewById(R.id.tvFechaVencimiento);
            tvPrioridadTarea = itemView.findViewById(R.id.tvPrioridadTarea);
            ivEliminarTarea = itemView.findViewById(R.id.ivEliminarTarea);
        }
    }
}