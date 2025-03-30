package com.example.leafiihc;

import java.util.Date;
import java.util.UUID;

public class Tarea {
    private String id;
    private String titulo;
    private String descripcion;
    private Date fechaCreacion;
    private Date fechaVencimiento;
    private boolean completada;
    private String prioridad; // "Alta", "Media", "Baja"
    private String categoria; // "Riego", "Fertilización", "Poda", etc.

    public Tarea() {
        this.id = UUID.randomUUID().toString();
        this.fechaCreacion = new Date();
        this.completada = false;
    }

    // Constructor completo
    public Tarea(String titulo, String descripcion, Date fechaVencimiento, String prioridad, String categoria) {
        this.id = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaCreacion = new Date();
        this.fechaVencimiento = fechaVencimiento;
        this.completada = false;
        this.prioridad = prioridad;
        this.categoria = categoria;
    }

    // Constructor simplificado para compatibilidad
    public Tarea(String titulo, String descripcion, Date fechaVencimiento) {
        this.id = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaCreacion = new Date();
        this.fechaVencimiento = fechaVencimiento;
        this.completada = false;
        this.prioridad = "Media"; // Valor por defecto
        this.categoria = "Otro"; // Valor por defecto
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    // Metodo alias para compatibilidad
    public Date getFecha() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}