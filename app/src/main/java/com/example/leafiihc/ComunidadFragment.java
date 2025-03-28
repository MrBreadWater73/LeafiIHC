package com.example.leafiihc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ComunidadFragment extends Fragment {

    private RecyclerView rvPublicaciones;
    private PublicacionAdapter publicacionAdapter;
    private List<Publicacion> publicacionesList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comunidad, container, false);
        
        // Inicializar RecyclerView
        rvPublicaciones = view.findViewById(R.id.rvPublicaciones);
        rvPublicaciones.setLayoutManager(new LinearLayoutManager(getContext()));
        
        // Cargar datos
        publicacionesList = new ArrayList<>();
        cargarPublicaciones();
        
        // Configurar adaptador
        publicacionAdapter = new PublicacionAdapter(getContext(), publicacionesList, new PublicacionAdapter.OnPublicacionClickListener() {
            @Override
            public void onLikeClick(Publicacion publicacion, int position) {
                // Incrementar likes
                publicacion.setLikes(publicacion.getLikes() + 1);
                publicacionAdapter.notifyItemChanged(position);
                Toast.makeText(getContext(), "Te gusta esta publicación", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCommentClick(Publicacion publicacion, int position) {
                // Abrir sección de comentarios
                Toast.makeText(getContext(), "Comentarios de: " + publicacion.getTitulo(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onShareClick(Publicacion publicacion, int position) {
                // Compartir publicación
                Toast.makeText(getContext(), "Compartir: " + publicacion.getTitulo(), Toast.LENGTH_SHORT).show();
            }
        });
        
        rvPublicaciones.setAdapter(publicacionAdapter);
        
        return view;
    }

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