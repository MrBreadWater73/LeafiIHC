package com.example.leafiihc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.gson.Gson;

import de.hdodenhof.circleimageview.CircleImageView;

public class PerfilUsuarioActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView ivBack;
    private TextView tvGuardar, tvUserName, tvUserEmail;
    private CircleImageView ivUserProfilePic;
    private ImageView ivEditPhoto;
    private EditText etNombre, etApellido, etEmail, etTelefono;
    private Switch switchNotificaciones, switchConsejosDiarios, switchTemaOscuro;
    private Button btnCerrarSesion;

    private Usuario usuario;
    private Uri imagenSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        // Inicializar vistas
        ivBack = findViewById(R.id.ivBack);
        tvGuardar = findViewById(R.id.tvGuardar);
        tvUserName = findViewById(R.id.tvUserName);
        tvUserEmail = findViewById(R.id.tvUserEmail);
        ivUserProfilePic = findViewById(R.id.ivUserProfilePic);
        ivEditPhoto = findViewById(R.id.ivEditPhoto);
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etEmail = findViewById(R.id.etEmail);
        etTelefono = findViewById(R.id.etTelefono);
        switchNotificaciones = findViewById(R.id.switchNotificaciones);
        switchConsejosDiarios = findViewById(R.id.switchConsejosDiarios);
        switchTemaOscuro = findViewById(R.id.switchTemaOscuro);
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);

        // Cargar datos del usuario
        cargarDatosUsuario();

        // Configurar listeners
        ivBack.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilUsuarioActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });

        tvGuardar.setOnClickListener(v -> guardarDatosUsuario());

        ivEditPhoto.setOnClickListener(v -> abrirSelectorImagen());

        btnCerrarSesion.setOnClickListener(v -> cerrarSesion());
    }

    private void cargarDatosUsuario() {
        // En una aplicación real, estos datos vendrían de una base de datos o API
        // Para este ejemplo, usaremos SharedPreferences para simular persistencia
        
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String usuarioJson = prefs.getString("usuario", "");
        
        if (!usuarioJson.isEmpty()) {
            Gson gson = new Gson();
            usuario = gson.fromJson(usuarioJson, Usuario.class);
        } else {
            // Si no hay datos guardados, crear un usuario de ejemplo
            usuario = new Usuario("Juan", "Pérez", "juan.perez@ejemplo.com", "123456789");
        }
        
        // Mostrar datos en la UI
        tvUserName.setText(usuario.getNombreCompleto());
        tvUserEmail.setText(usuario.getEmail());
        
        etNombre.setText(usuario.getNombre());
        etApellido.setText(usuario.getApellido());
        etEmail.setText(usuario.getEmail());
        etTelefono.setText(usuario.getTelefono());
        
        switchNotificaciones.setChecked(usuario.isRecibirNotificaciones());
        switchConsejosDiarios.setChecked(usuario.isConsejosDiarios());
        switchTemaOscuro.setChecked(usuario.isTemaOscuro());
        
        // Si hay una foto de perfil guardada, cargarla
        if (!usuario.getFotoPerfil().isEmpty()) {
            try {
                Uri fotoUri = Uri.parse(usuario.getFotoPerfil());
                ivUserProfilePic.setImageURI(fotoUri);
            } catch (Exception e) {
                // Si hay un error, usar la imagen por defecto
                ivUserProfilePic.setImageResource(R.drawable.ic_user);
            }
        }
    }

    private void guardarDatosUsuario() {
        // Actualizar objeto usuario con los datos de la UI
        usuario.setNombre(etNombre.getText().toString().trim());
        usuario.setApellido(etApellido.getText().toString().trim());
        usuario.setEmail(etEmail.getText().toString().trim());
        usuario.setTelefono(etTelefono.getText().toString().trim());
        
        usuario.setRecibirNotificaciones(switchNotificaciones.isChecked());
        usuario.setConsejosDiarios(switchConsejosDiarios.isChecked());
        usuario.setTemaOscuro(switchTemaOscuro.isChecked());
        
        // Guardar la imagen seleccionada si existe
        if (imagenSeleccionada != null) {
            usuario.setFotoPerfil(imagenSeleccionada.toString());
        }
        
        // En una aplicación real, estos datos se enviarían a una base de datos o API
        // Para este ejemplo, usaremos SharedPreferences para simular persistencia
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        
        Gson gson = new Gson();
        String usuarioJson = gson.toJson(usuario);
        editor.putString("usuario", usuarioJson);
        editor.apply();
        
        // Actualizar la UI
        tvUserName.setText(usuario.getNombreCompleto());
        tvUserEmail.setText(usuario.getEmail());
        
        Toast.makeText(this, "Perfil actualizado correctamente", Toast.LENGTH_SHORT).show();
    }

    private void abrirSelectorImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imagenSeleccionada = data.getData();
            ivUserProfilePic.setImageURI(imagenSeleccionada);
        }
    }

    private void cerrarSesion() {
        // En una aplicación real, aquí se cerraría la sesión del usuario
        // Para este ejemplo, simplemente volvemos a la pantalla de login
        
        // Limpiar SharedPreferences
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
        
        // Volver a la pantalla de login
        Intent intent = new Intent(PerfilUsuarioActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}