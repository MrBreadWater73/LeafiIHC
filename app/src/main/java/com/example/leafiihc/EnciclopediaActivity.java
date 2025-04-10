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

import com.example.leafiihc.api.TrefleApiClient;
import com.example.leafiihc.api.TrefleApiService;
import com.example.leafiihc.api.TreflePlant;
import com.example.leafiihc.api.TrefleSearchResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnciclopediaActivity extends AppCompatActivity implements PlantaAdapter.OnPlantaClickListener {

    private RecyclerView rvPlantas;
    private PlantaAdapter plantaAdapter;
    private List<Planta> plantasList;
    private EditText etSearch;
    private TextView tvEmptyState;
    private ImageView ivHome;
    private ProgressBar progressBar;
    private View btnEnfermedades;
    private TrefleApiService apiService;
    private static final String API_KEY = "iEzGUK_ulXxuTWcJrnJ3KregnfmjPgf8jyurisNPVH8";
    private static final int ITEMS_PER_PAGE = 30; // Trefle uses 30 items per page by default

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
        
        // Inicializar el servicio de API
        apiService = TrefleApiClient.getInstance().getApiService();

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
        
        // Actualizar UI
        plantaAdapter.notifyDataSetChanged();
        rvPlantas.setVisibility(View.VISIBLE);
        tvEmptyState.setVisibility(View.GONE);
        isLastPage = true;
        isLoading = false;
        progressBar.setVisibility(View.GONE);
        // --- Fin: Datos de Ejemplo ---

        /* // --- Código Original de la API (Comentado temporalmente) ---
        apiService.getPlants(API_KEY, currentPage).enqueue(new Callback<TrefleSearchResponse>() {
            @Override
            public void onResponse(Call<TrefleSearchResponse> call, Response<TrefleSearchResponse> response) {
                isLoading = false;
                progressBar.setVisibility(View.GONE);
                
                if (response.isSuccessful() && response.body() != null) {
                    TrefleSearchResponse searchResponse = response.body();
                    List<TreflePlant> treflePlants = searchResponse.getData();
                    
                    if (treflePlants != null && !treflePlants.isEmpty()) {
                        // Limitar a las primeras 10 plantas
                        int limit = Math.min(treflePlants.size(), 10);
                        for (int i = 0; i < limit; i++) {
                            TreflePlant treflePlant = treflePlants.get(i);
                            Planta planta = convertirTreflePlantAPlanta(treflePlant);
                            plantasList.add(planta);
                        }
                        
                        plantaAdapter.notifyDataSetChanged();
                        rvPlantas.setVisibility(View.VISIBLE);
                        tvEmptyState.setVisibility(View.GONE);
                        
                        // No cargar más páginas
                        isLastPage = true;
                    } else {
                        mostrarMensajeVacio("No se encontraron plantas");
                    }
                } else {
                    String errorBody = "";
                    try {
                        if (response.errorBody() != null) {
                            errorBody = response.errorBody().string();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String error = "Error al cargar las plantas: " + response.code() + 
                        " " + response.message() + "\n" + errorBody;
                    mostrarError(error);
                }
            }

            @Override
            public void onFailure(Call<TrefleSearchResponse> call, Throwable t) {
                isLoading = false;
                progressBar.setVisibility(View.GONE);
                mostrarError("Error de conexión: " + t.getMessage());
            }
        });
        */ // --- Fin Código Original ---
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
    
    private Planta convertirTreflePlantAPlanta(TreflePlant treflePlant) {
        Planta planta = new Planta();
        
        // Set basic information
        planta.setNombre(treflePlant.getCommonName() != null ? treflePlant.getCommonName() : "Sin nombre común");
        planta.setNombreCientifico(treflePlant.getScientificName() != null ? treflePlant.getScientificName() : "Sin nombre científico");
        
        // Set description
        StringBuilder descripcion = new StringBuilder();
        if (treflePlant.getDescription() != null) {
            descripcion.append("Description: ").append(treflePlant.getDescription());
        }
        if (treflePlant.getPlantingDescription() != null) {
            if (descripcion.length() > 0) descripcion.append("\n\n");
            descripcion.append("Planting: ").append(treflePlant.getPlantingDescription());
        }
        if (descripcion.length() == 0) {
            descripcion.append("Sin descripción disponible");
        }
        planta.setDescripcion(descripcion.toString().trim());
        
        // Set family information
        String familia = "Desconocida";
        if (treflePlant.getFamily() != null) {
            familia = treflePlant.getFamily();
            if (treflePlant.getFamilyCommonName() != null) {
                familia += " (" + treflePlant.getFamilyCommonName() + ")";
            }
        }
        planta.setFamilia(familia);
        
        // Set genus
        planta.setGenero(treflePlant.getGenus() != null ? treflePlant.getGenus() : "Desconocido");
        
        // Set watering information based on soil humidity
        StringBuilder riego = new StringBuilder();
        if (treflePlant.getSoilHumidity() != null) {
            riego.append("Soil humidity level: ").append(treflePlant.getSoilHumidity()).append("/10");
        }
        if (treflePlant.getAtmosphericHumidity() != null) {
            if (riego.length() > 0) riego.append("\n");
            riego.append("Atmospheric humidity: ").append(treflePlant.getAtmosphericHumidity()).append("/10");
        }
        planta.setRiego(riego.length() > 0 ? riego.toString() : "Desconocido");
        
        // Set light information
        StringBuilder luz = new StringBuilder();
        if (treflePlant.getLight() != null) {
            luz.append("Light requirement: ").append(treflePlant.getLight()).append("/10");
            luz.append(" (0 = no light, 10 = very intensive sunlight)");
        }
        planta.setLuz(luz.length() > 0 ? luz.toString() : "Desconocido");
        
        // Set temperature information
        StringBuilder temperatura = new StringBuilder();
        if (treflePlant.getMinimumTemperature() != null && treflePlant.getMinimumTemperature().getCelsius() != null) {
            temperatura.append("Minimum: ").append(treflePlant.getMinimumTemperature().getCelsius()).append("°C");
        }
        if (treflePlant.getMaximumTemperature() != null && treflePlant.getMaximumTemperature().getCelsius() != null) {
            if (temperatura.length() > 0) temperatura.append("\n");
            temperatura.append("Maximum: ").append(treflePlant.getMaximumTemperature().getCelsius()).append("°C");
        }
        planta.setTemperatura(temperatura.length() > 0 ? temperatura.toString() : "Información no disponible");
        
        // Set care instructions
        StringBuilder cuidados = new StringBuilder();
        if (treflePlant.getPlantingDescription() != null) {
            cuidados.append("Planting instructions: ").append(treflePlant.getPlantingDescription()).append("\n\n");
        }
        if (treflePlant.getSowingDescription() != null) {
            cuidados.append("Sowing instructions: ").append(treflePlant.getSowingDescription()).append("\n\n");
        }
        if (treflePlant.getEdible() != null) {
            cuidados.append("Edible: ").append(treflePlant.getEdible() ? "Yes" : "No");
            if (treflePlant.getEdibleParts() != null && !treflePlant.getEdibleParts().isEmpty()) {
                cuidados.append("\nEdible parts: ").append(String.join(", ", treflePlant.getEdibleParts()));
            }
            cuidados.append("\n\n");
        }
        planta.setCuidados(cuidados.length() > 0 ? cuidados.toString().trim() : "No care information available");
        
        // Set growth information
        StringBuilder crecimiento = new StringBuilder();
        if (treflePlant.getGrowthRate() != null) {
            crecimiento.append("Growth rate: ").append(treflePlant.getGrowthRate()).append("\n");
        }
        if (treflePlant.getGrowthForm() != null) {
            crecimiento.append("Growth form: ").append(treflePlant.getGrowthForm()).append("\n");
        }
        if (treflePlant.getGrowthHabit() != null) {
            crecimiento.append("Growth habit: ").append(treflePlant.getGrowthHabit()).append("\n");
        }
        if (treflePlant.getGrowthMonths() != null && !treflePlant.getGrowthMonths().isEmpty()) {
            crecimiento.append("Growth months: ").append(String.join(", ", treflePlant.getGrowthMonths())).append("\n");
        }
        if (treflePlant.getBloomMonths() != null && !treflePlant.getBloomMonths().isEmpty()) {
            crecimiento.append("Bloom months: ").append(String.join(", ", treflePlant.getBloomMonths())).append("\n");
        }
        if (treflePlant.getFruitMonths() != null && !treflePlant.getFruitMonths().isEmpty()) {
            crecimiento.append("Fruit months: ").append(String.join(", ", treflePlant.getFruitMonths()));
        }
        planta.setCrecimiento(crecimiento.length() > 0 ? crecimiento.toString() : "No growth information available");
        
        // Set propagation information
        StringBuilder propagacion = new StringBuilder();
        if (treflePlant.getSowingDescription() != null) {
            propagacion.append("Sowing method: ").append(treflePlant.getSowingDescription());
        }
        planta.setPropagacion(propagacion.length() > 0 ? propagacion.toString() : "No propagation information available");
        
        // Set toxicity information
        StringBuilder toxicidad = new StringBuilder();
        if (treflePlant.getToxicity() != null) {
            toxicidad.append("Toxicity level: ").append(treflePlant.getToxicity());
        }
        planta.setToxicidad(toxicidad.length() > 0 ? toxicidad.toString() : "No toxicity information available");
        
        // Set diseases and pests information
        planta.setEnfermedades("Disease information not available in Trefle API");
        planta.setPlagas("Pest information not available in Trefle API");
        
        // Set known problems
        StringBuilder problemas = new StringBuilder();
        if (treflePlant.getAdaptedToCoarseTexturedSoils() != null) {
            problemas.append("Adapted to coarse textured soils: ").append(treflePlant.getAdaptedToCoarseTexturedSoils()).append("\n");
        }
        if (treflePlant.getAdaptedToFineTexturedSoils() != null) {
            problemas.append("Adapted to fine textured soils: ").append(treflePlant.getAdaptedToFineTexturedSoils()).append("\n");
        }
        if (treflePlant.getAdaptedToMediumTexturedSoils() != null) {
            problemas.append("Adapted to medium textured soils: ").append(treflePlant.getAdaptedToMediumTexturedSoils());
        }
        planta.setProblemasConocidos(problemas.length() > 0 ? problemas.toString() : "No known problems information available");
        
        // Set planting instructions
        StringBuilder instrucciones = new StringBuilder();
        if (treflePlant.getPlantingDescription() != null) {
            instrucciones.append(treflePlant.getPlantingDescription()).append("\n");
        }
        if (treflePlant.getMinimumPrecipitation() != null && treflePlant.getMinimumPrecipitation().getValue() != null) {
            instrucciones.append("Minimum precipitation required: ")
                .append(treflePlant.getMinimumPrecipitation().getValue())
                .append(" ")
                .append(treflePlant.getMinimumPrecipitation().getUnit())
                .append("/year\n");
        }
        if (treflePlant.getMaximumPrecipitation() != null && treflePlant.getMaximumPrecipitation().getValue() != null) {
            instrucciones.append("Maximum precipitation tolerance: ")
                .append(treflePlant.getMaximumPrecipitation().getValue())
                .append(" ")
                .append(treflePlant.getMaximumPrecipitation().getUnit())
                .append("/year\n");
        }
        if (treflePlant.getMinimumRootDepth() != null && treflePlant.getMinimumRootDepth().getValue() != null) {
            instrucciones.append("Minimum root depth required: ")
                .append(treflePlant.getMinimumRootDepth().getValue())
                .append(" ")
                .append(treflePlant.getMinimumRootDepth().getUnit());
        }
        planta.setInstruccionesCuidado(instrucciones.length() > 0 ? instrucciones.toString() : "No care instructions available");
        
        // Set image URL
        planta.setImageUrl(treflePlant.getImageUrl());
        
        // Set Trefle ID for future reference
        planta.setPerenualId(treflePlant.getId());
        
        return planta;
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