package com.example.leafiihc;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {

    private EditText etNombre, etEmail, etUsuarioRegistro, etPasswordRegistro, etConfirmPassword;
    private Button btnRegistrar;
    private TextView tvIniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Inicializar vistas
        etNombre = findViewById(R.id.etNombre);
        etEmail = findViewById(R.id.etEmail);
        etUsuarioRegistro = findViewById(R.id.etUsuarioRegistro);
        etPasswordRegistro = findViewById(R.id.etPasswordRegistro);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        tvIniciarSesion = findViewById(R.id.tvIniciarSesion);

        // Configurar listener para el botón de registro
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });

        // Configurar listener para el texto de iniciar sesión
        tvIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volver a la pantalla de inicio de sesión
                finish(); // Cierra esta actividad y vuelve a la anterior
            }
        });
    }

    private void registrarUsuario() {
        // Obtener texto de los campos
        String nombre = etNombre.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String usuario = etUsuarioRegistro.getText().toString().trim();
        String password = etPasswordRegistro.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        // Validar campos
        if (TextUtils.isEmpty(nombre)) {
            etNombre.setError("Ingresa tu nombre");
            return;
        }

        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Ingresa tu correo electrónico");
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Ingresa un correo electrónico válido");
            return;
        }

        if (TextUtils.isEmpty(usuario)) {
            etUsuarioRegistro.setError("Ingresa un nombre de usuario");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etPasswordRegistro.setError("Ingresa una contraseña");
            return;
        }

        if (password.length() < 6) {
            etPasswordRegistro.setError("La contraseña debe tener al menos 6 caracteres");
            return;
        }

        if (TextUtils.isEmpty(confirmPassword)) {
            etConfirmPassword.setError("Confirma tu contraseña");
            return;
        }

        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Las contraseñas no coinciden");
            return;
        }

        // Aquí implementarías la lógica de registro
        // Por ejemplo, guardar en una base de datos local o enviar a un servidor

        // Simulación de registro exitoso
        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();

        // Volver a la pantalla de inicio de sesión con el usuario registrado
        Intent intent = new Intent();
        intent.putExtra("USUARIO_REGISTRADO", usuario);
        setResult(RESULT_OK, intent);
        finish(); // Cierra esta actividad y vuelve a LoginActivity
    }

    @Override
    public void onBackPressed() {
        // Simplemente volvemos a la actividad anterior (LoginActivity)
        super.onBackPressed();
    }
}