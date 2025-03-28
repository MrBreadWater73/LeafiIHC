package com.example.leafiihc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarioFragment extends Fragment {

    private CalendarView calendarView;
    private TextView tvFechaSeleccionada;
    private RecyclerView rvTareas;
    private TareaAdapter tareaAdapter;
    private List<Tarea> tareasList;
    private List<Tarea> tareasListFull;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendario, container, false);
        
        // Inicializar vistas
        calendarView = view.findViewById(R.id.calendarView);
        tvFechaSeleccionada = view.findViewById(R.id.tvFechaSeleccionada);
        rvTareas = view.findViewById(R.id.rvTareas);
        
        // Configurar RecyclerView
        rvTareas.setLayoutManager(new LinearLayoutManager(getContext()));
        
        // Cargar datos
        tareasListFull = new ArrayList<>();
        cargarTareas();
        
        // Mostrar tareas para la fecha actual
        Date fechaActual = new Date();
        tareasList = filtrarTareasPorFecha(fechaActual);
        
        // Configurar adaptador
        tareaAdapter = new TareaAdapter(getContext(), tareasList);
        rvTareas.setAdapter(tareaAdapter);
        
        // Configurar fecha seleccionada
        SimpleDateFormat sdf = new SimpleDateFormat("d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
        tvFechaSeleccionada.setText("Tareas para el " + sdf.format(fechaActual));
        
        // Configurar listener del calendario
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                Date fechaSeleccionada = calendar.getTime();
                
                // Actualizar título
                tvFechaSeleccionada.setText("Tareas para el " + sdf.format(fechaSeleccionada));
                
                // Filtrar tareas por fecha
                tareasList.clear();
                tareasList.addAll(filtrarTareasPorFecha(fechaSeleccionada));
                tareaAdapter.notifyDataSetChanged();
            }
        });
        
        return view;
    }

    private void cargarTareas() {
        // Crear algunas tareas de ejemplo para diferentes fechas
        Calendar calendar = Calendar.getInstance();
        
        // Tareas para hoy
        tareasListFull.add(new Tarea(
            "Regar las plantas",
            "Regar todas las plantas de interior",
            calendar.getTime()
        ));
        
        tareasListFull.add(new Tarea(
            "Fertilizar orquídeas",
            "Aplicar fertilizante específico para orquídeas",
            calendar.getTime()
        ));
        
        // Tareas para mañana
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        tareasListFull.add(new Tarea(
            "Podar arbustos",
            "Podar los arbustos del jardín",
            calendar.getTime()
        ));
        
        // Tareas para la próxima semana
        calendar.add(Calendar.DAY_OF_MONTH, 6);
        tareasListFull.add(new Tarea(
            "Trasplantar suculentas",
            "Cambiar las suculentas a macetas más grandes",
            calendar.getTime()
        ));
        
        tareasListFull.add(new Tarea(
            "Revisar plagas",
            "Inspeccionar plantas en busca de plagas",
            calendar.getTime()
        ));
    }

    private List<Tarea> filtrarTareasPorFecha(Date fecha) {
        List<Tarea> tareasFiltradas = new ArrayList<>();
        
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(fecha);
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);
        
        Calendar cal2 = Calendar.getInstance();
        
        for (Tarea tarea : tareasListFull) {
            cal2.setTime(tarea.getFecha());
            cal2.set(Calendar.HOUR_OF_DAY, 0);
            cal2.set(Calendar.MINUTE, 0);
            cal2.set(Calendar.SECOND, 0);
            cal2.set(Calendar.MILLISECOND, 0);
            
            if (cal1.getTimeInMillis() == cal2.getTimeInMillis()) {
                tareasFiltradas.add(tarea);
            }
        }
        
        return tareasFiltradas;
    }
}