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

    private List<AreaVerde.Tarea> tareas;
    private Context context;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    private OnTareaListener onTareaListener;

    public interface OnTareaListener {
        void onTareaCompletadaChange(AreaVerde.Tarea tarea, boolean isChecked);
        void onTareaDelete(AreaVerde.Tarea tarea, int position);
    }

    public TareaAdapter(Context context, List<AreaVerde.Tarea> tareas, OnTareaListener onTareaListener) {
        this.context = context;
        this.tareas = tareas;
        this.onTareaListener = onTareaListener;
    }

    @NonNull
    @Override
    public TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tarea, parent, false);
        return new TareaViewHolder(view, context, onTareaListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TareaViewHolder holder, int position) {
        AreaVerde.Tarea tarea = tareas.get(position);
        holder.bind(tarea);
    }

    @Override
    public int getItemCount() {
        return tareas.size();
    }

    public static class TareaViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitulo;
        private TextView tvDescripcion;
        private TextView tvFechaVencimiento;
        private TextView tvPrioridad;
        private TextView tvCategoria;
        private CheckBox cbTareaCompletada;
        private ImageView ivEliminarTarea;
        private Context context;
        private OnTareaListener onTareaListener;

        public TareaViewHolder(@NonNull View itemView, Context context, OnTareaListener onTareaListener) {
            super(itemView);
            this.context = context;
            this.onTareaListener = onTareaListener;
            tvTitulo = itemView.findViewById(R.id.tvTituloTarea);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcionTarea);
            tvFechaVencimiento = itemView.findViewById(R.id.tvFechaVencimientoTarea);
            tvPrioridad = itemView.findViewById(R.id.tvPrioridadTarea);
            tvCategoria = itemView.findViewById(R.id.tvCategoriaTarea);
            cbTareaCompletada = itemView.findViewById(R.id.cbTareaCompletada);
            ivEliminarTarea = itemView.findViewById(R.id.ivEliminarTarea);
        }

        public void bind(AreaVerde.Tarea tarea) {
            tvTitulo.setText(tarea.getTitulo());
            tvDescripcion.setText(tarea.getDescripcion());
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
            tvFechaVencimiento.setText(sdf.format(tarea.getFechaVencimiento()));
            
            tvPrioridad.setText(tarea.getPrioridad());
            tvCategoria.setText(tarea.getCategoria());

            cbTareaCompletada.setChecked(tarea.isCompletada());

            // Aplicar estilo tachado si la tarea está completada
            if (tarea.isCompletada()) {
                tvTitulo.setPaintFlags(tvTitulo.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                tvDescripcion.setPaintFlags(tvDescripcion.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                tvTitulo.setPaintFlags(tvTitulo.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                tvDescripcion.setPaintFlags(tvDescripcion.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }

            // Configurar color de prioridad
            String prioridad = tarea.getPrioridad() != null ? tarea.getPrioridad() : "Media";
            switch (prioridad) {
                case "Alta":
                    tvPrioridad.setTextColor(context.getResources().getColor(android.R.color.holo_red_light));
                    break;
                case "Media":
                    tvPrioridad.setTextColor(context.getResources().getColor(android.R.color.holo_orange_light));
                    break;
                case "Baja":
                    tvPrioridad.setTextColor(context.getResources().getColor(android.R.color.holo_green_dark));
                    break;
            }

            // Configurar listeners
            cbTareaCompletada.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isChecked = cbTareaCompletada.isChecked();
                    onTareaListener.onTareaCompletadaChange(tarea, isChecked);

                    // Actualizar estilo tachado
                    if (isChecked) {
                        tvTitulo.setPaintFlags(tvTitulo.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        tvDescripcion.setPaintFlags(tvDescripcion.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    } else {
                        tvTitulo.setPaintFlags(tvTitulo.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                        tvDescripcion.setPaintFlags(tvDescripcion.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    }
                }
            });

            ivEliminarTarea.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onTareaListener.onTareaDelete(tarea, getAdapterPosition());
                }
            });
        }
    }
}