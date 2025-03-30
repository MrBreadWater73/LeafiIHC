package com.example.leafiihc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AreaVerdeAdapter extends RecyclerView.Adapter<AreaVerdeAdapter.AreaVerdeViewHolder> {

    private List<AreaVerde> areasList;
    private Context context;
    private OnAreaVerdeListener listener;

    public interface OnAreaVerdeListener {
        void onAreaVerdeClick(AreaVerde areaVerde, int position);
        void onAreaVerdeCheckChanged(AreaVerde areaVerde, int position, boolean isChecked);
        void onAreaVerdeEditClick(AreaVerde areaVerde, int position);
    }

    public AreaVerdeAdapter(Context context, List<AreaVerde> areasList, OnAreaVerdeListener listener) {
        this.context = context;
        this.areasList = areasList;
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
        AreaVerde areaVerde = areasList.get(position);
        
        holder.tvNombreArea.setText(areaVerde.getNombre());
        holder.tvCodigoArea.setText(areaVerde.getCodigo());
        holder.cbSeleccionada.setChecked(areaVerde.isSeleccionada());
        
        // Configurar listeners
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onAreaVerdeClick(areaVerde, holder.getAdapterPosition());
                }
            }
        });
        
        holder.cbSeleccionada.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                areaVerde.setSeleccionada(isChecked);
                if (listener != null) {
                    listener.onAreaVerdeCheckChanged(areaVerde, holder.getAdapterPosition(), isChecked);
                }
            }
        });
        
        holder.ivEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onAreaVerdeEditClick(areaVerde, holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return areasList.size();
    }

    public static class AreaVerdeViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreArea, tvCodigoArea;
        CheckBox cbSeleccionada;
        ImageView ivEditar;

        public AreaVerdeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreArea = itemView.findViewById(R.id.tvNombreArea);
            tvCodigoArea = itemView.findViewById(R.id.tvCodigoArea);
            cbSeleccionada = itemView.findViewById(R.id.cbSeleccionada);
            ivEditar = itemView.findViewById(R.id.ivEditar);
        }
    }
}