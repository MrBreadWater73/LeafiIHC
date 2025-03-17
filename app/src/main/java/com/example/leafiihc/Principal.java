package com.example.leafiihc;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Principal extends AppCompatActivity {

    private EditText etUsuario, etPassword;
    private Button btnIniciarSesion;
    private TextView tvOlvidastePassword, tvRegistrate;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Inicializar vistas
        etUsuario = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        tvOlvidastePassword = findViewById(R.id.tvOlvidastePassword);
        tvRegistrate = findViewById(R.id.tvRegistrate);

        // Configurar listener para el botón de inicio de sesión
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });

        // Configurar listener para el texto de olvidaste contraseña
        tvOlvidastePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí puedes abrir la actividad de recuperación de contraseña
                Toast.makeText(Principal.this, "Recuperar contraseña", Toast.LENGTH_SHORT).show();
                // Intent intent = new Intent(Principal.this, RecuperarContrasenaActivity.class);
                // startActivity(intent);
            }
        });

        // Configurar listener para el texto de registro
        tvRegistrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir la actividad de registro
                Intent intent = new Intent(Principal.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Verificar si el usuario ya está autenticado
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            // Si ya está autenticado, ir a la pantalla principal
            Intent intent = new Intent(Principal.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void iniciarSesion() {
        // Obtener texto de los campos
        String usuario = etUsuario.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Validar campos
        if (TextUtils.isEmpty(usuario)) {
            etUsuario.setError("Ingresa tu usuario");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Ingresa tu contraseña");
            return;
        }

        // Iniciar sesión con Firebase
        // Asumimos que el usuario ingresa su correo electrónico como usuario
        mAuth.signInWithEmailAndPassword(usuario, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Inicio de sesión exitoso
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(Principal.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                            // Ir a la pantalla principal
                            Intent intent = new Intent(Principal.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // Si falla el inicio de sesión
                            Toast.makeText(Principal.this, "Error en el inicio de sesión: " + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}