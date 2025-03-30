package com.example.leafiihc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

    private List<AreaVerde> areasVerdesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areas_verdes);

        // Inicializar vistas
        etCodigo = findViewById(R.id.etCodigo);
        ivClear = findViewById(R.id.ivClear);
        btnIngresar = findViewById(R.id.btnIngresar);
        btnVerAreas = findViewById(R.id.btnVerAreas);
        btnEscanearQR = findViewById(R.id.btnEscanearQR);
        ivHome = findViewById(R.id.ivHome);
        ivUserAvatar = findViewById(R.id.ivUserAvatar);

        // Cargar áreas verdes guardadas
        cargarAreasVerdes();

        // Configurar listeners
        etCodigo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Mostrar u ocultar botón de ingresar según si hay texto
                if (s.length() > 0) {
                    btnIngresar.setVisibility(View.VISIBLE);
                } else {
                    btnIngresar.setVisibility(View.GONE);
                }
            }
        });

        ivClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etCodigo.setText("");
            }
        });

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarAreaVerde();
            }
        });

        btnVerAreas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AreasVerdesActivity.this, VerAreasVerdesActivity.class);
                startActivity(intent);
            }
        });

        btnEscanearQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // En una implementación real, aquí se abriría el escáner de QR
                Toast.makeText(AreasVerdesActivity.this, "Funcionalidad de escaneo QR en desarrollo", Toast.LENGTH_SHORT).show();
            }
        });

        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AreasVerdesActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        ivUserAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AreasVerdesActivity.this, PerfilUsuarioActivity.class);
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
        
        Toast.makeText(this, "Área verde registrada correctamente", Toast.LENGTH_SHORT).show();
        etCodigo.setText("");
    }
}