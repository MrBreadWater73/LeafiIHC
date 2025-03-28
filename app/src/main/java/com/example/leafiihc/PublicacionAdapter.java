package com.example.leafiihc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PublicacionAdapter extends RecyclerView.Adapter<PublicacionAdapter.PublicacionViewHolder> {

    private List<Publicacion> publicacionesList;
    private Context context;
    private OnPublicacionClickListener listener;

    public interface OnPublicacionClickListener {
        void onLikeClick(Publicacion publicacion, int position);
        void onCommentClick(Publicacion publicacion, int position);
        void onShareClick(Publicacion publicacion, int position);
    }

    public PublicacionAdapter(Context context, List<Publicacion> publicacionesList, OnPublicacionClickListener listener) {
        this.context = context;
        this.publicacionesList = publicacionesList;
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
        Publicacion publicacion = publicacionesList.get(position);
        
        holder.tvPublicacionUsuario.setText(publicacion.getUsuario());
        holder.tvPublicacionTitulo.setText(publicacion.getTitulo());
        holder.tvPublicacionContenido.setText(publicacion.getContenido());
        holder.tvPublicacionLikes.setText(String.valueOf(publicacion.getLikes()));
        holder.tvPublicacionComments.setText(String.valueOf(publicacion.getComentarios()));
        
        // Formatear fecha
        holder.tvPublicacionFecha.setText(formatearFecha(publicacion.getFecha()));
        
        // Mostrar imagen si existe
        if (publicacion.getImagenUrl() != null) {
            holder.ivPublicacionImagen.setVisibility(View.VISIBLE);
            // Aquí cargarías la imagen con Glide o Picasso
        } else {
            holder.ivPublicacionImagen.setVisibility(View.GONE);
        }
        
        // Configurar listeners
        holder.llPublicacionLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onLikeClick(publicacion, holder.getAdapterPosition());
                }
            }
        });
        
        holder.llPublicacionComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onCommentClick(publicacion, holder.getAdapterPosition());
                }
            }
        });
        
        holder.llPublicacionShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onShareClick(publicacion, holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return publicacionesList.size();
    }

    private String formatearFecha(Date fecha) {
        Date ahora = new Date();
        long diferencia = ahora.getTime() - fecha.getTime();
        long segundos = diferencia / 1000;
        long minutos = segundos / 60;
        long horas = minutos / 60;
        long dias = horas / 24;
        
        if (dias > 0) {
            return "Hace " + dias + (dias == 1 ? " día" : " días");
        } else if (horas > 0) {
            return "Hace " + horas + (horas == 1 ? " hora" : " horas");
        } else if (minutos > 0) {
            return "Hace " + minutos + (minutos == 1 ? " minuto" : " minutos");
        } else {
            return "Hace un momento";
        }
    }

    public static class PublicacionViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPublicacionAvatar, ivPublicacionMenu, ivPublicacionImagen;
        ImageView ivPublicacionLike, ivPublicacionComment, ivPublicacionShare;
        TextView tvPublicacionUsuario, tvPublicacionFecha, tvPublicacionTitulo, tvPublicacionContenido;
        TextView tvPublicacionLikes, tvPublicacionComments;
        LinearLayout llPublicacionLike, llPublicacionComment, llPublicacionShare;

        public PublicacionViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPublicacionAvatar = itemView.findViewById(R.id.ivPublicacionAvatar);
            ivPublicacionMenu = itemView.findViewById(R.id.ivPublicacionMenu);
            ivPublicacionImagen = itemView.findViewById(R.id.ivPublicacionImagen);
            ivPublicacionLike = itemView.findViewById(R.id.ivPublicacionLike);
            ivPublicacionComment = itemView.findViewById(R.id.ivPublicacionComment);
            ivPublicacionShare = itemView.findViewById(R.id.ivPublicacionShare);
            tvPublicacionUsuario = itemView.findViewById(R.id.tvPublicacionUsuario);
            tvPublicacionFecha = itemView.findViewById(R.id.tvPublicacionFecha);
            tvPublicacionTitulo = itemView.findViewById(R.id.tvPublicacionTitulo);
            tvPublicacionContenido = itemView.findViewById(R.id.tvPublicacionContenido);
            tvPublicacionLikes = itemView.findViewById(R.id.tvPublicacionLikes);
            tvPublicacionComments = itemView.findViewById(R.id.tvPublicacionComments);
            llPublicacionLike = itemView.findViewById(R.id.llPublicacionLike);
            llPublicacionComment = itemView.findViewById(R.id.llPublicacionComment);
            llPublicacionShare = itemView.findViewById(R.id.llPublicacionShare);
        }
    }
}