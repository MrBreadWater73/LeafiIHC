package com.example.leafiihc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetallePlantaActivity extends AppCompatActivity {

    private TextView tvPlantaNombre, tvPlantaNombreCientifico, tvPlantaDescripcion;
    private TextView tvRiego, tvLuz, tvTemperatura;
    private ImageView ivPlantaImagen, ivBack, ivHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_planta);

        // Inicializar vistas
        tvPlantaNombre = findViewById(R.id.tvPlantaNombre);
        tvPlantaNombreCientifico = findViewById(R.id.tvPlantaNombreCientifico);
        tvPlantaDescripcion = findViewById(R.id.tvPlantaDescripcion);
        tvRiego = findViewById(R.id.tvRiego);
        tvLuz = findViewById(R.id.tvLuz);
        tvTemperatura = findViewById(R.id.tvTemperatura);
        ivPlantaImagen = findViewById(R.id.ivPlantaImagen);
        ivBack = findViewById(R.id.ivBack);
        ivHome = findViewById(R.id.ivHome);

        // Obtener datos de la planta del intent
        if (getIntent().hasExtra("NOMBRE_PLANTA")) {
            String nombre = getIntent().getStringExtra("NOMBRE_PLANTA");
            String nombreCientifico = getIntent().getStringExtra("NOMBRE_CIENTIFICO");
            String descripcion = getIntent().getStringExtra("DESCRIPCION");
            int imagenId = getIntent().getIntExtra("IMAGEN_ID", R.drawable.ic_encyclopedia);

            // Configurar datos en las vistas
            tvPlantaNombre.setText(nombre);
            tvPlantaNombreCientifico.setText(nombreCientifico);
            tvPlantaDescripcion.setText(descripcion);
            ivPlantaImagen.setImageResource(imagenId);

            // Configurar datos de cuidados (estos podrían venir del intent también)
            tvRiego.setText("Riego: " + getCuidadoRiego(nombre));
            tvLuz.setText("Luz: " + getCuidadoLuz(nombre));
            tvTemperatura.setText("Temperatura: " + getCuidadoTemperatura(nombre));
        }

        // Configurar listener para el botón de retroceso
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Configurar listener para el botón de inicio
        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetallePlantaActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    // Métodos para obtener información de cuidados según la planta
    // Estos métodos podrían ser reemplazados por datos reales de una base de datos
    private String getCuidadoRiego(String nombrePlanta) {
        // Ejemplo simple basado en el nombre de la planta
        if (nombrePlanta.toLowerCase().contains("cactus")) {
            return "Escaso, cada 2-3 semanas";
        } else if (nombrePlanta.toLowerCase().contains("orquídea")) {
            return "Moderado, una vez por semana";
        } else if (nombrePlanta.toLowerCase().contains("suculenta")) {
            return "Escaso, cuando el sustrato esté seco";
        } else if (nombrePlanta.toLowerCase().contains("aloe")) {
            return "Escaso, cada 2-3 semanas";
        } else {
            return "Moderado, cada 2-3 días";
        }
    }

    private String getCuidadoLuz(String nombrePlanta) {
        // Ejemplo simple basado en el nombre de la planta
        if (nombrePlanta.toLowerCase().contains("cactus") ||
                nombrePlanta.toLowerCase().contains("suculenta")) {
            return "Directa o indirecta brillante";
        } else if (nombrePlanta.toLowerCase().contains("orquídea")) {
            return "Indirecta brillante, sin sol directo";
        } else if (nombrePlanta.toLowerCase().contains("aloe")) {
            return "Directa o indirecta brillante";
        } else {
            return "Indirecta brillante";
        }
    }

    private String getCuidadoTemperatura(String nombrePlanta) {
        // Ejemplo simple basado en el nombre de la planta
        if (nombrePlanta.toLowerCase().contains("cactus") ||
                nombrePlanta.toLowerCase().contains("suculenta")) {
            return "18-32°C";
        } else if (nombrePlanta.toLowerCase().contains("orquídea")) {
            return "18-24°C";
        } else if (nombrePlanta.toLowerCase().contains("aloe")) {
            return "15-28°C";
        } else {
            return "18-24°C";
        }
    }
}