package com.example.leafiihc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class AreasVerdesActivity extends AppCompatActivity {

    private EditText etCodigo;
    private ImageView ivClear, ivHome;
    private Button btnIngresar, btnVerAreas, btnEscanearQR;
    private CircleImageView ivUserAvatar;
    private RecyclerView rvAreasVerdes;
    private TextView tvSinAreas;
    private AreaVerdeAdapter areaVerdeAdapter;

    private List<AreaVerde> areasVerdesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areas_verdes);

        // Inicializar vistas
        initializeViews();

        // Cargar áreas verdes guardadas
        cargarAreasVerdes();

        // Configurar RecyclerView
        setupRecyclerView();

        // Configurar listeners
        setupListeners();
    }

    private void initializeViews() {
        etCodigo = findViewById(R.id.etCodigo);
        ivClear = findViewById(R.id.ivClear);
        btnIngresar = findViewById(R.id.btnIngresar);
        btnVerAreas = findViewById(R.id.btnVerAreas);
        btnEscanearQR = findViewById(R.id.btnEscanearQR);
        ivHome = findViewById(R.id.ivHome);
        ivUserAvatar = findViewById(R.id.ivUserAvatar);
        rvAreasVerdes = findViewById(R.id.rvAreasVerdes);
        tvSinAreas = findViewById(R.id.tvSinAreas);
    }

    private void setupRecyclerView() {
        if (rvAreasVerdes != null) {
            rvAreasVerdes.setLayoutManager(new LinearLayoutManager(this));
            areaVerdeAdapter = new AreaVerdeAdapter(this, areasVerdesList, new AreaVerdeAdapter.OnAreaVerdeListener() {
                @Override
                public void onAreaVerdeClick(AreaVerde areaVerde) {
                    abrirDetalleAreaVerde(areaVerde);
                }

                @Override
                public void onAreaVerdeDelete(AreaVerde areaVerde, int position) {
                    eliminarAreaVerde(areaVerde, position);
                }
            });
            rvAreasVerdes.setAdapter(areaVerdeAdapter);
        } else {
            Toast.makeText(this, "Error al inicializar la lista de áreas", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupListeners() {
        ivClear.setOnClickListener(v -> etCodigo.setText(""));

        btnIngresar.setOnClickListener(v -> registrarAreaVerde());

        btnVerAreas.setOnClickListener(v -> {
            if (areasVerdesList.isEmpty()) {
                tvSinAreas.setVisibility(View.VISIBLE);
                rvAreasVerdes.setVisibility(View.GONE);
            } else {
                tvSinAreas.setVisibility(View.GONE);
                rvAreasVerdes.setVisibility(View.VISIBLE);
            }
        });

        btnEscanearQR.setOnClickListener(v -> {
            // TODO: Implementar escaneo de QR
            Toast.makeText(this, "Funcionalidad de escaneo QR en desarrollo", Toast.LENGTH_SHORT).show();
        });

        ivHome.setOnClickListener(v -> {
            Intent intent = new Intent(AreasVerdesActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });



        ivUserAvatar.setOnClickListener(v -> {
            Intent intent = new Intent(AreasVerdesActivity.this, PerfilUsuarioActivity.class);
            startActivity(intent);
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

    private void registrarAreaVerde() {
        String codigo = etCodigo.getText().toString().trim();

        if (codigo.isEmpty()) {
            Toast.makeText(this, "Por favor ingresa un código", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verificar si el código ya existe
        for (AreaVerde area : areasVerdesList) {
            if (area.getCodigo().equals(codigo)) {
                Toast.makeText(this, "Este código ya está registrado", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        // Crear nueva área verde
        AreaVerde nuevaArea = new AreaVerde("Área verde " + (areasVerdesList.size() + 1), codigo);
        areasVerdesList.add(nuevaArea);

        // Guardar en SharedPreferences
        guardarAreasVerdes();

        // Actualizar RecyclerView
        if (areaVerdeAdapter != null) {
            areaVerdeAdapter.notifyDataSetChanged();
        }

        Toast.makeText(this, "Área verde registrada correctamente", Toast.LENGTH_SHORT).show();
        etCodigo.setText("");
    }

    private void eliminarAreaVerde(AreaVerde areaVerde, int position) {
        areasVerdesList.remove(position);
        if (areaVerdeAdapter != null) {
            areaVerdeAdapter.notifyItemRemoved(position);
        }
        guardarAreasVerdes();

        // Actualizar visibilidad
        if (areasVerdesList.isEmpty()) {
            tvSinAreas.setVisibility(View.VISIBLE);
            rvAreasVerdes.setVisibility(View.GONE);
        }

        Toast.makeText(this, "Área verde eliminada", Toast.LENGTH_SHORT).show();
    }

    private void abrirDetalleAreaVerde(AreaVerde areaVerde) {
        Intent intent = new Intent(this, DetalleAreaVerdeActivity.class);
        intent.putExtra("CODIGO_AREA", areaVerde.getCodigo());
        startActivity(intent);
    }
}