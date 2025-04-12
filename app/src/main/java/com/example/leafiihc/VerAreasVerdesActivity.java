package com.example.leafiihc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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
    private AreaVerdeAdapter areaVerdeAdapter;
    private List<AreaVerde> areasVerdesList;
    private TextView tvSinAreas;
    private ImageView ivBack, ivHome;
    private CircleImageView ivUserAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_areas_verdes);

        // Inicializar vistas
        initializeViews();

        // Cargar áreas verdes
        cargarAreasVerdes();

        // Configurar RecyclerView
        setupRecyclerView();
    }

    private void initializeViews() {
        rvAreasVerdes = findViewById(R.id.rvAreasVerdes);
        tvSinAreas = findViewById(R.id.tvListaVacia);
        ivBack = findViewById(R.id.ivBack);
        ivHome = findViewById(R.id.ivHome);
        ivUserAvatar = findViewById(R.id.ivUserAvatar);

        // Configurar listeners
        ivBack.setOnClickListener(v -> finish());

        ivHome.setOnClickListener(v -> {
            Intent intent = new Intent(VerAreasVerdesActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });

        ivUserAvatar.setOnClickListener(v -> {
            Intent intent = new Intent(VerAreasVerdesActivity.this, PerfilUsuarioActivity.class);
            startActivity(intent);
        });
    }

    private void setupRecyclerView() {
        rvAreasVerdes.setLayoutManager(new LinearLayoutManager(this));
        areaVerdeAdapter = new AreaVerdeAdapter(this, areasVerdesList, this);
        rvAreasVerdes.setAdapter(areaVerdeAdapter);

        // Mostrar mensaje si no hay áreas
        if (areasVerdesList.isEmpty()) {
            tvSinAreas.setVisibility(View.VISIBLE);
            rvAreasVerdes.setVisibility(View.GONE);
        } else {
            tvSinAreas.setVisibility(View.GONE);
            rvAreasVerdes.setVisibility(View.VISIBLE);
        }
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

    @Override
    public void onAreaVerdeClick(AreaVerde areaVerde) {
        Intent intent = new Intent(this, DetalleAreaVerdeActivity.class);
        intent.putExtra("CODIGO_AREA", areaVerde.getCodigo());
        startActivity(intent);
    }

    @Override
    public void onAreaVerdeDelete(AreaVerde areaVerde, int position) {
        areasVerdesList.remove(position);
        areaVerdeAdapter.notifyItemRemoved(position);
        guardarAreasVerdes();

        // Actualizar visibilidad
        if (areasVerdesList.isEmpty()) {
            tvSinAreas.setVisibility(View.VISIBLE);
            rvAreasVerdes.setVisibility(View.GONE);
        }

        Toast.makeText(this, "Área verde eliminada", Toast.LENGTH_SHORT).show();
    }
}