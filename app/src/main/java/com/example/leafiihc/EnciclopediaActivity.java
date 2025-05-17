package com.example.leafiihc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;


public class EnciclopediaActivity extends AppCompatActivity implements PlantaAdapter.OnPlantaClickListener {

    private RecyclerView rvPlantas;
    private PlantaAdapter plantaAdapter;
    private List<Planta> plantasList;
    private EditText etSearch;
    private TextView tvEmptyState;
    private ImageView ivHome;
    private ProgressBar progressBar;
    private View btnEnfermedades;

    private int currentPage = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enciclopedia);

        // Inicializar vistas
        rvPlantas = findViewById(R.id.rvPlantas);
        etSearch = findViewById(R.id.etSearch);
        tvEmptyState = findViewById(R.id.tvEmptyState);
        ivHome = findViewById(R.id.ivHome);
        progressBar = findViewById(R.id.progressBar);
        btnEnfermedades = findViewById(R.id.btnEnfermedades);

        // Añadir este código en el metodo onCreate después de inicializar las vistas
        CircleImageView ivUserAvatar = findViewById(R.id.ivUserAvatar);
        ivUserAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnciclopediaActivity.this, PerfilUsuarioActivity.class);
                startActivity(intent);
            }
        });

        // Configurar RecyclerView
        rvPlantas.setLayoutManager(new LinearLayoutManager(this));
        rvPlantas.setHasFixedSize(true);

        // Inicializar lista de plantas
        plantasList = new ArrayList<>();

        // Configurar adaptador
        plantaAdapter = new PlantaAdapter(this, plantasList, this);
        rvPlantas.setAdapter(plantaAdapter);

        // Cargar plantas iniciales
        cargarPlantasDesdeAPI();

        // Configurar búsqueda
        etSearch.addTextChangedListener(new TextWatcher() {
            private Runnable searchRunnable;
            private final Handler searchHandler = new Handler(Looper.getMainLooper());

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Cancelar búsqueda anterior si existe
                if (searchRunnable != null) {
                    searchHandler.removeCallbacks(searchRunnable);
                }

                final String query = s.toString().trim();
                
                // Crear nueva búsqueda con delay
                searchRunnable = new Runnable() {
                    @Override
                    public void run() {
                        if (query.isEmpty()) {
                            // Si el campo está vacío, cargar todas las plantas
                            plantasList.clear();
                            currentPage = 1;
                            isLastPage = false;
                            cargarPlantasDesdeAPI();
                        } else {
                            // Realizar búsqueda
                            buscarPlantasEnAPI(query);
                        }
                    }
                };

                // Ejecutar búsqueda después de 500ms de inactividad
                searchHandler.postDelayed(searchRunnable, 500);
            }
        });

        // Configurar botón de inicio
        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnciclopediaActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Configurar botón de enfermedades y plagas
        btnEnfermedades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnciclopediaActivity.this, EnfermedadesPlagasActivity.class);
                startActivity(intent);
            }
        });
    }

    private void cargarPlantasDesdeAPI() {
        if (isLoading || isLastPage) return;
        
        isLoading = true;
        progressBar.setVisibility(View.VISIBLE);
        tvEmptyState.setVisibility(View.GONE);
        
        // --- Inicio: Datos de Ejemplo ---
        plantasList.clear();
        
        // Monstera Deliciosa
        Planta monstera = new Planta();
        monstera.setNombre("Monstera Deliciosa");
        monstera.setNombreCientifico("Monstera deliciosa");
        monstera.setDescripcion("Conocida como Costilla de Adán, es una planta trepadora popular por sus grandes hojas verdes y perforadas.");
        monstera.setImagenResourceId(R.drawable.monstera);
        monstera.setFamilia("Araceae");
        monstera.setGenero("Monstera");
        monstera.setRiego("Riego moderado, dejar secar el sustrato entre riegos");
        monstera.setLuz("Luz indirecta brillante");
        monstera.setTemperatura("18-27°C");
        monstera.setCuidados("Necesita soporte para trepar, limpiar hojas regularmente");
        monstera.setToxicidad("Tóxica para mascotas");
        monstera.setCrecimiento("Rápido, puede alcanzar varios metros");
        monstera.setPropagacion("Por esquejes de tallo con nudos");
        plantasList.add(monstera);
        
        // Pothos
        Planta pothos = new Planta();
        pothos.setNombre("Pothos");
        pothos.setNombreCientifico("Epipremnum aureum");
        pothos.setDescripcion("Planta colgante de fácil cuidado, ideal para principiantes. Sus hojas en forma de corazón son muy decorativas.");
        pothos.setImagenResourceId(R.drawable.pothos);
        pothos.setFamilia("Araceae");
        pothos.setGenero("Epipremnum");
        pothos.setRiego("Riego moderado, tolera sequía");
        pothos.setLuz("Luz indirecta, tolera poca luz");
        pothos.setTemperatura("15-30°C");
        pothos.setCuidados("Poda regular para mantener forma, limpiar hojas");
        pothos.setToxicidad("Tóxica para mascotas");
        pothos.setCrecimiento("Rápido, puede crecer varios metros");
        pothos.setPropagacion("Por esquejes de tallo en agua o tierra");
        plantasList.add(pothos);
        
        // Sansevieria
        Planta sansevieria = new Planta();
        sansevieria.setNombre("Sansevieria");
        sansevieria.setNombreCientifico("Sansevieria trifasciata");
        sansevieria.setDescripcion("También conocida como Lengua de Suegra, es una planta muy resistente que purifica el aire.");
        sansevieria.setImagenResourceId(R.drawable.sansevieria);
        sansevieria.setFamilia("Asparagaceae");
        sansevieria.setGenero("Sansevieria");
        sansevieria.setRiego("Riego escaso, tolera sequía");
        sansevieria.setLuz("Luz indirecta, tolera poca luz");
        sansevieria.setTemperatura("15-30°C");
        sansevieria.setCuidados("Muy resistente, ideal para principiantes");
        sansevieria.setToxicidad("Tóxica para mascotas");
        sansevieria.setCrecimiento("Lento a moderado");
        sansevieria.setPropagacion("Por división de rizomas o esquejes de hoja");
        plantasList.add(sansevieria);
        
        // Ficus Lyrata
        Planta ficus = new Planta();
        ficus.setNombre("Ficus Lyrata");
        ficus.setNombreCientifico("Ficus lyrata");
        ficus.setDescripcion("Conocido como Ficus Hoja de Violín, es una planta de interior elegante con hojas grandes y brillantes.");
        ficus.setImagenResourceId(R.drawable.ficus);
        ficus.setFamilia("Moraceae");
        ficus.setGenero("Ficus");
        ficus.setRiego("Riego moderado, dejar secar entre riegos");
        ficus.setLuz("Luz indirecta brillante");
        ficus.setTemperatura("18-27°C");
        ficus.setCuidados("Necesita humedad ambiental, limpiar hojas regularmente");
        ficus.setToxicidad("Tóxica para mascotas");
        ficus.setCrecimiento("Moderado");
        ficus.setPropagacion("Por esquejes de tallo");
        plantasList.add(ficus);
        
        // Aloe Vera
        Planta aloe = new Planta();
        aloe.setNombre("Aloe Vera");
        aloe.setNombreCientifico("Aloe vera");
        aloe.setDescripcion("Planta suculenta conocida por sus propiedades medicinales y su gel refrescante.");
        aloe.setImagenResourceId(R.drawable.aloe);
        aloe.setFamilia("Asphodelaceae");
        aloe.setGenero("Aloe");
        aloe.setRiego("Riego escaso, dejar secar completamente entre riegos");
        aloe.setLuz("Luz directa o indirecta brillante");
        aloe.setTemperatura("15-30°C");
        aloe.setCuidados("Necesita buen drenaje, evitar exceso de agua");
        aloe.setToxicidad("No tóxica, gel comestible");
        aloe.setCrecimiento("Moderado");
        aloe.setPropagacion("Por hijuelos o esquejes de hoja");
        plantasList.add(aloe);
        
        // Calathea
        Planta calathea = new Planta();
        calathea.setNombre("Calathea");
        calathea.setNombreCientifico("Calathea orbifolia");
        calathea.setDescripcion("Planta tropical con hojas decorativas que se mueven siguiendo el ciclo del día y la noche.");
        calathea.setImagenResourceId(R.drawable.calathea);
        calathea.setFamilia("Marantaceae");
        calathea.setGenero("Calathea");
        calathea.setRiego("Riego regular, mantener suelo húmedo");
        calathea.setLuz("Luz indirecta, evitar sol directo");
        calathea.setTemperatura("18-27°C");
        calathea.setCuidados("Necesita alta humedad ambiental");
        calathea.setToxicidad("No tóxica");
        calathea.setCrecimiento("Moderado");
        calathea.setPropagacion("Por división de rizomas");
        plantasList.add(calathea);
        
        // Zamioculca
        Planta zamioculca = new Planta();
        zamioculca.setNombre("Zamioculca");
        zamioculca.setNombreCientifico("Zamioculcas zamiifolia");
        zamioculca.setDescripcion("Planta muy resistente que puede sobrevivir con poca luz y riego escaso.");
        zamioculca.setImagenResourceId(R.drawable.zamioculca);
        zamioculca.setFamilia("Araceae");
        zamioculca.setGenero("Zamioculcas");
        zamioculca.setRiego("Riego escaso, tolera sequía");
        zamioculca.setLuz("Luz indirecta, tolera poca luz");
        zamioculca.setTemperatura("15-30°C");
        zamioculca.setCuidados("Muy resistente, ideal para principiantes");
        zamioculca.setToxicidad("Tóxica para mascotas");
        zamioculca.setCrecimiento("Lento");
        zamioculca.setPropagacion("Por división de rizomas");
        plantasList.add(zamioculca);
        
        // Pilea
        Planta pilea = new Planta();
        pilea.setNombre("Pilea");
        pilea.setNombreCientifico("Pilea peperomioides");
        pilea.setDescripcion("Conocida como Planta del Dinero China, tiene hojas redondas y brillantes en tallos largos.");
        pilea.setImagenResourceId(R.drawable.pilea);
        pilea.setFamilia("Urticaceae");
        pilea.setGenero("Pilea");
        pilea.setRiego("Riego moderado, dejar secar entre riegos");
        pilea.setLuz("Luz indirecta brillante");
        pilea.setTemperatura("18-27°C");
        pilea.setCuidados("Girar planta regularmente para crecimiento uniforme");
        pilea.setToxicidad("No tóxica");
        pilea.setCrecimiento("Moderado");
        pilea.setPropagacion("Por hijuelos o esquejes de tallo");
        plantasList.add(pilea);
        
        // Cactus
        Planta cactus = new Planta();
        cactus.setNombre("Cactus");
        cactus.setNombreCientifico("Cactaceae");
        cactus.setDescripcion("Familia de plantas suculentas adaptadas a ambientes áridos, con espinas y formas variadas.");
        cactus.setImagenResourceId(R.drawable.cactus);
        cactus.setFamilia("Cactaceae");
        cactus.setGenero("Varios géneros");
        cactus.setRiego("Riego escaso, dejar secar completamente entre riegos");
        cactus.setLuz("Luz directa");
        cactus.setTemperatura("15-35°C");
        cactus.setCuidados("Necesita buen drenaje, evitar exceso de agua");
        cactus.setToxicidad("Depende de la especie");
        cactus.setCrecimiento("Lento a moderado");
        cactus.setPropagacion("Por semillas, esquejes o hijuelos");
        plantasList.add(cactus);
        
        // Suculenta
        Planta suculenta = new Planta();
        suculenta.setNombre("Suculenta");
        suculenta.setNombreCientifico("Crassula ovata");
        suculenta.setDescripcion("Planta carnosa que almacena agua en sus hojas, ideal para principiantes.");
        suculenta.setImagenResourceId(R.drawable.suculenta);
        suculenta.setFamilia("Crassulaceae");
        suculenta.setGenero("Crassula");
        suculenta.setRiego("Riego escaso, dejar secar completamente entre riegos");
        suculenta.setLuz("Luz directa o indirecta brillante");
        suculenta.setTemperatura("15-30°C");
        suculenta.setCuidados("Necesita buen drenaje, evitar exceso de agua");
        suculenta.setToxicidad("No tóxica");
        suculenta.setCrecimiento("Lento a moderado");
        suculenta.setPropagacion("Por esquejes de hoja o tallo");
        plantasList.add(suculenta);

        // Orquídea Phalaenopsis
        Planta orquidea = new Planta();
        orquidea.setNombre("Orquídea Phalaenopsis");
        orquidea.setNombreCientifico("Phalaenopsis spp.");
        orquidea.setDescripcion("Conocida como orquídea mariposa, es una de las orquídeas más populares y fáciles de cultivar en interiores, con flores elegantes y duraderas.");
        orquidea.setImagenResourceId(R.drawable.orquidea);
        orquidea.setFamilia("Orchidaceae");
        orquidea.setGenero("Phalaenopsis");
        orquidea.setRiego("Riego moderado, dejar secar entre riegos");
        orquidea.setLuz("Luz indirecta brillante, sin sol directo");
        orquidea.setTemperatura("18-29°C");
        orquidea.setCuidados("Maceta con buen drenaje, sustrato específico para orquídeas");
        orquidea.setToxicidad("No tóxica");
        orquidea.setCrecimiento("Lento");
        orquidea.setPropagacion("Por keikis (hijuelos) o división");
        plantasList.add(orquidea);

// Helecho Nido de Ave
        Planta helecho = new Planta();
        helecho.setNombre("Helecho Nido de Ave");
        helecho.setNombreCientifico("Asplenium nidus");
        helecho.setDescripcion("Helecho de interior con grandes frondas en forma de roseta que recuerdan a un nido, añade un toque tropical a cualquier espacio.");
        helecho.setImagenResourceId(R.drawable.helecho);
        helecho.setFamilia("Aspleniaceae");
        helecho.setGenero("Asplenium");
        helecho.setRiego("Riego regular, mantener sustrato húmedo");
        helecho.setLuz("Luz indirecta, evitar sol directo");
        helecho.setTemperatura("18-24°C");
        helecho.setCuidados("Alta humedad ambiental, pulverizar regularmente");
        helecho.setToxicidad("No tóxico");
        helecho.setCrecimiento("Moderado");
        helecho.setPropagacion("Por esporas o división");
        plantasList.add(helecho);

// Palmera Areca
        Planta areca = new Planta();
        areca.setNombre("Palmera Areca");
        areca.setNombreCientifico("Dypsis lutescens");
        areca.setDescripcion("Palmera de interior con tallos múltiples y hojas arqueadas y plumosas, ideal para purificar el aire y dar un aspecto tropical.");
        areca.setImagenResourceId(R.drawable.areca);
        areca.setFamilia("Arecaceae");
        areca.setGenero("Dypsis");
        areca.setRiego("Riego moderado, dejar secar ligeramente entre riegos");
        areca.setLuz("Luz indirecta brillante");
        areca.setTemperatura("18-24°C");
        areca.setCuidados("Humedad ambiental media-alta, evitar corrientes");
        areca.setToxicidad("No tóxica");
        areca.setCrecimiento("Moderado a lento");
        areca.setPropagacion("Por división de hijuelos");
        plantasList.add(areca);

// Anturio
        Planta anturio = new Planta();
        anturio.setNombre("Anturio");
        anturio.setNombreCientifico("Anthurium andraeanum");
        anturio.setDescripcion("Planta tropical con llamativas flores en forma de corazón de color rojo brillante y hojas grandes de color verde oscuro.");
        anturio.setImagenResourceId(R.drawable.anturio);
        anturio.setFamilia("Araceae");
        anturio.setGenero("Anthurium");
        anturio.setRiego("Riego moderado, dejar secar ligeramente entre riegos");
        anturio.setLuz("Luz indirecta brillante");
        anturio.setTemperatura("20-28°C");
        anturio.setCuidados("Alta humedad ambiental, limpiar hojas regularmente");
        anturio.setToxicidad("Tóxica para mascotas y humanos");
        anturio.setCrecimiento("Lento a moderado");
        anturio.setPropagacion("Por división o esquejes con raíces");
        plantasList.add(anturio);

// Planta del Dinero
        Planta planta_dinero = new Planta();
        planta_dinero.setNombre("Planta del Dinero");
        planta_dinero.setNombreCientifico("Crassula argentea");
        planta_dinero.setDescripcion("Suculenta de hojas carnosas, redondas y gruesas que se asemejan a monedas. Se le atribuyen propiedades de buena suerte y prosperidad.");
        planta_dinero.setImagenResourceId(R.drawable.planta_dinero);
        planta_dinero.setFamilia("Crassulaceae");
        planta_dinero.setGenero("Crassula");
        planta_dinero.setRiego("Riego escaso, dejar secar completamente entre riegos");
        planta_dinero.setLuz("Luz directa o indirecta brillante");
        planta_dinero.setTemperatura("15-26°C");
        planta_dinero.setCuidados("Sustrato bien drenado, poca agua en invierno");
        planta_dinero.setToxicidad("No tóxica");
        planta_dinero.setCrecimiento("Lento");
        planta_dinero.setPropagacion("Por esquejes de hoja o tallo");
        plantasList.add(planta_dinero);

// Lavanda
        Planta lavanda = new Planta();
        lavanda.setNombre("Lavanda");
        lavanda.setNombreCientifico("Lavandula angustifolia");
        lavanda.setDescripcion("Planta aromática con flores púrpuras y follaje grisáceo, conocida por su fragancia relajante y propiedades medicinales.");
        lavanda.setImagenResourceId(R.drawable.lavanda);
        lavanda.setFamilia("Lamiaceae");
        lavanda.setGenero("Lavandula");
        lavanda.setRiego("Riego escaso, tolerante a la sequía");
        lavanda.setLuz("Luz directa, pleno sol");
        lavanda.setTemperatura("15-30°C");
        lavanda.setCuidados("Sustrato bien drenado, poda anual");
        lavanda.setToxicidad("No tóxica para humanos, puede ser tóxica para mascotas");
        lavanda.setCrecimiento("Moderado");
        lavanda.setPropagacion("Por esquejes o semillas");
        plantasList.add(lavanda);

// Planta Araña
        Planta arana = new Planta();
        arana.setNombre("Planta Araña");
        arana.setNombreCientifico("Chlorophytum comosum");
        arana.setDescripcion("Planta muy resistente con hojas arqueadas verdes o variegadas que produce pequeñas 'plántulas' en sus tallos colgantes.");
        arana.setImagenResourceId(R.drawable.planta_arana);
        arana.setFamilia("Asparagaceae");
        arana.setGenero("Chlorophytum");
        arana.setRiego("Riego moderado, dejar secar ligeramente entre riegos");
        arana.setLuz("Luz indirecta a semisombra");
        arana.setTemperatura("13-27°C");
        arana.setCuidados("Tolera condiciones variadas, ideal para principiantes");
        arana.setToxicidad("No tóxica, segura para mascotas");
        arana.setCrecimiento("Moderado a rápido");
        arana.setPropagacion("Por hijuelos o división");
        plantasList.add(arana);



        // Actualizar UI
        plantaAdapter.notifyDataSetChanged();
        rvPlantas.setVisibility(View.VISIBLE);
        tvEmptyState.setVisibility(View.GONE);
        isLastPage = true;
        isLoading = false;
        progressBar.setVisibility(View.GONE);

    }
    
    private void buscarPlantasEnAPI(String query) {
        if (query.length() < 2) {
            mostrarMensajeVacio("Ingresa al menos 2 caracteres para buscar");
            return;
        }

        isLoading = true;
        progressBar.setVisibility(View.VISIBLE);
        tvEmptyState.setVisibility(View.GONE);
        
        // Filtrar plantas existentes
        List<Planta> resultados = new ArrayList<>();
        for (Planta planta : plantasList) {
            if (planta.getNombre().toLowerCase().contains(query.toLowerCase()) ||
                planta.getNombreCientifico().toLowerCase().contains(query.toLowerCase())) {
                resultados.add(planta);
            }
        }
        
        // Actualizar UI
        plantaAdapter = new PlantaAdapter(this, resultados, this);
        rvPlantas.setAdapter(plantaAdapter);
        plantaAdapter.notifyDataSetChanged();
        
        if (resultados.isEmpty()) {
            mostrarMensajeVacio("No se encontraron resultados para \"" + query + "\"");
        } else {
            rvPlantas.setVisibility(View.VISIBLE);
            tvEmptyState.setVisibility(View.GONE);
        }
        
        isLoading = false;
        progressBar.setVisibility(View.GONE);
    }
    
    private void mostrarMensajeVacio(String mensaje) {
        tvEmptyState.setText(mensaje);
        tvEmptyState.setVisibility(View.VISIBLE);
        rvPlantas.setVisibility(View.GONE);
    }
    
    private void mostrarError(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
        mostrarMensajeVacio("Error al cargar las plantas");
    }

    @Override
    public void onPlantaClick(Planta planta, int position) {
        mostrarDetallePlanta(planta);
    }

    private void mostrarDetallePlanta(Planta planta) {
        Intent intent = new Intent(this, DetallePlantaActivity.class);
        intent.putExtra("NOMBRE_PLANTA", planta.getNombre());
        intent.putExtra("NOMBRE_CIENTIFICO", planta.getNombreCientifico());
        intent.putExtra("DESCRIPCION", planta.getDescripcion());
        intent.putExtra("IMAGEN_ID", planta.getImagenResourceId());
        intent.putExtra("FAMILIA", planta.getFamilia());
        intent.putExtra("GENERO", planta.getGenero());
        intent.putExtra("RIEGO", planta.getRiego());
        intent.putExtra("LUZ", planta.getLuz());
        intent.putExtra("TEMPERATURA", planta.getTemperatura());
        intent.putExtra("CUIDADOS", planta.getCuidados());
        intent.putExtra("TOXICIDAD", planta.getToxicidad());
        intent.putExtra("CRECIMIENTO", planta.getCrecimiento());
        intent.putExtra("PROPAGACION", planta.getPropagacion());
        startActivity(intent);
    }
}