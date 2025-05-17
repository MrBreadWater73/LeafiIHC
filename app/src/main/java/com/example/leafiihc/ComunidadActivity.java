package com.example.leafiihc;

import android.os.Bundle;
import android.view.View;
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
        
        // Configurar FloatingActionButton
        fabNuevaPublicacion = findViewById(R.id.fabNuevaPublicacion);
        fabNuevaPublicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ComunidadActivity.this, "Crear nueva publicación", Toast.LENGTH_SHORT).show();
                // Aquí se implementaría la funcionalidad para crear una nueva publicación
            }
        });
        
        // Cargar datos
        publicacionesList = new ArrayList<>();
        cargarPublicaciones();
        
        // Configurar adaptador
        publicacionAdapter = new PublicacionAdapter(this, publicacionesList, new PublicacionAdapter.OnPublicacionClickListener() {
            @Override
            public void onLikeClick(Publicacion publicacion, int position) {
                // Incrementar likes
                publicacion.setLikes(publicacion.getLikes() + 1);
                publicacionAdapter.notifyItemChanged(position);
                Toast.makeText(ComunidadActivity.this, "Te gusta esta publicación", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCommentClick(Publicacion publicacion, int position) {
                // Abrir sección de comentarios
                Toast.makeText(ComunidadActivity.this, "Comentarios de: " + publicacion.getTitulo(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onShareClick(Publicacion publicacion, int position) {
                // Compartir publicación
                Toast.makeText(ComunidadActivity.this, "Compartir: " + publicacion.getTitulo(), Toast.LENGTH_SHORT).show();
            }
        });
        
        rvPublicaciones.setAdapter(publicacionAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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
        pub2.setComentarios(12);
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
        pub3.setComentarios(7);
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
        pub4.setComentarios(15);
        publicacionesList.add(pub4);
    }
}
