package com.example.leafiihc;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsuario, etPassword;
    private Button btnIniciarSesion;
    private TextView tvOlvidastePassword, tvRegistrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializar vistas
        etUsuario = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        tvOlvidastePassword = findViewById(R.id.tvOlvidastePassword);
        tvRegistrate = findViewById(R.id.tvRegistrate);

        // Verificar si hay un usuario registrado pasado como extra
        if (getIntent().hasExtra("USUARIO_REGISTRADO")) {
            String usuarioRegistrado = getIntent().getStringExtra("USUARIO_REGISTRADO");
            etUsuario.setText(usuarioRegistrado);
        }

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
                Toast.makeText(LoginActivity.this, "Recuperar contraseña", Toast.LENGTH_SHORT).show();
                // Intent intent = new Intent(LoginActivity.this, RecuperarContrasenaActivity.class);
                // startActivity(intent);
            }
        });

        // Configurar listener para el texto de registro
        tvRegistrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir la actividad de registro para obtener resultado
                Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivityForResult(intent, 1001); // Código de solicitud 1001
            }
        });




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

        // Aquí implementarías la lógica de autenticación
        // Por ejemplo, verificar credenciales contra una base de datos o API

        // Simulación de autenticación exitosa
        if (usuario.equals("admin") && password.equals("admin")) {
            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish(); // Cierra esta actividad para que no puedan volver atrás
        } else {
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        // Si el usuario presiona el botón de retroceso en la pantalla de login,
        // cerramos la aplicación en lugar de volver al splash
        super.onBackPressed();
        finishAffinity();
    }
}