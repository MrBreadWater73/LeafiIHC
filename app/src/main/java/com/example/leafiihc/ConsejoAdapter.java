package com.example.leafiihc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ConsejoAdapter extends RecyclerView.Adapter<ConsejoAdapter.ConsejoViewHolder> {

    private List<Consejo> consejosList;
    private List<Consejo> consejosListFull;
    private Context context;

    public ConsejoAdapter(Context context, List<Consejo> consejosList) {
        this.context = context;
        this.consejosList = consejosList;
        this.consejosListFull = new ArrayList<>(consejosList);
    }

    @NonNull
    @Override
    public ConsejoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_consejo, parent, false);
        return new ConsejoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsejoViewHolder holder, int position) {
        Consejo consejo = consejosList.get(position);
        
        holder.tvConsejoTitulo.setText(consejo.getTitulo());
        holder.tvConsejoDescripcion.setText(consejo.getDescripcion());
        
        // Configurar icono de favorito
        if (consejo.isFavorito()) {
            holder.ivConsejoStar.setImageResource(R.drawable.ic_star);
        } else {
            holder.ivConsejoStar.setImageResource(R.drawable.ic_star_outline);
        }
        
        // Configurar listener para expandir/colapsar
        holder.ivConsejoExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.llConsejoContent.getVisibility() == View.VISIBLE) {
                    holder.llConsejoContent.setVisibility(View.GONE);
                    holder.ivConsejoExpand.setImageResource(R.drawable.ic_expand);
                } else {
                    holder.llConsejoContent.setVisibility(View.VISIBLE);
                    holder.ivConsejoExpand.setImageResource(R.drawable.ic_collapse);
                }
            }
        });
        
        // Configurar listener para marcar como favorito
        holder.ivConsejoStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consejo.setFavorito(!consejo.isFavorito());
                notifyItemChanged(holder.getAdapterPosition());
                
                if (consejo.isFavorito()) {
                    Toast.makeText(context, "Añadido a favoritos", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Eliminado de favoritos", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        // Configurar listener para compartir
        holder.ivConsejoShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Compartir: " + consejo.getTitulo(), Toast.LENGTH_SHORT).show();
                // Aquí implementarías la funcionalidad de compartir
            }
        });
        
        // Configurar listener para guardar
        holder.ivConsejoSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Guardado: " + consejo.getTitulo(), Toast.LENGTH_SHORT).show();
                // Aquí implementarías la funcionalidad de guardar
            }
        });
    }

    @Override
    public int getItemCount() {
        return consejosList.size();
    }

    public void filter(String text) {
        consejosList.clear();
        if (text.isEmpty()) {
            consejosList.addAll(consejosListFull);
        } else {
            text = text.toLowerCase();
            for (Consejo consejo : consejosListFull) {
                if (consejo.getTitulo().toLowerCase().contains(text) || 
                    consejo.getDescripcion().toLowerCase().contains(text) ||
                    consejo.getCategoria().toLowerCase().contains(text)) {
                    consejosList.add(consejo);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class ConsejoViewHolder extends RecyclerView.ViewHolder {
        ImageView ivConsejoStar, ivConsejoExpand, ivConsejoShare, ivConsejoSave;
        TextView tvConsejoTitulo, tvConsejoDescripcion;
        LinearLayout llConsejoContent;

        public ConsejoViewHolder(@NonNull View itemView) {
            super(itemView);
            ivConsejoStar = itemView.findViewById(R.id.ivConsejoStar);
            ivConsejoExpand = itemView.findViewById(R.id.ivConsejoExpand);
            ivConsejoShare = itemView.findViewById(R.id.ivConsejoShare);
            ivConsejoSave = itemView.findViewById(R.id.ivConsejoSave);
            tvConsejoTitulo = itemView.findViewById(R.id.tvConsejoTitulo);
            tvConsejoDescripcion = itemView.findViewById(R.id.tvConsejoDescripcion);
            llConsejoContent = itemView.findViewById(R.id.llConsejoContent);
        }
    }
}