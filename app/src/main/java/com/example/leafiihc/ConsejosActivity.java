package com.example.leafiihc;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import de.hdodenhof.circleimageview.CircleImageView;

public class ConsejosActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private EditText etSearchConsejos;
    private FloatingActionButton fabAddConsejo;
    private ImageView ivHome;
    private ConsejosViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consejos);

        // Inicializar vistas
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        etSearchConsejos = findViewById(R.id.etSearchConsejos);
        fabAddConsejo = findViewById(R.id.fabAddConsejo);
        ivHome = findViewById(R.id.ivHome);

        // Añadir este código en el metodo onCreate después de inicializar las vistas
        CircleImageView ivUserAvatar = findViewById(R.id.ivUserAvatar);
        ivUserAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsejosActivity.this, PerfilUsuarioActivity.class);
                startActivity(intent);
            }
        });

        // Configurar ViewPager
        viewPagerAdapter = new ConsejosViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        // Conectar TabLayout con ViewPager
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Consejos");
                    break;
                case 1:
                    tab.setText("Calendario");
                    break;
                case 2:
                    tab.setText("Comunidad");
                    break;
            }
        }).attach();

        // Configurar listener para el botón flotante
        fabAddConsejo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí implementarías la funcionalidad para añadir un nuevo consejo o publicación
                int currentTab = tabLayout.getSelectedTabPosition();
                switch (currentTab) {
                    case 0:
                        Toast.makeText(ConsejosActivity.this, "Añadir nuevo consejo", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(ConsejosActivity.this, "Añadir nueva tarea", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(ConsejosActivity.this, "Crear nueva publicación", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        // Configurar listener para el botón de inicio
        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsejosActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        // Configurar búsqueda
        etSearchConsejos.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Aquí implementarías la funcionalidad de búsqueda
                // Dependiendo de la pestaña activa
                Toast.makeText(ConsejosActivity.this, "Buscando: " + s.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}