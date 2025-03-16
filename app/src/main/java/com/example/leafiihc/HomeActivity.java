package com.example.leafiihc;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    private TextView tvBienvenida;
    private Button btnCerrarSesion;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Inicializar vistas
        tvBienvenida = findViewById(R.id.tvBienvenida);
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);

        // Obtener usuario actual
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            // Mostrar mensaje de bienvenida con el nombre del usuario
            String nombre = currentUser.getDisplayName();
            if (nombre != null && !nombre.isEmpty()) {
                tvBienvenida.setText("¡Bienvenido/a, " + nombre + "!");
            } else {
                tvBienvenida.setText("¡Bienvenido/a!");
            }
        }

        // Configurar listener para el botón de cerrar sesión
        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrarSesion();
            }
        });
    }

    private void cerrarSesion() {
        // Cerrar sesión en Firebase
        mAuth.signOut();

        // Volver a la pantalla de inicio de sesión
        Intent intent = new Intent(HomeActivity.this, Principal.class);
        startActivity(intent);
        finish();
    }
}