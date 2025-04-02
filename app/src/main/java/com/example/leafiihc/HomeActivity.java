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

import com.google.android.material.bottomnavigation.BottomNavigationView;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout llAreasVerdes, llEnciclopedia, llConsejos;
    private BottomNavigationView bottomNavBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        // Inicializar vistas
        llAreasVerdes = findViewById(R.id.llAreasVerdes);
        llEnciclopedia = findViewById(R.id.llEnciclopedia);
        llConsejos = findViewById(R.id.llConsejos);
        bottomNavBar = findViewById(R.id.bottomNavBar);

        // Configurar BottomNavigationView
        bottomNavBar.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                // Ya estamos en Home, no hacer nada
                return true;
            } else if (itemId == R.id.navigation_plants) {
                Intent intent = new Intent(HomeActivity.this, EnciclopediaActivity.class);
                startActivity(intent);
                finish();
                return true;
            } else if (itemId == R.id.navigation_profile) {
                Intent intent = new Intent(HomeActivity.this, PerfilUsuarioActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
            return false;
        });

        // Añadir este código en el metodo onCreate después de inicializar las vistas
        CircleImageView ivUserAvatar = findViewById(R.id.ivUserAvatar);
        ivUserAvatar.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, PerfilUsuarioActivity.class);
            startActivity(intent);
        });

        // Configurar listeners para las opciones del menú
        llAreasVerdes.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, AreasVerdesActivity.class);
            startActivity(intent);
        });

        llEnciclopedia.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, EnciclopediaActivity.class);
            startActivity(intent);
        });

        llConsejos.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ConsejosActivity.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onBackPressed() {
        // Mostrar diálogo de confirmación
        Toast.makeText(this, "¿Desea salir de la aplicación?", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }
}