package com.example.leafiihc;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EnciclopediaActivity extends AppCompatActivity implements PlantaAdapter.OnPlantaClickListener {

    private RecyclerView rvPlantas;
    private PlantaAdapter plantaAdapter;
    private List<Planta> plantasList;
    private EditText etSearch;
    private TextView tvEmptyState;
    private ImageView ivHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enciclopedia);

        // Inicializar vistas
        rvPlantas = findViewById(R.id.rvPlantas);
        etSearch = findViewById(R.id.etSearch);
        tvEmptyState = findViewById(R.id.tvEmptyState);
        ivHome = findViewById(R.id.ivHome);

        // Añadir este código en el metodo onCreate después de inicializar las vistas
        CircleImageView ivUserAvatar = findViewById(R.id.ivUserAvatar);
        ivUserAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnciclopediaActivity.this, PerfilUsuarioActivity.class);
                startActivity(intent);
            }
        });

        // Configurar RecyclerView
        rvPlantas.setLayoutManager(new LinearLayoutManager(this));
        rvPlantas.setHasFixedSize(true);

        // Inicializar lista de plantas
        plantasList = new ArrayList<>();
        cargarPlantas();

        // Configurar adaptador
        plantaAdapter = new PlantaAdapter(this, plantasList, this);
        rvPlantas.setAdapter(plantaAdapter);

        // Mostrar mensaje si no hay plantas
        if (plantasList.isEmpty()) {
            tvEmptyState.setVisibility(View.VISIBLE);
            rvPlantas.setVisibility(View.GONE);
        } else {
            tvEmptyState.setVisibility(View.GONE);
            rvPlantas.setVisibility(View.VISIBLE);
        }

        // Configurar búsqueda
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                plantaAdapter.filter(s.toString());
                if (plantaAdapter.getItemCount() == 0) {
                    tvEmptyState.setVisibility(View.VISIBLE);
                    rvPlantas.setVisibility(View.GONE);
                } else {
                    tvEmptyState.setVisibility(View.GONE);
                    rvPlantas.setVisibility(View.VISIBLE);
                }
            }
        });

        // Configurar botón de inicio
        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnciclopediaActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void cargarPlantas() {
        // Aquí puedes cargar las plantas desde una base de datos o API
        // Por ahora, añadiremos algunas plantas de ejemplo
        plantasList.add(new Planta("Aloe Vera", "Aloe barbadensis miller",
                "Planta suculenta de la familia Xanthorrhoeaceae, utilizada en medicina tradicional.",
                R.drawable.ic_encyclopedia));

        plantasList.add(new Planta("Lavanda", "Lavandula",
                "Género de plantas de la familia Lamiaceae, con propiedades aromáticas y medicinales.",
                R.drawable.ic_encyclopedia));

        plantasList.add(new Planta("Cactus", "Cactaceae",
                "Familia de plantas suculentas adaptadas a climas áridos, con espinas y tallos carnosos.",
                R.drawable.ic_encyclopedia));

        plantasList.add(new Planta("Orquídea", "Orchidaceae",
                "Familia de plantas monocotiledóneas con flores vistosas y complejas.",
                R.drawable.ic_encyclopedia));

        plantasList.add(new Planta("Suculenta", "Crassulaceae",
                "Plantas que almacenan agua en sus hojas, tallos o raíces, adaptadas a ambientes áridos.",
                R.drawable.ic_encyclopedia));
    }

    @Override
    public void onPlantaClick(Planta planta, int position) {
        // Abrir la actividad de detalle para la planta seleccionada
        Intent intent = new Intent(EnciclopediaActivity.this, DetallePlantaActivity.class);
        intent.putExtra("NOMBRE_PLANTA", planta.getNombre());
        intent.putExtra("NOMBRE_CIENTIFICO", planta.getNombreCientifico());
        intent.putExtra("DESCRIPCION", planta.getDescripcion());
        intent.putExtra("IMAGEN_ID", planta.getImagenResourceId());
        startActivity(intent);
    }
}