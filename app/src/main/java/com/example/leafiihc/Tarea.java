package com.example.leafiihc;

import java.util.Date;

public class Tarea {
    private String titulo;
    private String descripcion;
    private Date fecha;
    private boolean completada;
    private boolean notificacion;

    public Tarea(String titulo, String descripcion, Date fecha) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.completada = false;
        this.notificacion = false;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public boolean isNotificacion() {
        return notificacion;
    }

    public void setNotificacion(boolean notificacion) {
        this.notificacion = notificacion;
    }
}