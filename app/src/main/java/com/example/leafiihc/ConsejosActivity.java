package com.example.leafiihc;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

public class ConsejosActivity extends AppCompatActivity implements TareaAdapter.OnTareaListener {

    private ImageView ivBack, ivHome;
    private CircleImageView ivUserAvatar;
    private RecyclerView rvTareas;
    private FloatingActionButton fabAgregarTarea;
    private TextView tvSinTareas;

    private List<Tarea> tareasList;
    private TareaAdapter tareaAdapter;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consejos);

        // Inicializar vistas
        ivBack = findViewById(R.id.ivBack);
        ivHome = findViewById(R.id.ivHome);
        ivUserAvatar = findViewById(R.id.ivUserAvatar);
        rvTareas = findViewById(R.id.rvTareas);
        fabAgregarTarea = findViewById(R.id.fabAgregarTarea);
        tvSinTareas = findViewById(R.id.tvSinTareas);

        // Configurar RecyclerView
        rvTareas.setLayoutManager(new LinearLayoutManager(this));

        // Cargar tareas
        cargarTareas();
        actualizarListaTareas();

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
                Intent intent = new Intent(ConsejosActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        ivUserAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsejosActivity.this, PerfilUsuarioActivity.class);
                startActivity(intent);
            }
        });

        fabAgregarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoAgregarTarea();
            }
        });
    }

    private void cargarTareas() {
        SharedPreferences prefs = getSharedPreferences("TareasPrefs", MODE_PRIVATE);
        String tareasJson = prefs.getString("tareas", "");

        if (!tareasJson.isEmpty()) {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Tarea>>(){}.getType();
            tareasList = gson.fromJson(tareasJson, type);
        } else {
            tareasList = new ArrayList<>();
        }
    }

    private void guardarTareas() {
        SharedPreferences prefs = getSharedPreferences("TareasPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Gson gson = new Gson();
        String tareasJson = gson.toJson(tareasList);
        editor.putString("tareas", tareasJson);
        editor.apply();
    }

    private void actualizarListaTareas() {
        if (tareasList != null && !tareasList.isEmpty()) {
            tvSinTareas.setVisibility(View.GONE);
            rvTareas.setVisibility(View.VISIBLE);

            tareaAdapter = new TareaAdapter(this, tareasList, this);
            rvTareas.setAdapter(tareaAdapter);
        } else {
            tvSinTareas.setVisibility(View.VISIBLE);
            rvTareas.setVisibility(View.GONE);
        }
    }

    private void mostrarDialogoAgregarTarea() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_agregar_tarea);
        dialog.setCancelable(true);

        // Inicializar vistas del diálogo
        final EditText etTituloTarea = dialog.findViewById(R.id.etTituloTarea);
        final EditText etDescripcionTarea = dialog.findViewById(R.id.etDescripcionTarea);
        final Spinner spinnerCategoriaTarea = dialog.findViewById(R.id.spinnerCategoriaTarea);
        final Spinner spinnerPrioridadTarea = dialog.findViewById(R.id.spinnerPrioridadTarea);
        final EditText etFechaVencimiento = dialog.findViewById(R.id.etFechaVencimiento);
        final ImageView ivCalendarioTarea = dialog.findViewById(R.id.ivCalendarioTarea);
        Button btnCancelarTarea = dialog.findViewById(R.id.btnCancelarTarea);
        Button btnGuardarTarea = dialog.findViewById(R.id.btnGuardarTarea);

        // Configurar spinner de categorías
        String[] categorias = {"Riego", "Fertilización", "Poda", "Fumigación", "Limpieza", "Siembra", "Otro"};
        ArrayAdapter<String> adapterCategorias = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categorias);
        adapterCategorias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoriaTarea.setAdapter(adapterCategorias);

        // Configurar spinner de prioridades
        String[] prioridades = {"Alta", "Media", "Baja"};
        ArrayAdapter<String> adapterPrioridades = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, prioridades);
        adapterPrioridades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPrioridadTarea.setAdapter(adapterPrioridades);

        // Configurar fecha actual
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 7); // Por defecto, una semana después
        etFechaVencimiento.setText(sdf.format(calendar.getTime()));

        // Configurar selector de fecha
        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                etFechaVencimiento.setText(sdf.format(calendar.getTime()));
            }
        };

        etFechaVencimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ConsejosActivity.this, dateSetListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        ivCalendarioTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ConsejosActivity.this, dateSetListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // Configurar botones
        btnCancelarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnGuardarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validar campos
                if (etTituloTarea.getText().toString().trim().isEmpty()) {
                    Toast.makeText(ConsejosActivity.this, "Por favor ingresa un título", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    // Crear nueva tarea
                    String titulo = etTituloTarea.getText().toString().trim();
                    String descripcion = etDescripcionTarea.getText().toString().trim();
                    String categoria = spinnerCategoriaTarea.getSelectedItem().toString();
                    String prioridad = spinnerPrioridadTarea.getSelectedItem().toString();
                    Date fechaVencimiento = sdf.parse(etFechaVencimiento.getText().toString());

                    Tarea nuevaTarea = new Tarea(titulo, descripcion, fechaVencimiento, prioridad, categoria);
                    tareasList.add(0, nuevaTarea); // Agregar al inicio de la lista

                    // Guardar cambios
                    guardarTareas();

                    // Actualizar UI
                    actualizarListaTareas();

                    Toast.makeText(ConsejosActivity.this, "Tarea agregada correctamente", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                } catch (ParseException e) {
                    Toast.makeText(ConsejosActivity.this, "Error al procesar la fecha", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }

    @Override
    public void onTareaCompletadaChange(Tarea tarea, boolean isChecked) {
        // Actualizar estado de la tarea
        tarea.setCompletada(isChecked);
        guardarTareas();
    }

    @Override
    public void onTareaDelete(final Tarea tarea, final int position) {
        // Confirmar eliminación
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar tarea");
        builder.setMessage("¿Estás seguro de que deseas eliminar esta tarea?");
        builder.setPositiveButton("Eliminar", (dialog, which) -> {
            tareasList.remove(position);
            tareaAdapter.notifyItemRemoved(position);
            guardarTareas();
            actualizarListaTareas();
            Toast.makeText(ConsejosActivity.this, "Tarea eliminada", Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }
}