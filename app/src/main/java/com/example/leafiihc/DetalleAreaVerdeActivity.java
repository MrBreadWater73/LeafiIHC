package com.example.leafiihc;

import com.example.leafiihc.services.GreenAreaMonitoringService;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetalleAreaVerdeActivity extends AppCompatActivity {

    private ImageView ivBack, ivHome;
    private CircleImageView ivUserAvatar;
    private TextView tvNombreAreaDetalle, tvCodigoAreaDetalle, tvFechaRegistro;
    private TextView tvEstadoArea, tvUltimaRevision, tvHumedad, tvLuz;
    private ProgressBar pbHumedad, pbLuz;
    private Button btnRegistrarActividad, btnEditarArea;
    private RecyclerView rvActividades;
    private TextView tvSinActividades, tvSinTareas;
    private ActividadAdapter actividadAdapter;
    private Button btnAgregarTarea;
    private Button btnIniciarMonitoreo;
    private Button btnDetenerMonitoreo;
    private RecyclerView rvTareas;
    private TareaAdapter tareaAdapter;

    private List<AreaVerde> areasVerdesList;
    private AreaVerde areaVerde;
    private String codigoArea;
    private boolean modoEdicion;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_area_verde);

        // Start monitoring service
        Intent serviceIntent = new Intent(this, GreenAreaMonitoringService.class);
        startService(serviceIntent);

        // Request notification permission if needed
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) 
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, 
                    new String[]{Manifest.permission.POST_NOTIFICATIONS}, 
                    1);
            }
        }

        // Inicializar vistas
        initializeViews();

        // Obtener datos del intent
        codigoArea = getIntent().getStringExtra("CODIGO_AREA");
        modoEdicion = getIntent().getBooleanExtra("MODO_EDICION", false);

        if (codigoArea == null) {
            Toast.makeText(this, "Error: No se proporcionó código de área", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Cargar áreas verdes y buscar la seleccionada
        cargarAreasVerdes();
        buscarAreaVerde();

        // Mostrar datos del área verde
        if (areaVerde != null) {
            mostrarDatosAreaVerde();

            // Si viene en modo edición, mostrar el diálogo de edición automáticamente
            if (modoEdicion) {
                mostrarDialogoEditarArea();
            }
        } else {
            Toast.makeText(this, "No se encontró el área verde", Toast.LENGTH_SHORT).show();
            finish();
        }

        // Configurar listeners
        setupListeners();

        // Configurar adaptadores
        setupAdapters();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Stop monitoring service when activity is destroyed
        Intent serviceIntent = new Intent(this, GreenAreaMonitoringService.class);
        stopService(serviceIntent);
    }

    private void initializeViews() {
        // Inicializar vistas del header
        ivBack = findViewById(R.id.ivBack);
        ivHome = findViewById(R.id.ivHome);
        ivUserAvatar = findViewById(R.id.ivUserAvatar);
        
        // Inicializar TextViews
        tvNombreAreaDetalle = findViewById(R.id.tvNombreAreaDetalle);
        tvCodigoAreaDetalle = findViewById(R.id.tvCodigoAreaDetalle);
        tvFechaRegistro = findViewById(R.id.tvFechaRegistro);
        tvEstadoArea = findViewById(R.id.tvEstadoArea);
        tvUltimaRevision = findViewById(R.id.tvUltimaRevision);
        tvHumedad = findViewById(R.id.tvHumedad);
        tvLuz = findViewById(R.id.tvLuz);
        tvSinActividades = findViewById(R.id.tvSinActividades);
        tvSinTareas = findViewById(R.id.tvSinTareas);

        // Inicializar ProgressBars
        pbHumedad = findViewById(R.id.pbHumedad);
        pbLuz = findViewById(R.id.pbLuz);

        // Inicializar botones
        btnRegistrarActividad = findViewById(R.id.btnRegistrarActividad);
        btnEditarArea = findViewById(R.id.btnEditarArea);
        btnAgregarTarea = findViewById(R.id.btnAgregarTarea);
        btnIniciarMonitoreo = findViewById(R.id.btnIniciarMonitoreo);
        btnDetenerMonitoreo = findViewById(R.id.btnDetenerMonitoreo);

        // Inicializar RecyclerViews
        rvActividades = findViewById(R.id.rvActividades);
        if (rvActividades != null) {
            rvActividades.setLayoutManager(new LinearLayoutManager(this));
            rvActividades.setNestedScrollingEnabled(false);
        }

        rvTareas = findViewById(R.id.rvTareas);
        if (rvTareas != null) {
            rvTareas.setLayoutManager(new LinearLayoutManager(this));
            rvTareas.setNestedScrollingEnabled(false);
        }
    }

    private void setupListeners() {
        ivBack.setOnClickListener(v -> finish());

        ivHome.setOnClickListener(v -> {
            Intent intent = new Intent(DetalleAreaVerdeActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });

        ivUserAvatar.setOnClickListener(v -> {
            Intent intent = new Intent(DetalleAreaVerdeActivity.this, PerfilUsuarioActivity.class);
            startActivity(intent);
        });

        btnRegistrarActividad.setOnClickListener(v -> mostrarDialogoRegistrarActividad());
        btnEditarArea.setOnClickListener(v -> mostrarDialogoEditarArea());
        btnAgregarTarea.setOnClickListener(v -> mostrarDialogoAgregarTarea());
        btnIniciarMonitoreo.setOnClickListener(v -> iniciarMonitoreo());
        btnDetenerMonitoreo.setOnClickListener(v -> detenerMonitoreo());
    }

    private void setupAdapters() {
        if (areaVerde != null) {
            // Configurar adaptador de actividades
            if (rvActividades != null) {
                if (areaVerde.getActividades() != null && !areaVerde.getActividades().isEmpty()) {
                    actividadAdapter = new ActividadAdapter(this, areaVerde.getActividades());
                    rvActividades.setAdapter(actividadAdapter);
                    rvActividades.setVisibility(View.VISIBLE);
                    if (tvSinActividades != null) {
                        tvSinActividades.setVisibility(View.GONE);
                    }
                } else {
                    rvActividades.setVisibility(View.GONE);
                    if (tvSinActividades != null) {
                        tvSinActividades.setVisibility(View.VISIBLE);
                    }
                }
            }

            // Configurar adaptador de tareas
            if (rvTareas != null) {
                if (areaVerde.getTareas() != null && !areaVerde.getTareas().isEmpty()) {
                    tareaAdapter = new TareaAdapter(this, areaVerde.getTareas(), new TareaAdapter.OnTareaListener() {
                        @Override
                        public void onTareaCompletadaChange(AreaVerde.Tarea tarea, boolean isChecked) {
                            tarea.setCompletada(isChecked);
                            guardarAreasVerdes();
                        }

                        @Override
                        public void onTareaDelete(AreaVerde.Tarea tarea, int position) {
                            areaVerde.getTareas().remove(position);
                            tareaAdapter.notifyItemRemoved(position);
                            guardarAreasVerdes();
                        }
                    });
                    rvTareas.setAdapter(tareaAdapter);
                    rvTareas.setVisibility(View.VISIBLE);
                    if (tvSinTareas != null) {
                        tvSinTareas.setVisibility(View.GONE);
                    }
                } else {
                    rvTareas.setVisibility(View.GONE);
                    if (tvSinTareas != null) {
                        tvSinTareas.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    private void cargarAreasVerdes() {
        SharedPreferences prefs = getSharedPreferences("AreasVerdesPrefs", MODE_PRIVATE);
        String areasJson = prefs.getString("areasVerdes", "");

        if (!areasJson.isEmpty()) {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<AreaVerde>>(){}.getType();
            areasVerdesList = gson.fromJson(areasJson, type);
        } else {
            areasVerdesList = new ArrayList<>();
        }
    }

    private void guardarAreasVerdes() {
        SharedPreferences prefs = getSharedPreferences("AreasVerdesPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Gson gson = new Gson();
        String areasJson = gson.toJson(areasVerdesList);
        editor.putString("areasVerdes", areasJson);
        editor.apply();
    }

    private void buscarAreaVerde() {
        for (AreaVerde area : areasVerdesList) {
            if (area.getCodigo().equals(codigoArea)) {
                areaVerde = area;
                break;
            }
        }
    }

    private void mostrarDatosAreaVerde() {
        tvNombreAreaDetalle.setText(areaVerde.getNombre());
        tvCodigoAreaDetalle.setText(areaVerde.getCodigo());
        tvFechaRegistro.setText(sdf.format(areaVerde.getFechaRegistro()));
        tvEstadoArea.setText(areaVerde.getEstado());
        tvUltimaRevision.setText(sdf.format(areaVerde.getUltimaRevision()));

        // Configurar barras de progreso
        pbHumedad.setProgress(areaVerde.getNivelHumedad());
        tvHumedad.setText(areaVerde.getNivelHumedad() + "%");

        pbLuz.setProgress(areaVerde.getNivelLuz());
        tvLuz.setText(areaVerde.getNivelLuz() + "%");

        // Configurar color del estado según su valor
        if (areaVerde.getEstado().equals("Saludable")) {
            tvEstadoArea.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        } else if (areaVerde.getEstado().equals("Atención")) {
            tvEstadoArea.setTextColor(getResources().getColor(android.R.color.holo_orange_dark));
        } else {
            tvEstadoArea.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }

        // Actualizar lista de actividades
        actualizarListaActividades();
    }

    private void actualizarListaActividades() {
        if (areaVerde.getActividades() != null && !areaVerde.getActividades().isEmpty()) {
            if (tvSinActividades != null) {
                tvSinActividades.setVisibility(View.GONE);
            }
            if (rvActividades != null) {
                rvActividades.setVisibility(View.VISIBLE);
                actividadAdapter = new ActividadAdapter(this, areaVerde.getActividades());
                rvActividades.setAdapter(actividadAdapter);
            }
        } else {
            if (tvSinActividades != null) {
                tvSinActividades.setVisibility(View.VISIBLE);
            }
            if (rvActividades != null) {
                rvActividades.setVisibility(View.GONE);
            }
        }
    }

    private void mostrarDialogoRegistrarActividad() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_registrar_actividad);
        dialog.setCancelable(true);

        // Inicializar vistas del diálogo
        final Spinner spinnerTipoActividad = dialog.findViewById(R.id.spinnerTipoActividad);
        final EditText etFechaActividad = dialog.findViewById(R.id.etFechaActividad);
        final EditText etDescripcionActividad = dialog.findViewById(R.id.etDescripcionActividad);
        final ImageView ivCalendario = dialog.findViewById(R.id.ivCalendario);
        Button btnCancelarActividad = dialog.findViewById(R.id.btnCancelarActividad);
        Button btnGuardarActividad = dialog.findViewById(R.id.btnGuardarActividad);

        // Configurar spinner de tipos de actividad
        String[] tiposActividad = {"Riego", "Fertilización", "Poda", "Fumigación", "Limpieza", "Otro"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tiposActividad);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoActividad.setAdapter(adapter);

        // Configurar fecha actual
        final Calendar calendar = Calendar.getInstance();
        etFechaActividad.setText(sdf.format(calendar.getTime()));

        // Configurar selector de fecha
        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                etFechaActividad.setText(sdf.format(calendar.getTime()));
            }
        };

        etFechaActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(DetalleAreaVerdeActivity.this, dateSetListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        ivCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(DetalleAreaVerdeActivity.this, dateSetListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // Configurar botones
        btnCancelarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnGuardarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validar campos
                if (etDescripcionActividad.getText().toString().trim().isEmpty()) {
                    Toast.makeText(DetalleAreaVerdeActivity.this, "Por favor ingresa una descripción", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    // Crear nueva actividad
                    String tipo = spinnerTipoActividad.getSelectedItem().toString();
                    Date fecha = sdf.parse(etFechaActividad.getText().toString());
                    String descripcion = etDescripcionActividad.getText().toString().trim();

                    AreaVerde.Actividad nuevaActividad = new AreaVerde.Actividad(tipo, fecha, descripcion);
                    areaVerde.addActividad(nuevaActividad);

                    // Actualizar fecha de última revisión
                    areaVerde.setUltimaRevision(new Date());

                    // Guardar cambios
                    guardarAreasVerdes();

                    // Actualizar UI
                    mostrarDatosAreaVerde();
                    actualizarListaActividades();

                    Toast.makeText(DetalleAreaVerdeActivity.this, "Actividad registrada correctamente", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                } catch (ParseException e) {
                    Toast.makeText(DetalleAreaVerdeActivity.this, "Error al procesar la fecha", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }

    private void mostrarDialogoEditarArea() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_editar_area_verde);
        dialog.setCancelable(true);

        // Inicializar vistas del diálogo
        final EditText etNombreArea = dialog.findViewById(R.id.etNombreArea);
        final EditText etCodigoArea = dialog.findViewById(R.id.etCodigoArea);
        final Spinner spinnerEstadoArea = dialog.findViewById(R.id.spinnerEstadoArea);
        final SeekBar seekBarHumedad = dialog.findViewById(R.id.seekBarHumedad);
        final TextView tvValorHumedad = dialog.findViewById(R.id.tvValorHumedad);
        final SeekBar seekBarLuz = dialog.findViewById(R.id.seekBarLuz);
        final TextView tvValorLuz = dialog.findViewById(R.id.tvValorLuz);
        Button btnCancelarEdicion = dialog.findViewById(R.id.btnCancelarEdicion);
        Button btnGuardarEdicion = dialog.findViewById(R.id.btnGuardarEdicion);

        // Configurar spinner de estados
        String[] estados = {"Saludable", "Atención", "Crítico"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, estados);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstadoArea.setAdapter(adapter);

        // Cargar datos actuales
        etNombreArea.setText(areaVerde.getNombre());
        etCodigoArea.setText(areaVerde.getCodigo());

        // Seleccionar estado actual
        for (int i = 0; i < estados.length; i++) {
            if (estados[i].equals(areaVerde.getEstado())) {
                spinnerEstadoArea.setSelection(i);
                break;
            }
        }

        // Configurar seekbars
        seekBarHumedad.setProgress(areaVerde.getNivelHumedad());
        tvValorHumedad.setText(areaVerde.getNivelHumedad() + "%");

        seekBarLuz.setProgress(areaVerde.getNivelLuz());
        tvValorLuz.setText(areaVerde.getNivelLuz() + "%");

        // Configurar listeners de seekbars
        seekBarHumedad.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvValorHumedad.setText(progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        seekBarLuz.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvValorLuz.setText(progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Configurar botones
        btnCancelarEdicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnGuardarEdicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validar campos
                if (etNombreArea.getText().toString().trim().isEmpty()) {
                    Toast.makeText(DetalleAreaVerdeActivity.this, "Por favor ingresa un nombre", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Actualizar datos del área verde
                areaVerde.setNombre(etNombreArea.getText().toString().trim());
                areaVerde.setEstado(spinnerEstadoArea.getSelectedItem().toString());
                areaVerde.setNivelHumedad(seekBarHumedad.getProgress());
                areaVerde.setNivelLuz(seekBarLuz.getProgress());
                areaVerde.setUltimaRevision(new Date());

                // Guardar cambios
                guardarAreasVerdes();

                // Actualizar UI
                mostrarDatosAreaVerde();

                Toast.makeText(DetalleAreaVerdeActivity.this, "Información actualizada correctamente", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void mostrarDialogoAgregarTarea() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_agregar_tarea);
        dialog.setCancelable(true);

        // Inicializar vistas del diálogo
        final EditText etTituloTarea = dialog.findViewById(R.id.etTituloTarea);
        final EditText etDescripcionTarea = dialog.findViewById(R.id.etDescripcionTarea);
        final EditText etFechaTarea = dialog.findViewById(R.id.etFechaTarea);
        final Spinner spinnerPrioridad = dialog.findViewById(R.id.spinnerPrioridad);
        final Spinner spinnerCategoria = dialog.findViewById(R.id.spinnerCategoria);
        final ImageView ivCalendarioTarea = dialog.findViewById(R.id.ivCalendarioTarea);
        Button btnCancelarTarea = dialog.findViewById(R.id.btnCancelarTarea);
        Button btnGuardarTarea = dialog.findViewById(R.id.btnGuardarTarea);

        // Configurar spinners
        String[] prioridades = {"Alta", "Media", "Baja"};
        ArrayAdapter<String> adapterPrioridad = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, prioridades);
        adapterPrioridad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPrioridad.setAdapter(adapterPrioridad);

        String[] categorias = {"Riego", "Fertilización", "Poda", "Limpieza", "Fumigación", "Mantenimiento", "Otro"};
        ArrayAdapter<String> adapterCategoria = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categorias);
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapterCategoria);

        // Configurar fecha actual
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1); // Por defecto, mañana
        etFechaTarea.setText(sdf.format(calendar.getTime()));

        // Configurar selector de fecha
        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                etFechaTarea.setText(sdf.format(calendar.getTime()));
            }
        };

        etFechaTarea.setOnClickListener(v -> {
            new DatePickerDialog(DetalleAreaVerdeActivity.this, dateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        ivCalendarioTarea.setOnClickListener(v -> {
            new DatePickerDialog(DetalleAreaVerdeActivity.this, dateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        // Configurar botones
        btnCancelarTarea.setOnClickListener(v -> dialog.dismiss());

        btnGuardarTarea.setOnClickListener(v -> {
            // Validar campos
            if (etTituloTarea.getText().toString().trim().isEmpty()) {
                Toast.makeText(DetalleAreaVerdeActivity.this, "Por favor ingresa un título", Toast.LENGTH_SHORT).show();
                return;
            }

            if (etDescripcionTarea.getText().toString().trim().isEmpty()) {
                Toast.makeText(DetalleAreaVerdeActivity.this, "Por favor ingresa una descripción", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                // Crear nueva tarea
                String titulo = etTituloTarea.getText().toString().trim();
                String descripcion = etDescripcionTarea.getText().toString().trim();
                Date fecha = sdf.parse(etFechaTarea.getText().toString());
                String prioridad = spinnerPrioridad.getSelectedItem().toString();
                String categoria = spinnerCategoria.getSelectedItem().toString();

                // Si la lista de tareas no existe, crearla
                if (areaVerde.getTareas() == null) {
                    areaVerde.setTareas(new ArrayList<>());
                }

                // Agregar la nueva tarea
                AreaVerde.Tarea nuevaTarea = new AreaVerde.Tarea(titulo, descripcion, fecha, prioridad, categoria);
                areaVerde.getTareas().add(nuevaTarea);

                // Guardar cambios
                guardarAreasVerdes();

                // Actualizar la interfaz
                if (tareaAdapter == null) {
                    if (rvTareas != null) {
                        tareaAdapter = new TareaAdapter(this, areaVerde.getTareas(), new TareaAdapter.OnTareaListener() {
                            @Override
                            public void onTareaCompletadaChange(AreaVerde.Tarea tarea, boolean isChecked) {
                                tarea.setCompletada(isChecked);
                                guardarAreasVerdes();
                            }

                            @Override
                            public void onTareaDelete(AreaVerde.Tarea tarea, int position) {
                                areaVerde.getTareas().remove(position);
                                tareaAdapter.notifyItemRemoved(position);
                                guardarAreasVerdes();
                            }
                        });
                        rvTareas.setAdapter(tareaAdapter);
                    }
                } else {
                    tareaAdapter.notifyDataSetChanged();
                }

                // Actualizar visibilidad
                if (rvTareas != null) {
                    rvTareas.setVisibility(View.VISIBLE);
                }
                if (tvSinTareas != null) {
                    tvSinTareas.setVisibility(View.GONE);
                }

                Toast.makeText(DetalleAreaVerdeActivity.this, "Tarea agregada correctamente", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            } catch (ParseException e) {
                Toast.makeText(DetalleAreaVerdeActivity.this, "Error al procesar la fecha", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

    private void iniciarMonitoreo() {
        Intent serviceIntent = new Intent(this, GreenAreaMonitoringService.class);
        serviceIntent.putExtra("codigo_area", areaVerde.getCodigo());
        startService(serviceIntent);
        Toast.makeText(this, "Monitoreo iniciado", Toast.LENGTH_SHORT).show();
    }

    private void detenerMonitoreo() {
        Intent serviceIntent = new Intent(this, GreenAreaMonitoringService.class);
        stopService(serviceIntent);
        Toast.makeText(this, "Monitoreo detenido", Toast.LENGTH_SHORT).show();
    }
}