package com.example.leafiihc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout llAreasVerdes, llEnciclopedia, llConsejos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        // Inicializar vistas
        llAreasVerdes = findViewById(R.id.llAreasVerdes);
        llEnciclopedia = findViewById(R.id.llEnciclopedia);
        llConsejos = findViewById(R.id.llConsejos);

        // Añadir este código en el metodo onCreate después de inicializar las vistas
        CircleImageView ivUserAvatar = findViewById(R.id.ivUserAvatar);
        ivUserAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PerfilUsuarioActivity.class);
                startActivity(intent);
            }
        });

        // Configurar listeners para las opciones del menú
        llAreasVerdes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Áreas verdes seleccionado", Toast.LENGTH_SHORT).show();
                // Aquí puedes iniciar la actividad correspondiente
                // Intent intent = new Intent(HomeActivity.this, AreasVerdesActivity.class);
                // startActivity(intent);
            }
        });

        llEnciclopedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, EnciclopediaActivity.class);
                startActivity(intent);
            }
        });

        llConsejos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ConsejosActivity.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onBackPressed() {
        // Mostrar diálogo de confirmación para salir de la aplicación
        // en lugar de volver a la pantalla de login
        super.onBackPressed();
        finishAffinity();
    }
}