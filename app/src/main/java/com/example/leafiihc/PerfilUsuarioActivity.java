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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

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
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            // Obtener datos del usuario de Firebase
            String displayName = currentUser.getDisplayName();
            String email = currentUser.getEmail();
            Uri photoUrl = currentUser.getPhotoUrl();

            // Separar nombre y apellido si es posible
            String nombre = "", apellido = "";
            if (displayName != null) {
                String[] nombres = displayName.split(" ", 2);
                nombre = nombres[0];
                apellido = nombres.length > 1 ? nombres[1] : "";
            }

            // Crear o actualizar objeto usuario
            usuario = new Usuario(nombre, apellido, email, "");
            
            // Mostrar datos en la UI
            tvUserName.setText(usuario.getNombreCompleto());
            tvUserEmail.setText(usuario.getEmail());
            
            etNombre.setText(usuario.getNombre());
            etApellido.setText(usuario.getApellido());
            etEmail.setText(usuario.getEmail());
            
            // Cargar foto de perfil si existe
            if (photoUrl != null) {
                usuario.setFotoPerfil(photoUrl.toString());
                // Usar Glide para cargar la imagen
                Glide.with(this)
                    .load(photoUrl)
                    .apply(RequestOptions.circleCropTransform())
                    .placeholder(R.drawable.ic_user)
                    .error(R.drawable.ic_user)
                    .into(ivUserProfilePic);
            } else {
                // Si no hay foto, mostrar imagen por defecto
                ivUserProfilePic.setImageResource(R.drawable.ic_user);
            }
        } else {
            // Si no hay usuario autenticado, redirigir al login
            Intent intent = new Intent(PerfilUsuarioActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
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
        // Cerrar sesión en Firebase
        mAuth.signOut();
        
        // Volver a la pantalla de login
        Intent intent = new Intent(PerfilUsuarioActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}