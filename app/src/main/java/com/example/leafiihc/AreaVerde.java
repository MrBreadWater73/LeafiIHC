package com.example.leafiihc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AreaVerde {
    private String nombre;
    private String codigo;
    private Date fechaRegistro;
    private String estado;
    private Date ultimaRevision;
    private int nivelHumedad;
    private int nivelLuz;
    private List<Actividad> actividades;
    private List<Tarea> tareas;
    private boolean seleccionada;

    public AreaVerde(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.fechaRegistro = new Date();
        this.estado = "Saludable";
        this.ultimaRevision = new Date();
        this.nivelHumedad = 75;
        this.nivelLuz = 85;
        this.actividades = new ArrayList<>();
        this.tareas = new ArrayList<>();
        this.seleccionada = false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getUltimaRevision() {
        return ultimaRevision;
    }

    public void setUltimaRevision(Date ultimaRevision) {
        this.ultimaRevision = ultimaRevision;
    }

    public int getNivelHumedad() {
        return nivelHumedad;
    }

    public void setNivelHumedad(int nivelHumedad) {
        this.nivelHumedad = nivelHumedad;
    }

    public int getNivelLuz() {
        return nivelLuz;
    }

    public void setNivelLuz(int nivelLuz) {
        this.nivelLuz = nivelLuz;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }

    public void addActividad(Actividad actividad) {
        this.actividades.add(actividad);
    }

    public boolean isSeleccionada() {
        return seleccionada;
    }

    public void setSeleccionada(boolean seleccionada) {
        this.seleccionada = seleccionada;
    }

    // Clase interna para representar actividades
    public static class Actividad {
        private String tipo;
        private Date fecha;
        private String descripcion;

        public Actividad(String tipo, Date fecha, String descripcion) {
            this.tipo = tipo;
            this.fecha = fecha;
            this.descripcion = descripcion;
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public Date getFecha() {
            return fecha;
        }

        public void setFecha(Date fecha) {
            this.fecha = fecha;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }
    }

    public static class Tarea {
        private String titulo;
        private String descripcion;
        private Date fechaVencimiento;
        private String prioridad;
        private String categoria;
        private boolean completada;

        public Tarea(String titulo, String descripcion, Date fechaVencimiento, String prioridad, String categoria) {
            this.titulo = titulo;
            this.descripcion = descripcion;
            this.fechaVencimiento = fechaVencimiento;
            this.prioridad = prioridad;
            this.categoria = categoria;
            this.completada = false;
        }

        public String getTitulo() {
            return titulo;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public Date getFechaVencimiento() {
            return fechaVencimiento;
        }

        public String getPrioridad() {
            return prioridad;
        }

        public String getCategoria() {
            return categoria;
        }

        public boolean isCompletada() {
            return completada;
        }

        public void setCompletada(boolean completada) {
            this.completada = completada;
        }
    }
}