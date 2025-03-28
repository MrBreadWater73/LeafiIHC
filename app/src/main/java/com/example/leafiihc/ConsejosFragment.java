package com.example.leafiihc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ConsejosFragment extends Fragment {

    private RecyclerView rvConsejos;
    private ConsejoAdapter consejoAdapter;
    private List<Consejo> consejosList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_consejos_list, container, false);
        
        // Inicializar RecyclerView
        rvConsejos = view.findViewById(R.id.rvConsejos);
        rvConsejos.setLayoutManager(new LinearLayoutManager(getContext()));
        
        // Cargar datos
        consejosList = new ArrayList<>();
        cargarConsejos();
        
        // Configurar adaptador
        consejoAdapter = new ConsejoAdapter(getContext(), consejosList);
        rvConsejos.setAdapter(consejoAdapter);
        
        return view;
    }

    private void cargarConsejos() {
        // Consejos de control de plagas
        consejosList.add(new Consejo(
            "Control de plagas",
            "Para controlar plagas de forma natural, puedes usar una mezcla de agua con jabón neutro en spray. También puedes plantar hierbas repelentes como albahaca, menta o romero cerca de tus plantas susceptibles.\n\nPara áfidos, una solución de agua con ajo machacado es muy efectiva. Para cochinillas, usa alcohol isopropílico diluido en agua.",
            "Plagas"
        ));
        
        // Consejos de control de enfermedades
        consejosList.add(new Consejo(
            "Control de enfermedades",
            "Para prevenir enfermedades fúngicas, evita mojar las hojas al regar y asegura una buena circulación de aire entre plantas. Si aparece oídio (polvo blanco), una solución de bicarbonato de sodio con agua puede ayudar.\n\nPara la pudrición de raíces, reduce el riego y asegúrate de que las macetas tengan buen drenaje. El canela en polvo tiene propiedades antifúngicas naturales.",
            "Enfermedades"
        ));
        
        // Consejos generales
        consejosList.add(new Consejo(
            "Tips generales",
            "Rota tus plantas cada semana para que crezcan uniformemente. Usa cáscaras de plátano enterradas para aportar potasio. Las cáscaras de huevo trituradas son excelentes para aportar calcio.\n\nPara plantas de interior, limpia las hojas regularmente con un paño húmedo para eliminar el polvo y permitir que respiren mejor.",
            "General"
        ));
        
        // Consejos de riego
        consejosList.add(new Consejo(
            "Técnicas de riego",
            "Es mejor regar abundantemente con menos frecuencia que poco y a menudo. Esto fomenta que las raíces crezcan en profundidad. Riega temprano en la mañana para reducir la evaporación.\n\nPara comprobar si una planta necesita agua, introduce un dedo en la tierra hasta la segunda falange. Si sale seco, es momento de regar.",
            "Riego"
        ));
        
        // Consejos de abono
        consejosList.add(new Consejo(
            "Abonos naturales",
            "El agua de cocción de verduras, una vez enfriada, es un excelente fertilizante líquido. Los posos de café son ricos en nitrógeno y perfectos para plantas que aman los suelos ácidos como azaleas y hortensias.\n\nPuedes hacer compost casero con restos de frutas y verduras, cáscaras de huevo y papel sin tinta.",
            "Abono"
        ));
    }
}