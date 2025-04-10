package com.example.leafiihc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetallePlantaActivity extends AppCompatActivity {

    private ImageView ivPlanta;
    private TextView tvNombrePlanta;
    private TextView tvNombreCientifico;
    private TextView tvDescripcion;
    private TextView tvFamilia;
    private TextView tvGenero;
    private TextView tvRiego;
    private TextView tvLuz;
    private TextView tvTemperatura;
    private TextView tvCuidados;
    private TextView tvToxicidad;
    private TextView tvCrecimiento;
    private TextView tvPropagacion;
    private ImageView ivBack;
    private ImageView ivHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_planta);

        // Inicializar vistas
        ivPlanta = findViewById(R.id.ivPlanta);
        tvNombrePlanta = findViewById(R.id.tvNombrePlanta);
        tvNombreCientifico = findViewById(R.id.tvNombreCientifico);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        tvFamilia = findViewById(R.id.tvFamilia);
        tvGenero = findViewById(R.id.tvGenero);
        tvRiego = findViewById(R.id.tvRiego);
        tvLuz = findViewById(R.id.tvLuz);
        tvTemperatura = findViewById(R.id.tvTemperatura);
        tvCuidados = findViewById(R.id.tvCuidados);
        tvToxicidad = findViewById(R.id.tvToxicidad);
        tvCrecimiento = findViewById(R.id.tvCrecimiento);
        tvPropagacion = findViewById(R.id.tvPropagacion);
        ivBack = findViewById(R.id.ivBack);
        ivHome = findViewById(R.id.ivHome);

        // Obtener datos del intent
        Intent intent = getIntent();
        String nombrePlanta = intent.getStringExtra("NOMBRE_PLANTA");
        String nombreCientifico = intent.getStringExtra("NOMBRE_CIENTIFICO");
        String descripcion = intent.getStringExtra("DESCRIPCION");
        int imagenResourceId = intent.getIntExtra("IMAGEN_ID", R.drawable.ic_plants);
        String familia = intent.getStringExtra("FAMILIA");
        String genero = intent.getStringExtra("GENERO");
        String riego = intent.getStringExtra("RIEGO");
        String luz = intent.getStringExtra("LUZ");
        String temperatura = intent.getStringExtra("TEMPERATURA");
        String cuidados = intent.getStringExtra("CUIDADOS");
        String toxicidad = intent.getStringExtra("TOXICIDAD");
        String crecimiento = intent.getStringExtra("CRECIMIENTO");
        String propagacion = intent.getStringExtra("PROPAGACION");

        // Configurar vistas con los datos
        tvNombrePlanta.setText(nombrePlanta);
        tvNombreCientifico.setText(nombreCientifico != null ? nombreCientifico : "N/A");
        tvDescripcion.setText(descripcion != null ? descripcion : "Descripción no disponible.");
        tvFamilia.setText(familia != null ? familia : "N/A");
        tvGenero.setText(genero != null ? genero : "N/A");
        tvRiego.setText(riego != null ? riego : "Información no disponible");
        tvLuz.setText(luz != null ? luz : "Información no disponible");
        tvTemperatura.setText(temperatura != null ? temperatura : "Información no disponible");
        tvCuidados.setText(cuidados != null ? cuidados : "Información no disponible");
        tvToxicidad.setText(toxicidad != null ? toxicidad : "Información no disponible");
        tvCrecimiento.setText(crecimiento != null ? crecimiento : "Información no disponible");
        tvPropagacion.setText(propagacion != null ? propagacion : "Información no disponible");

        // Cargar imagen local
        ivPlanta.setImageResource(imagenResourceId);

        // Configurar botones de navegación
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Va al Home, limpiando actividades intermedias si es necesario
                Intent homeIntent = new Intent(DetallePlantaActivity.this, HomeActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(homeIntent);
                finish(); // Cierra esta actividad
            }
        });
    }
}