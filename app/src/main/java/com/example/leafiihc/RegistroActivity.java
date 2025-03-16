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
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {

    private EditText etNombre, etEmail, etUsuario, etPassword, etConfirmPassword;
    private Button btnRegistrar;
    private TextView tvYaTienesCuenta;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Inicializar vistas
        etNombre = findViewById(R.id.etNombre);
        etEmail = findViewById(R.id.etEmail);
        etUsuario = findViewById(R.id.etUsuarioRegistro);
        etPassword = findViewById(R.id.etPasswordRegistro);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        tvYaTienesCuenta = findViewById(R.id.tvYaTienesCuenta);

        // Configurar listener para el botón de registro
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });

        // Configurar listener para el texto de ya tienes cuenta
        tvYaTienesCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volver a la pantalla de inicio de sesión
                finish();
            }
        });
    }

    private void registrarUsuario() {
        // Obtener valores de los campos
        final String nombre = etNombre.getText().toString().trim();
        final String email = etEmail.getText().toString().trim();
        final String usuario = etUsuario.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
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

        if (TextUtils.isEmpty(usuario)) {
            etUsuario.setError("Ingresa un nombre de usuario");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Ingresa una contraseña");
            return;
        }

        if (password.length() < 6) {
            etPassword.setError("La contraseña debe tener al menos 6 caracteres");
            return;
        }

        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Las contraseñas no coinciden");
            return;
        }

        // Registrar usuario en Firebase
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Registro exitoso
                            FirebaseUser user = mAuth.getCurrentUser();

                            // Actualizar perfil del usuario
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(nombre)
                                    .build();

                            user.updateProfile(profileUpdates);

                            // Guardar información adicional en la base de datos
                            Map<String, Object> userData = new HashMap<>();
                            userData.put("nombre", nombre);
                            userData.put("email", email);
                            userData.put("usuario", usuario);

                            mDatabase.child("usuarios").child(user.getUid()).setValue(userData);

                            Toast.makeText(RegistroActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();

                            // Ir a la pantalla principal o de inicio
                            Intent intent = new Intent(RegistroActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // Si falla el registro
                            Toast.makeText(RegistroActivity.this, "Error en el registro: " + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}