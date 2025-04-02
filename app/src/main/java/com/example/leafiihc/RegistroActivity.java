package com.example.leafiihc;

import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegistroActivity extends AppCompatActivity {

    private EditText etNombre, etEmail, etUsuarioRegistro, etPasswordRegistro, etConfirmPassword;
    private Button btnRegistrar;
    private TextView tvIniciarSesion;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

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
                finish();
            }
        });
    }

    private void registrarUsuario() {
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

        // Crear usuario con Firebase Auth
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Actualizar el perfil del usuario con el nombre
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(nombre)
                                    .build();

                            mAuth.getCurrentUser().updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(RegistroActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                                                
                                                // Volver a la pantalla de inicio de sesión con el usuario registrado
                                                Intent intent = new Intent();
                                                intent.putExtra("USUARIO_REGISTRADO", email);
                                                setResult(RESULT_OK, intent);
                                                finish();
                                            } else {
                                                Toast.makeText(RegistroActivity.this, "Error al actualizar el perfil", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        } else {
                            String errorMessage = "Error en el registro";
                            if (task.getException() != null) {
                                String exceptionMessage = task.getException().getMessage();
                                if (exceptionMessage != null) {
                                    if (exceptionMessage.contains("email address is already in use")) {
                                        errorMessage = "El correo electrónico ya está registrado";
                                    } else if (exceptionMessage.contains("password is invalid")) {
                                        errorMessage = "La contraseña es inválida";
                                    }
                                }
                            }
                            Toast.makeText(RegistroActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}