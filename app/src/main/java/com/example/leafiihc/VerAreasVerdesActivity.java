package com.example.leafiihc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class VerAreasVerdesActivity extends AppCompatActivity implements AreaVerdeAdapter.OnAreaVerdeListener {

    private RecyclerView rvAreasVerdes;
    private TextView tvListaVacia;
    private Button btnVerDetalles, btnEliminar, btnRegresar;
    private ImageView ivHome;
    private CircleImageView ivUserAvatar;

    private List<AreaVerde> areasVerdesList;
    private AreaVerdeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_areas_verdes);

        // Inicializar vistas
        rvAreasVerdes = findViewById(R.id.rvAreasVerdes);
        tvListaVacia = findViewById(R.id.tvListaVacia);
        btnVerDetalles = findViewById(R.id.btnVerDetalles);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnRegresar = findViewById(R.id.btnRegresar);
        ivHome = findViewById(R.id.ivHome);
        ivUserAvatar = findViewById(R.id.ivUserAvatar);

        // Configurar RecyclerView
        rvAreasVerdes.setLayoutManager(new LinearLayoutManager(this));

        // Cargar áreas verdes
        cargarAreasVerdes();

        // Configurar adaptador
        adapter = new AreaVerdeAdapter(this, areasVerdesList, this);
        rvAreasVerdes.setAdapter(adapter);

        // Mostrar mensaje si la lista está vacía
        actualizarVistaListaVacia();

        // Configurar listeners
        btnVerDetalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verDetallesAreaSeleccionada();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarAreasSeleccionadas();
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerAreasVerdesActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        ivUserAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerAreasVerdesActivity.this, PerfilUsuarioActivity.class);
                startActivity(intent);
            }
        });
    }

    private void cargarAreasVerdes() {
        SharedPreferences prefs = getSharedPreferences("AreasVerdesPrefs", MODE_PRIVATE);
        String areasJson = prefs.getString("areasVerdes", "");
        
        if (!areasJson.isEmpty()) {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<AreaVerde>>(){}.getType();
            areasVerdesList = gson.fromJson(areasJson, type);
        } else {
            areasVerdesList = new ArrayList<>();
        }
    }

    private void guardarAreasVerdes() {
        SharedPreferences prefs = getSharedPreferences("AreasVerdesPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        
        Gson gson = new Gson();
        String areasJson = gson.toJson(areasVerdesList);
        editor.putString("areasVerdes", areasJson);
        editor.apply();
    }

    private void actualizarVistaListaVacia() {
        if (areasVerdesList.isEmpty()) {
            tvListaVacia.setVisibility(View.VISIBLE);
            rvAreasVerdes.setVisibility(View.GONE);
            btnVerDetalles.setEnabled(false);
            btnEliminar.setEnabled(false);
        } else {
            tvListaVacia.setVisibility(View.GONE);
            rvAreasVerdes.setVisibility(View.VISIBLE);
            btnVerDetalles.setEnabled(true);
            btnEliminar.setEnabled(true);
        }
    }

    private void verDetallesAreaSeleccionada() {
        AreaVerde areaSeleccionada = null;
        
        for (AreaVerde area : areasVerdesList) {
            if (area.isSeleccionada()) {
                areaSeleccionada = area;
                break;
            }
        }
        
        if (areaSeleccionada != null) {
            Intent intent = new Intent(VerAreasVerdesActivity.this, DetalleAreaVerdeActivity.class);
            intent.putExtra("CODIGO_AREA", areaSeleccionada.getCodigo());
            startActivity(intent);
        } else {
            Toast.makeText(this, "Selecciona un área verde para ver sus detalles", Toast.LENGTH_SHORT).show();
        }
    }

    private void eliminarAreasSeleccionadas() {
        boolean haySeleccionadas = false;
        List<AreaVerde> areasAEliminar = new ArrayList<>();
        
        for (AreaVerde area : areasVerdesList) {
            if (area.isSeleccionada()) {
                areasAEliminar.add(area);
                haySeleccionadas = true;
            }
        }
        
        if (haySeleccionadas) {
            areasVerdesList.removeAll(areasAEliminar);
            guardarAreasVerdes();
            adapter.notifyDataSetChanged();
            actualizarVistaListaVacia();
            Toast.makeText(this, "Áreas verdes eliminadas", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Selecciona al menos un área verde para eliminar", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAreaVerdeClick(AreaVerde areaVerde, int position) {
        Intent intent = new Intent(VerAreasVerdesActivity.this, DetalleAreaVerdeActivity.class);
        intent.putExtra("CODIGO_AREA", areaVerde.getCodigo());
        startActivity(intent);
    }

    @Override
    public void onAreaVerdeCheckChanged(AreaVerde areaVerde, int position, boolean isChecked) {
        // Ya se actualiza el estado en el adaptador
    }

    @Override
    public void onAreaVerdeEditClick(AreaVerde areaVerde, int position) {
        Intent intent = new Intent(VerAreasVerdesActivity.this, DetalleAreaVerdeActivity.class);
        intent.putExtra("CODIGO_AREA", areaVerde.getCodigo());
        intent.putExtra("MODO_EDICION", true);
        startActivity(intent);
    }
}