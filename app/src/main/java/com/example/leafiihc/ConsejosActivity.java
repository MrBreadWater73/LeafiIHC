package com.example.leafiihc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ConsejosActivity extends AppCompatActivity {

    private ImageView ivBack, ivHome;
    private CircleImageView ivUserAvatar;
    private RecyclerView rvConsejos;
    private TextView tvSinConsejos;
    private List<Consejo> consejosList;
    private ConsejoAdapter consejoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consejos);

        // Inicializar vistas
        ivBack = findViewById(R.id.ivBack);
        ivHome = findViewById(R.id.ivHome);
        ivUserAvatar = findViewById(R.id.ivUserAvatar);
        rvConsejos = findViewById(R.id.rvConsejos);
        tvSinConsejos = findViewById(R.id.tvSinConsejos);

        // Configurar RecyclerView
        rvConsejos.setLayoutManager(new LinearLayoutManager(this));

        // Cargar consejos
        cargarConsejos();
        actualizarListaConsejos();

        // Configurar listeners
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsejosActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        ivUserAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsejosActivity.this, PerfilUsuarioActivity.class);
                startActivity(intent);
            }
        });
    }

    private void cargarConsejos() {
        consejosList = new ArrayList<>();
        
        // Consejos de riego
        consejosList.add(new Consejo(
            "Riego eficiente",
            "Riega tus plantas temprano en la mañana o al atardecer para evitar la evaporación rápida del agua.",
            "Riego",
            R.drawable.ic_water
        ));
        
        consejosList.add(new Consejo(
            "Control de humedad",
            "Usa un medidor de humedad para asegurarte de que el suelo tenga la humedad adecuada para cada tipo de planta.",
            "Riego",
            R.drawable.ic_humidity
        ));

        // Consejos de luz
        consejosList.add(new Consejo(
            "Exposición solar",
            "Coloca las plantas según sus necesidades de luz: pleno sol, sombra parcial o sombra total.",
            "Luz",
            R.drawable.ic_sun
        ));

        // Consejos de fertilización
        consejosList.add(new Consejo(
            "Fertilización balanceada",
            "Usa fertilizantes orgánicos y sigue un calendario de fertilización según el tipo de planta.",
            "Fertilización",
            R.drawable.ic_fertilizer
        ));

        // Consejos de poda
        consejosList.add(new Consejo(
            "Poda correcta",
            "Realiza la poda en la época adecuada y usa herramientas limpias y afiladas para evitar daños.",
            "Poda",
            R.drawable.ic_pruning
        ));

        // Consejos de plagas
        consejosList.add(new Consejo(
            "Control de plagas",
            "Inspecciona regularmente tus plantas para detectar y tratar plagas de manera temprana.",
            "Plagas",
            R.drawable.ic_pest
        ));
    }

    private void actualizarListaConsejos() {
        if (consejosList != null && !consejosList.isEmpty()) {
            tvSinConsejos.setVisibility(View.GONE);
            rvConsejos.setVisibility(View.VISIBLE);

            consejoAdapter = new ConsejoAdapter(this, consejosList);
            rvConsejos.setAdapter(consejoAdapter);
        } else {
            tvSinConsejos.setVisibility(View.VISIBLE);
            rvConsejos.setVisibility(View.GONE);
        }
    }
}