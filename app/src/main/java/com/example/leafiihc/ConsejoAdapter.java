package com.example.leafiihc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ConsejoAdapter extends RecyclerView.Adapter<ConsejoAdapter.ConsejoViewHolder> {
    private List<Consejo> consejosList;
    private Context context;

    public ConsejoAdapter(Context context, List<Consejo> consejosList) {
        this.context = context;
        this.consejosList = consejosList;
    }

    @NonNull
    @Override
    public ConsejoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_consejo, parent, false);
        return new ConsejoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsejoViewHolder holder, int position) {
        Consejo consejo = consejosList.get(position);
        
        holder.tvTituloConsejo.setText(consejo.getTitulo());
        holder.tvDescripcionConsejo.setText(consejo.getDescripcion());
        holder.tvCategoriaConsejo.setText(consejo.getCategoria());
        holder.ivIconoConsejo.setImageResource(consejo.getIconoResId());
    }

    @Override
    public int getItemCount() {
        return consejosList.size();
    }

    public static class ConsejoViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIconoConsejo;
        TextView tvTituloConsejo, tvDescripcionConsejo, tvCategoriaConsejo;

        public ConsejoViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIconoConsejo = itemView.findViewById(R.id.ivIconoConsejo);
            tvTituloConsejo = itemView.findViewById(R.id.tvTituloConsejo);
            tvDescripcionConsejo = itemView.findViewById(R.id.tvDescripcionConsejo);
            tvCategoriaConsejo = itemView.findViewById(R.id.tvCategoriaConsejo);
        }
    }
}