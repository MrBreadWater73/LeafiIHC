package com.example.leafiihc;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private TextView tvSinActividades;
    private ActividadAdapter actividadAdapter;

    private List<AreaVerde> areasVerdesList;
    private AreaVerde areaVerde;
    private String codigoArea;
    private boolean modoEdicion;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_area_verde);

        // Obtener datos del intent
        codigoArea = getIntent().getStringExtra("CODIGO_AREA");
        modoEdicion = getIntent().getBooleanExtra("MODO_EDICION", false);

        // Inicializar vistas
        ivBack = findViewById(R.id.ivBack);
        ivHome = findViewById(R.id.ivHome);
        ivUserAvatar = findViewById(R.id.ivUserAvatar);
        tvNombreAreaDetalle = findViewById(R.id.tvNombreAreaDetalle);
        tvCodigoAreaDetalle = findViewById(R.id.tvCodigoAreaDetalle);
        tvFechaRegistro = findViewById(R.id.tvFechaRegistro);
        tvEstadoArea = findViewById(R.id.tvEstadoArea);
        tvUltimaRevision = findViewById(R.id.tvUltimaRevision);
        tvHumedad = findViewById(R.id.tvHumedad);
        tvLuz = findViewById(R.id.tvLuz);
        pbHumedad = findViewById(R.id.pbHumedad);
        pbLuz = findViewById(R.id.pbLuz);
        btnRegistrarActividad = findViewById(R.id.btnRegistrarActividad);
        btnEditarArea = findViewById(R.id.btnEditarArea);
        rvActividades = findViewById(R.id.rvActividades);
        tvSinActividades = findViewById(R.id.tvSinActividades);

        // Configurar RecyclerView
        rvActividades.setLayoutManager(new LinearLayoutManager(this));

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
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetalleAreaVerdeActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        ivUserAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetalleAreaVerdeActivity.this, PerfilUsuarioActivity.class);
                startActivity(intent);
            }
        });

        btnRegistrarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoRegistrarActividad();
            }
        });

        btnEditarArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoEditarArea();
            }
        });
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
            tvSinActividades.setVisibility(View.GONE);
            rvActividades.setVisibility(View.VISIBLE);

            actividadAdapter = new ActividadAdapter(this, areaVerde.getActividades());
            rvActividades.setAdapter(actividadAdapter);
        } else {
            tvSinActividades.setVisibility(View.VISIBLE);
            rvActividades.setVisibility(View.GONE);
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
}