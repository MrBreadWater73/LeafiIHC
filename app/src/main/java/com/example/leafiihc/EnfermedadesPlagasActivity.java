package com.example.leafiihc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EnfermedadesPlagasActivity extends AppCompatActivity {

    private RecyclerView rvEnfermedades;
    private EnfermedadAdapter enfermedadAdapter;
    private List<Enfermedad> enfermedadesList;
    private TextView tvEmptyState;
    private ImageView ivHome;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enfermedades_plagas);

        // Inicializar vistas
        rvEnfermedades = findViewById(R.id.rvEnfermedades);
        tvEmptyState = findViewById(R.id.tvEmptyState);
        ivHome = findViewById(R.id.ivHome);
        progressBar = findViewById(R.id.progressBar);

        // Configurar RecyclerView
        rvEnfermedades.setLayoutManager(new LinearLayoutManager(this));
        rvEnfermedades.setHasFixedSize(true);

        // Inicializar lista de enfermedades
        enfermedadesList = new ArrayList<>();
        
        // Agregar enfermedades hardcodeadas
        agregarEnfermedadesHardcodeadas();

        // Configurar adaptador
        enfermedadAdapter = new EnfermedadAdapter(this, enfermedadesList);
        rvEnfermedades.setAdapter(enfermedadAdapter);

        // Configurar botón de inicio
        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnfermedadesPlagasActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void agregarEnfermedadesHardcodeadas() {
        // Enfermedad 1: Oídio
        enfermedadesList.add(new Enfermedad(
            "Oídio",
            "Erysiphe spp.",
            "El oídio es una enfermedad fúngica que aparece como un polvo blanco o gris en las hojas, tallos y flores. Es común en condiciones de alta humedad y temperaturas moderadas.",
            "1. Aplicar fungicidas específicos para oídio\n2. Mejorar la circulación del aire\n3. Evitar el riego por aspersión\n4. Eliminar las partes afectadas",
            "Rosas, Cucurbitáceas, Vides, Manzanos",
            R.drawable.oidio
        ));

        // Enfermedad 2: Roya
        enfermedadesList.add(new Enfermedad(
            "Roya",
            "Puccinia spp.",
            "La roya se manifiesta como pequeñas pústulas de color naranja, marrón o negro en el envés de las hojas. Puede causar defoliación severa.",
            "1. Usar fungicidas preventivos\n2. Eliminar hojas infectadas\n3. Mantener el follaje seco\n4. Rotar cultivos",
            "Rosas, Cereales, Leguminosas",
            R.drawable.roya
        ));

        // Enfermedad 3: Mildiu
        enfermedadesList.add(new Enfermedad(
            "Mildiu",
            "Peronospora spp.",
            "El mildiu causa manchas amarillas en el haz de las hojas y un moho blanco en el envés. Es común en condiciones de alta humedad.",
            "1. Aplicar fungicidas sistémicos\n2. Reducir la humedad ambiental\n3. Usar riego por goteo\n4. Eliminar plantas infectadas",
            "Vides, Tomates, Patatas",
            R.drawable.mildiu
        ));

        // Enfermedad 4: Antracnosis
        enfermedadesList.add(new Enfermedad(
            "Antracnosis",
            "Colletotrichum spp.",
            "La antracnosis causa manchas oscuras y hundidas en hojas, tallos y frutos. Puede causar pudrición y caída prematura de frutos.",
            "1. Usar fungicidas protectores\n2. Podar ramas afectadas\n3. Evitar el riego por aspersión\n4. Mantener el área limpia de restos vegetales",
            "Cítricos, Mangos, Frijoles",
            R.drawable.antracnosis
        ));

        // Enfermedad 5: Pulgones
        enfermedadesList.add(new Enfermedad(
            "Pulgones",
            "Aphidoidea",
            "Los pulgones son pequeños insectos que succionan la savia de las plantas, causando deformación de hojas y brotes. También pueden transmitir virus.",
            "1. Usar insecticidas específicos\n2. Introducir depredadores naturales\n3. Aplicar jabón potásico\n4. Eliminar manualmente las colonias",
            "Rosas, Hortalizas, Frutales",
            R.drawable.pulgones
        ));

        mostrarMensajeVacio();
    }
    
    private void mostrarMensajeVacio() {
        if (enfermedadesList.isEmpty()) {
            tvEmptyState.setVisibility(View.VISIBLE);
            rvEnfermedades.setVisibility(View.GONE);
        } else {
            tvEmptyState.setVisibility(View.GONE);
            rvEnfermedades.setVisibility(View.VISIBLE);
        }
    }
} 