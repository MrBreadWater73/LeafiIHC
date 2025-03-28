package com.example.leafiihc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TareaAdapter extends RecyclerView.Adapter<TareaAdapter.TareaViewHolder> {

    private List<Tarea> tareasList;
    private Context context;

    public TareaAdapter(Context context, List<Tarea> tareasList) {
        this.context = context;
        this.tareasList = tareasList;
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
        
        holder.tvTareaTitulo.setText(tarea.getTitulo());
        holder.tvTareaDescripcion.setText(tarea.getDescripcion());
        holder.cbTarea.setChecked(tarea.isCompletada());
        
        // Configurar icono de notificación
        if (tarea.isNotificacion()) {
            holder.ivTareaNotification.setImageResource(R.drawable.ic_notification_active);
        } else {
            holder.ivTareaNotification.setImageResource(R.drawable.ic_notification);
        }
        
        // Configurar listener para el checkbox
        holder.cbTarea.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tarea.setCompletada(isChecked);
                if (isChecked) {
                    Toast.makeText(context, "Tarea completada", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        // Configurar listener para la notificación
        holder.ivTareaNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tarea.setNotificacion(!tarea.isNotificacion());
                notifyItemChanged(holder.getAdapterPosition());
                
                if (tarea.isNotificacion()) {
                    Toast.makeText(context, "Notificación activada", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Notificación desactivada", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return tareasList.size();
    }

    public static class TareaViewHolder extends RecyclerView.ViewHolder {
        CheckBox cbTarea;
        TextView tvTareaTitulo, tvTareaDescripcion;
        ImageView ivTareaNotification;

        public TareaViewHolder(@NonNull View itemView) {
            super(itemView);
            cbTarea = itemView.findViewById(R.id.cbTarea);
            tvTareaTitulo = itemView.findViewById(R.id.tvTareaTitulo);
            tvTareaDescripcion = itemView.findViewById(R.id.tvTareaDescripcion);
            ivTareaNotification = itemView.findViewById(R.id.ivTareaNotification);
        }
    }
}