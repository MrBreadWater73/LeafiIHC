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

public class AreaVerdeAdapter extends RecyclerView.Adapter<AreaVerdeAdapter.AreaVerdeViewHolder> {

    private Context context;
    private List<AreaVerde> areasVerdes;
    private OnAreaVerdeListener listener;

    public interface OnAreaVerdeListener {
        void onAreaVerdeClick(AreaVerde areaVerde);
        void onAreaVerdeDelete(AreaVerde areaVerde, int position);
    }

    public AreaVerdeAdapter(Context context, List<AreaVerde> areasVerdes, OnAreaVerdeListener listener) {
        this.context = context;
        this.areasVerdes = areasVerdes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AreaVerdeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_area_verde, parent, false);
        return new AreaVerdeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AreaVerdeViewHolder holder, int position) {
        AreaVerde areaVerde = areasVerdes.get(position);
        holder.tvNombre.setText(areaVerde.getNombre());
        holder.tvCodigo.setText(areaVerde.getCodigo());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onAreaVerdeClick(areaVerde);
            }
        });

        holder.ivDelete.setOnClickListener(v -> {
            if (listener != null) {
                listener.onAreaVerdeDelete(areaVerde, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return areasVerdes.size();
    }

    public static class AreaVerdeViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvCodigo;
        ImageView ivDelete;

        public AreaVerdeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvCodigo = itemView.findViewById(R.id.tvCodigo);
            ivDelete = itemView.findViewById(R.id.ivDelete);
        }
    }
}