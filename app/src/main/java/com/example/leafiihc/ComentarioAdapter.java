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

public class ComentarioAdapter extends RecyclerView.Adapter<ComentarioAdapter.ComentarioViewHolder> {

    private Context context;
    private List<Comentario> comentarios;

    public ComentarioAdapter(Context context, List<Comentario> comentarios) {
        this.context = context;
        this.comentarios = comentarios;
    }

    @NonNull
    @Override
    public ComentarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comentario, parent, false);
        return new ComentarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComentarioViewHolder holder, int position) {
        Comentario comentario = comentarios.get(position);
        
        holder.tvAutor.setText(comentario.getAutor());
        holder.tvContenido.setText(comentario.getContenido());
        
        // Formatear fecha
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        holder.tvFecha.setText(sdf.format(comentario.getFecha()));
    }

    @Override
    public int getItemCount() {
        return comentarios.size();
    }

    static class ComentarioViewHolder extends RecyclerView.ViewHolder {
        ImageView ivAvatar;
        TextView tvAutor, tvContenido, tvFecha;

        public ComentarioViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.ivAvatar);
            tvAutor = itemView.findViewById(R.id.tvAutor);
            tvContenido = itemView.findViewById(R.id.tvContenido);
            tvFecha = itemView.findViewById(R.id.tvFecha);
        }
    }
} 