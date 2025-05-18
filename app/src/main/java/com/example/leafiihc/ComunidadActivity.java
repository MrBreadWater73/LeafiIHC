package com.example.leafiihc;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Actividad que muestra las publicaciones de la comunidad Leafi.
 * Permite a los usuarios ver, dar me gusta, comentar y compartir publicaciones.
 */
public class ComunidadActivity extends AppCompatActivity {

    private RecyclerView rvPublicaciones;
    private PublicacionAdapter publicacionAdapter;
    private List<Publicacion> publicacionesList;
    private FloatingActionButton fabNuevaPublicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comunidad);
        
        // Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Comunidad Leafi");
        
        // Inicializar RecyclerView
        rvPublicaciones = findViewById(R.id.rvPublicaciones);
        rvPublicaciones.setLayoutManager(new LinearLayoutManager(this));
        rvPublicaciones.setHasFixedSize(true);
        
        // Configurar FloatingActionButton
        fabNuevaPublicacion = findViewById(R.id.fabNuevaPublicacion);
        fabNuevaPublicacion.setOnClickListener(v -> mostrarDialogoNuevaPublicacion());
        
        // Cargar datos
        publicacionesList = new ArrayList<>();
        cargarPublicaciones();
        
        // Configurar adaptador
        publicacionAdapter = new PublicacionAdapter(this, publicacionesList, new PublicacionAdapter.OnPublicacionClickListener() {
            @Override
            public void onLikeClick(Publicacion publicacion, int position) {
                publicacion.toggleMeGusta();
                publicacionAdapter.notifyItemChanged(position);
            }

            @Override
            public void onCommentClick(Publicacion publicacion, int position) {
                mostrarDialogoComentarios(publicacion);
            }

            @Override
            public void onShareClick(Publicacion publicacion, int position) {
                compartirPublicacion(publicacion);
            }
        });
        
        rvPublicaciones.setAdapter(publicacionAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void mostrarDialogoNuevaPublicacion() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_nueva_publicacion);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        
        EditText etTitulo = dialog.findViewById(R.id.etTitulo);
        EditText etContenido = dialog.findViewById(R.id.etContenido);

        
        // Agregar botón de enviar al layout
        Button btnEnviar = new Button(this);
        btnEnviar.setText("Publicar");
        btnEnviar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        btnEnviar.setTextColor(Color.WHITE);
        
        // Obtener el layout raíz y agregar el botón
        LinearLayout rootLayout = dialog.findViewById(R.id.rootLayout);
        rootLayout.addView(btnEnviar);
        
        btnEnviar.setOnClickListener(v -> {
            String titulo = etTitulo.getText().toString().trim();
            String contenido = etContenido.getText().toString().trim();
            
            if (titulo.isEmpty() || contenido.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }
            
            // Crear nueva publicación
            Publicacion nuevaPublicacion = new Publicacion(
                "Diego Guzmán",
                titulo,
                contenido,
                new Date()
            );
            
            publicacionesList.add(0, nuevaPublicacion);
            publicacionAdapter.notifyItemInserted(0);
            rvPublicaciones.scrollToPosition(0);
            
            dialog.dismiss();
        });
        
        dialog.show();
    }

    private void mostrarDialogoComentarios(Publicacion publicacion) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_comentarios);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        
        RecyclerView rvComentarios = dialog.findViewById(R.id.rvComentarios);
        EditText etNuevoComentario = dialog.findViewById(R.id.etNuevoComentario);
        ImageButton btnEnviarComentario = dialog.findViewById(R.id.btnEnviarComentario);
        
        rvComentarios.setLayoutManager(new LinearLayoutManager(this));
        ComentarioAdapter comentarioAdapter = new ComentarioAdapter(this, publicacion.getListaComentarios());
        rvComentarios.setAdapter(comentarioAdapter);
        
        btnEnviarComentario.setOnClickListener(v -> {
            String contenido = etNuevoComentario.getText().toString().trim();
            
            if (contenido.isEmpty()) {
                Toast.makeText(this, "Por favor escribe un comentario", Toast.LENGTH_SHORT).show();
                return;
            }
            
            // Crear nuevo comentario
            Comentario nuevoComentario = new Comentario(
                "Usuario Actual", // En una app real, esto vendría del usuario logueado
                contenido,
                new Date()
            );
            
            publicacion.agregarComentario(nuevoComentario);
            comentarioAdapter.notifyItemInserted(publicacion.getListaComentarios().size() - 1);
            rvComentarios.scrollToPosition(publicacion.getListaComentarios().size() - 1);
            publicacionAdapter.notifyItemChanged(publicacionesList.indexOf(publicacion));
            
            etNuevoComentario.setText("");
        });
        
        dialog.show();
    }

    private void compartirPublicacion(Publicacion publicacion) {
        String textoCompartir = publicacion.getTitulo() + "\n\n" + publicacion.getContenido() + 
            "\n\nCompartido desde Leafi";
        
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, textoCompartir);
        startActivity(Intent.createChooser(shareIntent, "Compartir publicación"));
    }

    /**
     * Carga publicaciones de ejemplo en la lista.
     * En una implementación real, estos datos vendrían de una base de datos o API.
     */
    private void cargarPublicaciones() {
        // Crear algunas publicaciones de ejemplo
        Calendar calendar = Calendar.getInstance();
        Date ahora = calendar.getTime();
        
        // Publicación 1
        Publicacion pub1 = new Publicacion(
            "María García",
            "Mis tomates están creciendo muy bien",
            "Después de seguir los consejos de abono natural, mis tomates están creciendo mucho mejor que el año pasado. Recomiendo usar los posos de café mezclados con cáscaras de huevo trituradas.",
            ahora
        );
        pub1.setLikes(15);
        pub1.setComentarios(3);
        // Agregar comentarios de ejemplo
        pub1.agregarComentario(new Comentario("Juan Pérez", "¡Excelente consejo! Voy a probarlo en mi huerto.", calendar.getTime()));
        pub1.agregarComentario(new Comentario("Ana López", "Yo también lo hago y funciona muy bien.", calendar.getTime()));
        pub1.agregarComentario(new Comentario("Carlos Ruiz", "¿Cada cuánto tiempo lo aplicas?", calendar.getTime()));
        publicacionesList.add(pub1);
        
        // Publicación 2
        calendar.add(Calendar.HOUR, -2);
        Publicacion pub2 = new Publicacion(
            "Carlos Rodríguez",
            "Problema con mis orquídeas",
            "Mis orquídeas tienen las hojas amarillentas. ¿Alguien sabe qué puede ser y cómo solucionarlo? He probado a cambiar la ubicación pero no mejoran.",
            calendar.getTime()
        );
        pub2.setLikes(8);
        pub2.setComentarios(2);
        // Agregar comentarios de ejemplo
        pub2.agregarComentario(new Comentario("Laura Martínez", "Puede ser por exceso de riego.", calendar.getTime()));
        pub2.agregarComentario(new Comentario("Miguel Sánchez", "También revisa la humedad del ambiente.", calendar.getTime()));
        publicacionesList.add(pub2);
        
        // Publicación 3
        calendar.add(Calendar.HOUR, -5);
        Publicacion pub3 = new Publicacion(
            "Laura Martínez",
            "Mi huerto urbano",
            "Acabo de empezar mi huerto urbano en la terraza. Por ahora tengo lechugas, tomates cherry y albahaca. ¿Qué otras plantas me recomendáis que sean fáciles de cuidar?",
            calendar.getTime()
        );
        pub3.setLikes(23);
        pub3.setComentarios(1);
        // Agregar comentarios de ejemplo
        pub3.agregarComentario(new Comentario("Pedro Gómez", "Te recomiendo rúcula y espinacas, son muy fáciles de cultivar.", calendar.getTime()));
        publicacionesList.add(pub3);
        
        // Publicación 4
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Publicacion pub4 = new Publicacion(
            "Javier López",
            "Truco para eliminar mosquitos",
            "He descubierto que plantar citronela y lavanda cerca de las ventanas ayuda a mantener alejados a los mosquitos. Además, dan un aroma muy agradable a la casa.",
            calendar.getTime()
        );
        pub4.setLikes(42);
        pub4.setComentarios(2);
        // Agregar comentarios de ejemplo
        pub4.agregarComentario(new Comentario("Sofía Torres", "¡Genial! Voy a probarlo en mi jardín.", calendar.getTime()));
        pub4.agregarComentario(new Comentario("Diego Ramírez", "También funciona con albahaca.", calendar.getTime()));
        publicacionesList.add(pub4);
    }
}
