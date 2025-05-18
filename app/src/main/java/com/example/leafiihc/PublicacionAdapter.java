package com.example.leafiihc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class PublicacionAdapter extends RecyclerView.Adapter<PublicacionAdapter.PublicacionViewHolder> {

    private Context context;
    private List<Publicacion> publicaciones;
    private OnPublicacionClickListener listener;

    public interface OnPublicacionClickListener {
        void onLikeClick(Publicacion publicacion, int position);
        void onCommentClick(Publicacion publicacion, int position);
        void onShareClick(Publicacion publicacion, int position);
    }

    public PublicacionAdapter(Context context, List<Publicacion> publicaciones, OnPublicacionClickListener listener) {
        this.context = context;
        this.publicaciones = publicaciones;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PublicacionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_publicacion, parent, false);
        return new PublicacionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PublicacionViewHolder holder, int position) {
        Publicacion publicacion = publicaciones.get(position);
        
        holder.tvAutor.setText(publicacion.getAutor());
        holder.tvTitulo.setText(publicacion.getTitulo());
        holder.tvContenido.setText(publicacion.getContenido());
        holder.tvLikes.setText(String.valueOf(publicacion.getLikes()));
        holder.tvComentarios.setText(String.valueOf(publicacion.getComentarios()));
        
        // Formatear fecha
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        holder.tvFecha.setText(sdf.format(publicacion.getFecha()));
        
        // Configurar estado de me gusta
        holder.btnLike.setImageResource(publicacion.isMeGusta() ? 
            R.drawable.ic_like_red : R.drawable.ic_like);
        holder.btnLike.setColorFilter(publicacion.isMeGusta() ? 
            context.getResources().getColor(R.color.red) : 
            context.getResources().getColor(R.color.gray));
        
        // Configurar listeners
        holder.btnLike.setOnClickListener(v -> {
            if (listener != null) {
                listener.onLikeClick(publicacion, position);
            }
        });
        
        holder.btnComentar.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCommentClick(publicacion, position);
            }
        });
        
        holder.btnCompartir.setOnClickListener(v -> {
            if (listener != null) {
                listener.onShareClick(publicacion, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return publicaciones.size();
    }

    static class PublicacionViewHolder extends RecyclerView.ViewHolder {
        TextView tvAutor, tvFecha, tvTitulo, tvContenido, tvLikes, tvComentarios;
        ImageButton btnLike, btnComentar, btnCompartir;

        public PublicacionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAutor = itemView.findViewById(R.id.tvAutor);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvContenido = itemView.findViewById(R.id.tvContenido);
            tvLikes = itemView.findViewById(R.id.tvLikes);
            tvComentarios = itemView.findViewById(R.id.tvComentarios);
            btnLike = itemView.findViewById(R.id.btnLike);
            btnComentar = itemView.findViewById(R.id.btnComentar);
            btnCompartir = itemView.findViewById(R.id.btnCompartir);
        }
    }
}